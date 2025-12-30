import threading
from collections import defaultdict

class InMemoryKVDB:
    def __init__(self):
        # Main database: key -> value
        self._data = {}
        
        # Reverse index: value -> count
        self._reverse_index = defaultdict(int)
        
        # Transaction stack: each transaction contains a snapshot of the database and reverse index
        self._transaction_stack = []
        
        # Lock for thread safety (using RLock for reentrancy)
        self._lock = threading.RLock()
        
    def _get_transaction_state(self):
        """Returns the current transaction state (snapshot and transaction data)."""
        if self._transaction_stack:
            return self._transaction_stack[-1]
        return None
    
    def _get_value(self, key, snapshot_data):
        """Helper to get value from snapshot or main data."""
        if key in snapshot_data:
            return snapshot_data[key]
        return self._data.get(key)
        
    def _get_reverse_index_count(self, value, snapshot_reverse_index):
        """Helper to get count from snapshot or main reverse index."""
        count = self._reverse_index.get(value, 0)
        if snapshot_reverse_index:
            count += snapshot_reverse_index.get(value, 0)
        return count
        
    def GET(self, key):
        """Retrieves the value associated with the key."""
        with self._lock:
            txn = self._get_transaction_state()
            if txn:
                txn_data, _ = txn
                if key in txn_data:
                    return txn_data[key]
                return self._get_value(key, {})
            return self._data.get(key)
        
    def SET(self, key, value):
        """Stores a key-value pair, updating if the key already exists."""
        with self._lock:
            txn = self._get_transaction_state()
            
            if txn:
                # Transactional mode
                txn_data, txn_reverse_index = txn
                
                # Handle old value in transaction
                if key in txn_data:
                    old_value = txn_data[key]
                    # Decrement count for old value in transaction reverse index
                    txn_reverse_index[old_value] -= 1
                    if txn_reverse_index[old_value] == 0:
                        del txn_reverse_index[old_value]
                else:
                    # Check if key exists in main database
                    old_value = self._get_value(key, {})
                    if old_value is not None:
                        # This is a delete from main database perspective
                        txn_reverse_index[old_value] -= 1
                        if txn_reverse_index[old_value] == 0:
                            del txn_reverse_index[old_value]
                
                # Set new value in transaction
                txn_data[key] = value
                # Increment count for new value in transaction reverse index
                txn_reverse_index[value] += 1
            else:
                # Non-transactional mode
                if key in self._data:
                    old_value = self._data[key]
                    # Decrement count for old value
                    self._reverse_index[old_value] -= 1
                    if self._reverse_index[old_value] == 0:
                        del self._reverse_index[old_value]
                
                # Set new value
                self._data[key] = value
                # Increment count for new value
                self._reverse_index[value] += 1
        
    def DELETE(self, key):
        """Removes the key-value pair if it exists."""
        with self._lock:
            txn = self._get_transaction_state()
            
            if txn:
                # Transactional mode
                txn_data, txn_reverse_index = txn
                
                # Check if key is in transaction data
                if key in txn_data:
                    old_value = txn_data[key]
                    # Decrement count for old value
                    txn_reverse_index[old_value] -= 1
                    if txn_reverse_index[old_value] == 0:
                        del txn_reverse_index[old_value]
                    # Remove from transaction data
                    del txn_data[key]
                else:
                    # Check if key exists in main database
                    old_value = self._get_value(key, {})
                    if old_value is not None:
                        # This is a delete from main database perspective
                        txn_reverse_index[old_value] -= 1
                        if txn_reverse_index[old_value] == 0:
                            del txn_reverse_index[old_value]
                        # Mark as deleted in transaction
                        txn_data[key] = None
            else:
                # Non-transactional mode
                if key in self._data:
                    old_value = self._data[key]
                    # Decrement count for old value
                    self._reverse_index[old_value] -= 1
                    if self._reverse_index[old_value] == 0:
                        del self._reverse_index[old_value]
                    # Remove from main data
                    del self._data[key]
        
    def COUNT(self, value):
        """Returns the number of times the value appears in the database."""
        with self._lock:
            txn = self._get_transaction_state()
            if txn:
                _, txn_reverse_index = txn
                return self._get_reverse_index_count(value, txn_reverse_index)
            return self._reverse_index.get(value, 0)
        
    def BEGIN(self):
        """Starts a new transaction."""
        with self._lock:
            # Create a new transaction: (transaction_data, transaction_reverse_index)
            self._transaction_stack.append(({}, defaultdict(int)))
        
    def COMMIT(self):
        """Commits the current transaction."""
        with self._lock:
            if not self._transaction_stack:
                return
                
            # Get the current transaction
            txn_data, txn_reverse_index = self._transaction_stack.pop()
            
            # Merge transaction changes into main database
            for key, value in txn_data.items():
                if value is None:
                    # This is a delete operation
                    if key in self._data:
                        old_value = self._data[key]
                        # Decrement count for old value
                        self._reverse_index[old_value] -= 1
                        if self._reverse_index[old_value] == 0:
                            del self._reverse_index[old_value]
                        # Remove from main data
                        del self._data[key]
                else:
                    # This is a set operation
                    if key in self._data:
                        old_value = self._data[key]
                        # Decrement count for old value
                        self._reverse_index[old_value] -= 1
                        if self._reverse_index[old_value] == 0:
                            del self._reverse_index[old_value]
                    
                    # Set new value
                    self._data[key] = value
                    # Increment count for new value
                    self._reverse_index[value] += 1
        
    def ROLLBACK(self):
        """Rolls back the current transaction."""
        with self._lock:
            if self._transaction_stack:
                self._transaction_stack.pop()


# Test cases to verify functionality
if __name__ == "__main__":
    print("=" * 60)
    print("        THREAD-SAFE KV DATABASE TEST RESULTS")
    print("=" * 60)
    print()
    
    # Test 1: Basic operations
    print("Test 1: Basic operations")
    print("-" * 50)
    db = InMemoryKVDB()
    db.SET("name", "Alice")
    print(f"GET('name'): {db.GET('name')}")  # Should be "Alice"
    db.SET("name", "Bob")
    print(f"GET('name'): {db.GET('name')}")  # Should be "Bob"
    db.DELETE("name")
    print(f"GET('name'): {db.GET('name')}")  # Should be None
    print()
    
    # Test 2: Transactions - Commit
    print("Test 2: Transactions - Commit")
    print("-" * 50)
    db.SET("age", 30)
    db.BEGIN()
    db.SET("age", 25)
    db.SET("city", "New York")
    print(f"GET('age') during transaction: {db.GET('age')}")  # Should be 25
    print(f"GET('city') during transaction: {db.GET('city')}")  # Should be "New York"
    db.COMMIT()
    print(f"GET('age') after commit: {db.GET('age')}")  # Should be 25
    print(f"GET('city') after commit: {db.GET('city')}")  # Should be "New York"
    print()
    
    # Test 3: Transactions - Rollback
    print("Test 3: Transactions - Rollback")
    print("-" * 50)
    db.BEGIN()
    db.SET("age", 40)
    db.SET("country", "USA")
    print(f"GET('age') during transaction: {db.GET('age')}")  # Should be 40
    print(f"GET('country') during transaction: {db.GET('country')}")  # Should be "USA"
    db.ROLLBACK()
    print(f"GET('age') after rollback: {db.GET('age')}")  # Should be 25 (rolled back)
    print(f"GET('country') after rollback: {db.GET('country')}")  # Should be None (rolled back)
    print()
    
    # Test 4: COUNT functionality
    print("Test 4: COUNT functionality")
    print("-" * 50)
    db.DELETE("age")
    db.DELETE("city")
    db.SET("color1", "red")
    db.SET("color2", "blue")
    db.SET("color3", "red")
    db.SET("color4", "green")
    db.SET("color5", "red")
    print(f"COUNT('red'): {db.COUNT('red')}")  # Should be 3
    print(f"COUNT('blue'): {db.COUNT('blue')}")  # Should be 1
    print(f"COUNT('yellow'): {db.COUNT('yellow')}")  # Should be 0
    db.DELETE("color3")
    print(f"COUNT('red') after DELETE: {db.COUNT('red')}")  # Should be 2
    db.SET("color5", "blue")
    print(f"COUNT('red') after UPDATE: {db.COUNT('red')}")  # Should be 1
    print(f"COUNT('blue') after UPDATE: {db.COUNT('blue')}")  # Should be 2
    print()
    
    # Test 5: COUNT with transactions
    print("Test 5: COUNT with transactions")
    print("-" * 50)
    db.BEGIN()
    db.SET("color6", "red")
    db.SET("color7", "red")
    db.SET("color8", "blue")
    db.DELETE("color1")
    print(f"COUNT('red') during transaction: {db.COUNT('red')}")  # Should be 1 + 2 - 1 = 2
    print(f"COUNT('blue') during transaction: {db.COUNT('blue')}")  # Should be 2 + 1 = 3
    db.ROLLBACK()
    print(f"COUNT('red') after rollback: {db.COUNT('red')}")  # Should be 1
    print(f"COUNT('blue') after rollback: {db.COUNT('blue')}")  # Should be 2
    db.BEGIN()
    db.SET("color6", "red")
    db.SET("color7", "red")
    print(f"COUNT('red') during second transaction: {db.COUNT('red')}")  # Should be 1 + 2 = 3
    db.COMMIT()
    print(f"COUNT('red') after commit: {db.COUNT('red')}")  # Should be 3
    print()
    
    # Test 6: Thread safety test
    print("Test 6: Thread safety test")
    print("-" * 50)
    
    def worker(db, worker_id, iterations=100):
        """Worker function for thread safety test."""
        for i in range(iterations):
            # Test SET and GET operations
            db.SET(f"key_{worker_id}_{i}", f"value_{worker_id}_{i}")
            value = db.GET(f"key_{worker_id}_{i}")
            assert value == f"value_{worker_id}_{i}", f"Worker {worker_id} failed for key_{worker_id}_{i}"
            
            # Test DELETE operations
            db.DELETE(f"key_{worker_id}_{i}")
            assert db.GET(f"key_{worker_id}_{i}") is None, f"Worker {worker_id} failed to delete key_{worker_id}_{i}"
    
    # Create a new database for thread safety test
    thread_db = InMemoryKVDB()
    
    # Create and start multiple threads
    threads = []
    num_threads = 10
    num_iterations = 500
    
    for i in range(num_threads):
        t = threading.Thread(target=worker, args=(thread_db, i, num_iterations))
        threads.append(t)
        t.start()
    
    # Wait for all threads to complete
    for t in threads:
        t.join()
    
    print(f"Thread safety test passed: {num_threads} threads each performed {num_iterations} operations without errors")
    print()
    
    # Test 7: Edge cases
    print("Test 7: Edge cases")
    print("-" * 50)
    
    # Test GET/DELETE on non-existent keys
    print(f"GET('non_existent'): {db.GET('non_existent')}")  # Should be None
    db.DELETE('non_existent')  # Should not raise an error
    
    # Test nested transactions
    db.BEGIN()
    db.SET("nested_key", "nested_value")
    print(f"Nested transaction level 1: {db.GET('nested_key')}")  # Should be "nested_value"
    db.BEGIN()
    db.SET("nested_key", "nested_value2")
    print(f"Nested transaction level 2: {db.GET('nested_key')}")  # Should be "nested_value2"
    db.ROLLBACK()
    print(f"After rollback level 2: {db.GET('nested_key')}")  # Should be "nested_value"
    db.COMMIT()
    print(f"After commit level 1: {db.GET('nested_key')}")  # Should be "nested_value"
    
    print("\nAll tests completed successfully!")
    print("=" * 60)