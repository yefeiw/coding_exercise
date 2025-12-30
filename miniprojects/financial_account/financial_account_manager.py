class AccountBalance:
    """
    Account balance management class for managing grants and spends, with support for querying balance by timestamp.
    Core design:
    - grants: List sorted by expiration_time in ascending order, supporting binary search for efficient expired grant filtering
    - spends: List sorted by timestamp in ascending order, supporting binary search for efficient total spend calculation up to a specified time
    """
    
    def __init__(self):
        """Initialize the account management class"""
        # Store grants sorted by expiration_time in ascending order, supporting binary search for efficient expired grant filtering
        # Each element is a tuple(amount, expiration_time)
        self.grants = []
        
        # Store spends sorted by timestamp in ascending order, supporting binary search for efficient total spend calculation up to a specified time
        # Each element is a tuple(amount, timestamp)
        self.spends = []
    
    def addGrant(self, amount: float, expiration_time: int):
        """
        Add a grant
        
        Args:
            amount: Grant amount, must be positive
            expiration_time: Expiration timestamp (Unix timestamp, integer)
            
        Raises:
            ValueError: If amount is not positive
        """
        # Validate that amount must be positive
        if amount <= 0:
            raise ValueError("Grant amount must be positive")
        
        # Find insertion position to keep list sorted by expiration_time in ascending order
        # Use binary search to optimize insertion efficiency
        import bisect
        insert_pos = bisect.bisect_left(self.grants, expiration_time, key=lambda x: x[1])
        self.grants.insert(insert_pos, (amount, expiration_time))
    
    def addSpend(self, amount: float, timestamp: int):
        """
        Add a spend transaction
        
        Args:
            amount: Spend amount, must be positive
            timestamp: Spend timestamp (Unix timestamp, integer)
            
        Raises:
            ValueError: If amount is not positive
        """
        # Validate that amount must be positive
        if amount <= 0:
            raise ValueError("Spend amount must be positive")
        
        # Find insertion position to keep list sorted by timestamp in ascending order
        import bisect
        insert_pos = bisect.bisect_left(self.spends, timestamp, key=lambda x: x[1])
        self.spends.insert(insert_pos, (amount, timestamp))
    
    def getBalance(self, timestamp: int) -> float:
        """
        Query the account balance at the specified timestamp
        
        Calculation logic:
        1. Filter out all expired grants (expiration_time < timestamp)
        2. Calculate the total amount of all valid grants
        3. Calculate the total amount of all spends with timestamp <= query timestamp
        4. Balance = total valid grant amount - total spend amount
        
        Args:
            timestamp: Query timestamp (Unix timestamp, integer)
            
        Returns:
            float: Account balance (may be negative, indicating overdraft)
        """
        import bisect
        
        # 1. Calculate total valid grant amount
        # Find the first position where expiration_time >= timestamp
        # All grants before this position are expired (expiration_time < timestamp)
        valid_grants_pos = bisect.bisect_left(self.grants, timestamp, key=lambda x: x[1])
        
        # Calculate total amount of unexpired grants (including grants expiring exactly at timestamp)
        valid_grants_total = sum(grant[0] for grant in self.grants[valid_grants_pos:])
        
        # 2. Calculate total spend amount up to timestamp
        # Find the first position where timestamp > query time
        # All spends before this position meet the condition (spend_time <= timestamp)
        spend_pos = bisect.bisect_right(self.spends, timestamp, key=lambda x: x[1])
        total_spend = sum(spend[0] for spend in self.spends[:spend_pos])
        
        # 3. Calculate and return balance
        balance = valid_grants_total - total_spend
        return balance


def test_account_balance():
    """Test cases"""
    print("=" * 80)
    print("ACCOUNT BALANCE TEST")
    print("=" * 80)
    
    # Create account instance
    account = AccountBalance()
    print("1. AccountBalance initialized")
    
    # Test case 1: Grant not expired scenario
    print("\n2. Testing Grant not expired scenario:")
    account.addGrant(100.0, 1000)  # 100 yuan, expires at 1000
    balance_999 = account.getBalance(999)
    balance_1000 = account.getBalance(1000)
    balance_1001 = account.getBalance(1001)
    print(f"   Grant(100, 1000)")
    print(f"   Balance at timestamp 999: {balance_999}")  # Expected: 100
    print(f"   Balance at timestamp 1000: {balance_1000}")  # Expected: 100 (exactly at expiration time, considered valid)
    print(f"   Balance at timestamp 1001: {balance_1001}")  # Expected: 0 (expired)
    
    # Test case 2: Normal spend scenario
    print("\n3. Testing normal spend scenario:")
    account = AccountBalance()
    account.addGrant(200.0, 2000)  # 200 yuan, expires at 2000
    account.addSpend(150.0, 1500)  # Spend 150 yuan at time 1500
    balance_1600 = account.getBalance(1600)
    print(f"   Grant(200, 2000) -> Spend(150, 1500)")
    print(f"   Balance at timestamp 1600: {balance_1600}")  # Expected: 50
    
    # Test case 3: Spend exceeds valid balance scenario
    print("\n4. Testing overspend scenario:")
    account = AccountBalance()
    account.addGrant(50.0, 3000)  # 50 yuan, expires at 3000
    account.addSpend(100.0, 2000)  # Spend 100 yuan at time 2000
    balance_2500 = account.getBalance(2500)
    print(f"   Grant(50, 3000) -> Spend(100, 2000)")
    print(f"   Balance at timestamp 2500: {balance_2500}")  # Expected: -50 (overdraft allowed)
    
    # Test case 4: Multiple grants and spends scenario
    print("\n5. Testing multiple grants and spends scenario:")
    account = AccountBalance()
    account.addGrant(100.0, 500)   # 100 yuan, expires at 500
    account.addGrant(200.0, 1500)  # 200 yuan, expires at 1500
    account.addGrant(300.0, 2500)  # 300 yuan, expires at 2500
    account.addSpend(150.0, 1000)  # Spend 150 yuan at time 1000
    account.addSpend(200.0, 2000)  # Spend 200 yuan at time 2000
    
    balance_700 = account.getBalance(700)
    balance_1700 = account.getBalance(1700)
    balance_2700 = account.getBalance(2700)
    
    print(f"   Grants: [100@500, 200@1500, 300@2500]")
    print(f"   Spends: [150@1000, 200@2000]")
    print(f"   Balance at timestamp 700: {balance_700}")   # Expected: 100 (100 expired, 200 not expired, spend 150 not yet occurred)
    print(f"   Balance at timestamp 1700: {balance_1700}")  # Expected: 50 (100 expired, 200 expired, 300 not expired; spend 150 occurred)
    print(f"   Balance at timestamp 2700: {balance_2700}")  # Expected: -50 (all grants expired; spends 150+200=350 occurred)
    
    # Test case 5: Edge case - exact timestamps
    print("\n6. Testing edge case - exact timestamps:")
    account = AccountBalance()
    account.addGrant(50.0, 1000)  # 50 yuan, expires at 1000
    account.addGrant(75.0, 1000)  # 75 yuan, expires at 1000
    account.addSpend(100.0, 1000) # Spend 100 yuan at time 1000
    
    balance_1000 = account.getBalance(1000)
    print(f"   Grants: [50@1000, 75@1000] -> Spend: [100@1000]")
    print(f"   Balance at timestamp 1000: {balance_1000}")  # Expected: 25 (all grants valid, spend occurred)


if __name__ == "__main__":
    test_account_balance()
