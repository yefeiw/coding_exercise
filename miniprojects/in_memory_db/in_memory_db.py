from typing import List, Dict, Any, Tuple, Union, Optional
import logging
from datetime import datetime

# Configure logging
logging.basicConfig(level=logging.DEBUG, format='%(asctime)s - %(levelname)s - %(message)s')
logger = logging.getLogger(__name__)

class Database:
    def __init__(self):
        self.tables = {}
    
    def create_table(self, name: str, columns: List[str]) -> None:
        logger.info(f"Creating table '{name}' with columns: {columns}")
        if name in self.tables:
            logger.error(f"Table '{name}' already exists")
            raise ValueError(f"Table '{name}' already exists")
        self.tables[name] = Table(name, columns)
        logger.info(f"Successfully created table '{name}'")
    
    def drop_table(self, name: str) -> None:
        logger.info(f"Attempting to drop table '{name}'")
        if name not in self.tables:
            logger.error(f"Table '{name}' does not exist")
            raise ValueError(f"Table '{name}' does not exist")
        del self.tables[name]
        logger.info(f"Successfully dropped table '{name}'")
    
    def get_table(self, name: str) -> 'Table':
        logger.info(f"Attempting to get table '{name}'")
        if name not in self.tables:
            logger.error(f"Table '{name}' does not exist")
            raise ValueError(f"Table '{name}' does not exist")
        logger.info(f"Successfully retrieved table '{name}'")
        return self.tables[name]

class Table:
    def __init__(self, name: str, columns: List[str]):
        self.name = name
        self.columns = columns
        self.rows = []
    
    def insert(self, row: Dict[str, Any]) -> None:
        logger.info(f"Inserting row into table '{self.name}': {row}")
        missing_columns = [col for col in self.columns if col not in row]
        if missing_columns:
            logger.error(f"Missing required columns for table '{self.name}': {missing_columns}")
            raise ValueError(f"Missing required columns: {missing_columns}")
        filtered_row = {col: row[col] for col in self.columns}
        self.rows.append(filtered_row)
        logger.info(f"Successfully inserted row into table '{self.name}': {filtered_row}")
        logger.info(f"Table '{self.name}' now has {len(self.rows)} rows")
    
    def select(self, columns: List[str] = None, where: Union[Dict, List[Tuple]] = None) -> List[Dict[str, Any]]:
        logger.info(f"Selecting from table '{self.name}' with columns: {columns}, where: {where}")
        selected_columns = columns if columns else self.columns
        invalid_columns = [col for col in selected_columns if col not in self.columns]
        if invalid_columns:
            logger.error(f"Invalid columns for table '{self.name}': {invalid_columns}")
            raise ValueError(f"Invalid columns: {invalid_columns}")
        
        result = []
        logger.info(f"Processing {len(self.rows)} rows in table '{self.name}'")
        
        # Convert dictionary where clause to list format for uniform processing
        where_conditions = []
        if where:
            if isinstance(where, dict):
                # Legacy dictionary format
                for col, condition in where.items():
                    if isinstance(condition, tuple):
                        where_conditions.append((col, condition[0], condition[1]))
                    else:
                        where_conditions.append((col, "=", condition))
            elif isinstance(where, list):
                # New list format: [(col, op, value), ...]
                where_conditions = where
            else:
                logger.error(f"Invalid where clause format: {type(where).__name__}")
                raise ValueError(f"Where clause must be a dict or list of tuples")
        
        for i, row in enumerate(self.rows):
            logger.debug(f"Processing row {i+1} in table '{self.name}': {row}")
            match = True
            
            if where_conditions:
                logger.debug(f"Applying where clause: {where_conditions} to row {i+1}")
                for condition in where_conditions:
                    if len(condition) != 3:
                        logger.error(f"Invalid condition format: {condition}")
                        raise ValueError(f"Condition must be a tuple of (column, operator, value)")
                    
                    col, op, value = condition
                    if col not in row:
                        logger.error(f"Column '{col}' not found in table '{self.name}'")
                        raise ValueError(f"Column '{col}' not found in table")
                    row_value = row[col]
                    
                    logger.debug(f"Checking condition: {col} {op} {value} for value {row_value}")
                    if op == ">":
                        if not (row_value > value):
                            match = False
                            break
                    elif op == ">=":
                        if not (row_value >= value):
                            match = False
                            break
                    elif op == "<":
                        if not (row_value < value):
                            match = False
                            break
                    elif op == "<=":
                        if not (row_value <= value):
                            match = False
                            break
                    elif op == "!=":
                        if not (row_value != value):
                            match = False
                            break
                    elif op == "=":
                        if not (row_value == value):
                            match = False
                            break
                    elif op == "contains":
                        if not (value in str(row_value)):
                            match = False
                            break
                    else:
                        logger.error(f"Unsupported operator: {op}")
                        raise ValueError(f"Unsupported operator: {op}")
                
                if not match:
                    logger.debug(f"Row {i+1} did not match where clause, skipping")
                    continue
            
            selected_row = {col: row[col] for col in selected_columns}
            result.append(selected_row)
            logger.debug(f"Row {i+1} matched and added to results: {selected_row}")
        
        logger.info(f"Select completed from table '{self.name}': found {len(result)} matching rows")
        return result
    
    def join(self, other_table: 'Table', on: tuple[str, str], join_type: str = "inner") -> list[dict[str, any]]:
        logger.info(f"Performing {join_type} join on table '{self.name}' with table '{other_table.name}'")
        logger.info(f"Join condition: {self.name}.{on[0]} = {other_table.name}.{on[1]}")
        
        self_col, other_col = on
        if self_col not in self.columns:
            logger.error(f"Column '{self_col}' not found in table '{self.name}'")
            raise ValueError(f"Column '{self_col}' not found in table '{self.name}'")
        logger.debug(f"Validated join column '{self_col}' exists in table '{self.name}'")
        
        if other_col not in other_table.columns:
            logger.error(f"Column '{other_col}' not found in table '{other_table.name}'")
            raise ValueError(f"Column '{other_col}' not found in table '{other_table.name}'")
        logger.debug(f"Validated join column '{other_col}' exists in table '{other_table.name}'")
        
        if join_type not in ["inner", "left", "right", "full"]:
            logger.error(f"Invalid join type: {join_type}. Must be one of: inner, left, right, full")
            raise ValueError(f"Invalid join type: {join_type}. Must be one of: inner, left, right, full")
        logger.debug(f"Validated join type: {join_type}")
        
        result = []
        
        # Create a map for efficient lookups in the other table
        logger.info(f"Creating lookup map for table '{other_table.name}' using column '{other_col}'")
        other_rows_map = {}
        for i, other_row in enumerate(other_table.rows):
            key = other_row[other_col]
            if key not in other_rows_map:
                other_rows_map[key] = []
            other_rows_map[key].append(other_row)
            logger.debug(f"Added row {i+1} from table '{other_table.name}' to map with key '{key}'")
        logger.info(f"Created lookup map with {len(other_rows_map)} unique keys from {len(other_table.rows)} rows")
        
        processed_other_keys = set()
        
        # Determine if it's a self-join
        is_self_join = self.name == other_table.name
        
        if join_type == "inner":
            logger.info(f"Processing {join_type} join")
            for i, self_row in enumerate(self.rows):
                key = self_row[self_col]
                logger.debug(f"Processing row {i+1} from table '{self.name}' with key '{key}'")
                if key in other_rows_map:
                    logger.debug(f"Found {len(other_rows_map[key])} matching rows in table '{other_table.name}' for key '{key}'")
                    for other_row in other_rows_map[key]:
                        combined = {}
                        for col, val in self_row.items():
                            combined[f"{self.name}.{col}"] = val
                        for col, val in other_row.items():
                            if is_self_join:
                                combined[f"{other_table.name}.{col}_1"] = val
                            else:
                                combined[f"{other_table.name}.{col}"] = val
                        result.append(combined)
                        processed_other_keys.add(key)
                        logger.debug(f"Combined rows and added to results: {combined}")
                else:
                    logger.debug(f"No matching rows in table '{other_table.name}' for key '{key}'")
        
        elif join_type == "left":
            logger.info(f"Processing {join_type} join")
            for i, self_row in enumerate(self.rows):
                key = self_row[self_col]
                logger.debug(f"Processing row {i+1} from table '{self.name}' with key '{key}'")
                if key in other_rows_map:
                    logger.debug(f"Found {len(other_rows_map[key])} matching rows in table '{other_table.name}' for key '{key}'")
                    for other_row in other_rows_map[key]:
                        combined = {}
                        for col, val in self_row.items():
                            combined[f"{self.name}.{col}"] = val
                        for col, val in other_row.items():
                            if is_self_join:
                                combined[f"{other_table.name}.{col}_1"] = val
                            else:
                                combined[f"{other_table.name}.{col}"] = val
                        result.append(combined)
                        processed_other_keys.add(key)
                        logger.debug(f"Combined rows and added to results: {combined}")
                else:
                    logger.debug(f"No matching rows in table '{other_table.name}' for key '{key}', adding with NULL values")
                    combined = {}
                    for col, val in self_row.items():
                        combined[f"{self.name}.{col}"] = val
                    for col in other_table.columns:
                        if is_self_join:
                            combined[f"{other_table.name}.{col}_1"] = None
                        else:
                            combined[f"{other_table.name}.{col}"] = None
                    result.append(combined)
                    logger.debug(f"Added row with NULL values for right table: {combined}")
        
        elif join_type == "right":
            logger.info(f"Processing {join_type} join")
            all_self_keys = {row[self_col] for row in self.rows}
            logger.debug(f"Collected {len(all_self_keys)} unique keys from table '{self.name}'")
            
            for i, other_row in enumerate(other_table.rows):
                key = other_row[other_col]
                logger.debug(f"Processing row {i+1} from table '{other_table.name}' with key '{key}'")
                if key in all_self_keys:
                    logger.debug(f"Found matching keys in table '{self.name}' for key '{key}'")
                    for self_row in self.rows:
                        if self_row[self_col] == key:
                            combined = {}
                            for col, val in self_row.items():
                                combined[f"{self.name}.{col}"] = val
                            for col, val in other_row.items():
                                if is_self_join:
                                    combined[f"{other_table.name}.{col}_1"] = val
                                else:
                                    combined[f"{other_table.name}.{col}"] = val
                            result.append(combined)
                            logger.debug(f"Combined rows and added to results: {combined}")
                else:
                    logger.debug(f"No matching rows in table '{self.name}' for key '{key}', adding with NULL values")
                    combined = {}
                    for col in self.columns:
                        combined[f"{self.name}.{col}"] = None
                    for col, val in other_row.items():
                        if is_self_join:
                            combined[f"{other_table.name}.{col}_1"] = val
                        else:
                            combined[f"{other_table.name}.{col}"] = val
                    result.append(combined)
                    logger.debug(f"Added row with NULL values for left table: {combined}")
        
        elif join_type == "full":
            logger.info(f"Processing {join_type} join")
            # Process all left join rows first
            logger.info("Step 1: Performing left join logic for full join")
            for i, self_row in enumerate(self.rows):
                key = self_row[self_col]
                logger.debug(f"Processing row {i+1} from table '{self.name}' with key '{key}'")
                if key in other_rows_map:
                    logger.debug(f"Found {len(other_rows_map[key])} matching rows in table '{other_table.name}' for key '{key}'")
                    for other_row in other_rows_map[key]:
                        combined = {}
                        for col, val in self_row.items():
                            combined[f"{self.name}.{col}"] = val
                        for col, val in other_row.items():
                            if is_self_join:
                                combined[f"{other_table.name}.{col}_1"] = val
                            else:
                                combined[f"{other_table.name}.{col}"] = val
                        result.append(combined)
                        processed_other_keys.add(key)
                        logger.debug(f"Combined rows and added to results: {combined}")
                else:
                    logger.debug(f"No matching rows in table '{other_table.name}' for key '{key}', adding with NULL values")
                    combined = {}
                    for col, val in self_row.items():
                        combined[f"{self.name}.{col}"] = val
                    for col in other_table.columns:
                        if is_self_join:
                            combined[f"{other_table.name}.{col}_1"] = None
                        else:
                            combined[f"{other_table.name}.{col}"] = None
                    result.append(combined)
                    logger.debug(f"Added row with NULL values for right table: {combined}")
            logger.info(f"Completed left join logic, results now have {len(result)} rows")
            
            # Process right join rows that weren't already included in left join
            logger.info("Step 2: Adding unmatched rows from right table")
            for i, other_row in enumerate(other_table.rows):
                key = other_row[other_col]
                logger.debug(f"Checking row {i+1} from table '{other_table.name}' with key '{key}'")
                if key not in processed_other_keys:
                    logger.debug(f"Key '{key}' not found in left join results, adding with NULL values")
                    combined = {}
                    for col in self.columns:
                        combined[f"{self.name}.{col}"] = None
                    for col, val in other_row.items():
                        if is_self_join:
                            combined[f"{other_table.name}.{col}_1"] = val
                        else:
                            combined[f"{other_table.name}.{col}"] = val
                    result.append(combined)
                    logger.debug(f"Added row with NULL values for left table: {combined}")
        
        logger.info(f"Join completed successfully")
        logger.info(f"Table '{self.name}' had {len(self.rows)} rows, table '{other_table.name}' had {len(other_table.rows)}")
        logger.info(f"Join result contains {len(result)} rows")
        
        return result

# Test code to demonstrate usage
def test_in_memory_db():
    print("=" * 80)
    print("IN-MEMORY DATABASE EXTENSIVE TEST SUITE")
    print("=" * 80)
    
    # Create database and tables
    db = Database()
    print("\n1. Database initialized")
    
    # Test 1: Create tables
    print("\n2. Creating tables...")
    db.create_table("users", ["id", "name", "age", "email", "is_active"])
    db.create_table("orders", ["id", "user_id", "product", "amount", "status"])
    db.create_table("products", ["id", "name", "category", "price", "in_stock"])
    db.create_table("empty_table", ["id", "name", "value"])
    print("   ✓ Tables created successfully")
    
    # Get tables
    users_table = db.get_table("users")
    orders_table = db.get_table("orders")
    products_table = db.get_table("products")
    empty_table = db.get_table("empty_table")
    
    # Test 2: Insert data with various data types
    print("\n3. Inserting data...")
    users_table.insert({"id": 1, "name": "Alice", "age": 28, "email": "alice@example.com", "is_active": True})
    users_table.insert({"id": 2, "name": "Bob", "age": 35, "email": "bob@example.com", "is_active": True})
    users_table.insert({"id": 3, "name": "Charlie", "age": 42, "email": "charlie@example.com", "is_active": False})
    users_table.insert({"id": 4, "name": "David", "age": 19, "email": "david@example.com", "is_active": True})
    users_table.insert({"id": 5, "name": "Eve", "age": 30, "email": "eve@example.com", "is_active": False})
    
    orders_table.insert({"id": 1, "user_id": 1, "product": "Laptop", "amount": 999.99, "status": "shipped"})
    orders_table.insert({"id": 2, "user_id": 1, "product": "Mouse", "amount": 29.99, "status": "delivered"})
    orders_table.insert({"id": 3, "user_id": 2, "product": "Keyboard", "amount": 49.99, "status": "shipped"})
    orders_table.insert({"id": 4, "user_id": 3, "product": "Monitor", "amount": 199.99, "status": "cancelled"})
    orders_table.insert({"id": 5, "user_id": 10, "product": "Headphones", "amount": 79.99, "status": "processing"})
    
    products_table.insert({"id": 1, "name": "Laptop", "category": "Electronics", "price": 999.99, "in_stock": 10})
    products_table.insert({"id": 2, "name": "Mouse", "category": "Electronics", "price": 29.99, "in_stock": 50})
    products_table.insert({"id": 3, "name": "Keyboard", "category": "Electronics", "price": 49.99, "in_stock": 0})
    products_table.insert({"id": 4, "name": "Monitor", "category": "Electronics", "price": 199.99, "in_stock": 20})
    products_table.insert({"id": 5, "name": "Headphones", "category": "Audio", "price": 79.99, "in_stock": 35})
    products_table.insert({"id": 6, "name": "Speakers", "category": "Audio", "price": 129.99, "in_stock": 15})
    print("   ✓ Data inserted successfully")
    
    # Test 3: Select all from tables
    print("\n4. Testing SELECT ALL operations:")
    print("   a. All users:")
    all_users = users_table.select()
    for user in all_users:
        print(f"      {user}")
    
    print("\n   b. All products:")
    all_products = products_table.select()
    for product in all_products:
        print(f"      {product}")
    
    # Test 4: Select with specific columns
    print("\n5. Testing SELECT with specific columns:")
    print("   a. Users - only name and email:")
    users_name_email = users_table.select(columns=["name", "email"])
    for user in users_name_email:
        print(f"      {user}")
    
    print("\n   b. Products - name, category, and price:")
    products_basic = products_table.select(columns=["name", "category", "price"])
    for product in products_basic:
        print(f"      {product}")
    
    # Test 5: Select with various where clause operators
    print("\n6. Testing SELECT with WHERE clauses:")
    
    print("   a. Users older than 30:")
    users_over_30 = users_table.select(where={"age": (">", 30)})
    for user in users_over_30:
        print(f"      {user}")
    
    print("\n   b. Users 30 or younger:")
    users_30_or_younger = users_table.select(where={"age": ("<=", 30)})
    for user in users_30_or_younger:
        print(f"      {user}")
    
    print("\n   c. Active users:")
    active_users = users_table.select(where={"is_active": True})
    for user in active_users:
        print(f"      {user}")
    
    print("\n   d. Products in Electronics category:")
    electronics = products_table.select(where={"category": "Electronics"})
    for product in electronics:
        print(f"      {product}")
    
    print("\n   e. Products with price between 50 and 200:")
    mid_price_products = products_table.select(where=[("price", ">", 50), ("price", "<=", 200)])
    for product in mid_price_products:
        print(f"      {product}")
    
    print("\n   f. Orders with 'shipped' status:")
    shipped_orders = orders_table.select(where={"status": "shipped"})
    for order in shipped_orders:
        print(f"      {order}")
    
    print("\n   g. Products out of stock:")
    out_of_stock = products_table.select(where={"in_stock": 0})
    for product in out_of_stock:
        print(f"      {product}")

    print("\n   h. Users with email containing 'example':")
    users_with_example = users_table.select(where={"email": ("contains", "example")})
    for user in users_with_example:
        print(f"      {user}")

    print("\n   i. Products with name containing 'o':")
    products_with_o = products_table.select(where={"name": ("contains", "o")})
    for product in products_with_o:
        print(f"      {product}")

    print("\n   j. Orders with status containing 'ed':")
    orders_with_ed = orders_table.select(where={"status": ("contains", "ed")})
    for order in orders_with_ed:
        print(f"      {order}")

    # Test 5b: Edge cases for 'contains' operator
    print("\n   l. Edge cases for 'contains' operator:")
    print("      i. Contains empty string (should match all non-NULL values):")
    try:
        all_non_null_email = users_table.select(where={"email": ("contains", "")})
        for user in all_non_null_email:
            print(f"         {user}")
    except Exception as e:
        print(f"         Error: {type(e).__name__}: {e}")
    
    print("      ii. Contains None value (should fail or return empty):")
    try:
        contains_none = users_table.select(where={"email": ("contains", None)})
        print(f"         Result: {contains_none}")
    except Exception as e:
        print(f"         Error: {type(e).__name__}: {e}")
    
    print("      iii. Contains special characters:")
    try:
        # Create a test case with special characters
        db.create_table("special_chars", ["id", "text"])  
        special_chars_table = db.get_table("special_chars")
        special_chars_table.insert({"id": 1, "text": "test@example.com"})
        special_chars_table.insert({"id": 2, "text": "test#example.com"})
        special_chars_table.insert({"id": 3, "text": "test$example.com"})
        special_chars_table.insert({"id": 4, "text": "test%example.com"})
        
        print("         Contains '@':")
        contains_at = special_chars_table.select(where={"text": ("contains", "@")})
        for item in contains_at:
            print(f"            {item}")
        
        print("         Contains '#':")
        contains_hash = special_chars_table.select(where={"text": ("contains", "#")})
        for item in contains_hash:
            print(f"            {item}")
        
        print("         Contains '$':")
        contains_dollar = special_chars_table.select(where={"text": ("contains", "$")})
        for item in contains_dollar:
            print(f"            {item}")
    except Exception as e:
        print(f"         Error: {type(e).__name__}: {e}")

    # Test 5a: Complex where clause combinations
    print("\n   k. Complex where clauses:")
    print("      i. Active users older than 25:")
    active_older_users = users_table.select(where={"is_active": True, "age": (">", 25)})
    for user in active_older_users:
        print(f"         {user}")

    print("      ii. Electronics products in stock:")
    electronics_in_stock = products_table.select(where={"category": "Electronics", "in_stock": (">", 0)})
    for product in electronics_in_stock:
        print(f"         {product}")

    print("      iii. Orders with amount > 50 and status not cancelled:")
    # Note: This demonstrates current limitation - only last condition is applied
    large_orders = orders_table.select(where={"amount": (">", 50), "status": ("!=", "cancelled")})
    for order in large_orders:
        print(f"         {order}")

    # Test 6: All join types
    print("\n7. Testing JOIN operations:")
    
    print("   a. Inner join users and orders:")
    inner_join = users_table.join(orders_table, on=("id", "user_id"), join_type="inner")
    for row in inner_join:
        print(f"      {row}")
    
    print("\n   b. Left join users and orders:")
    left_join = users_table.join(orders_table, on=("id", "user_id"), join_type="left")
    for row in left_join:
        print(f"      {row}")
    
    print("\n   c. Right join users and orders:")
    right_join = users_table.join(orders_table, on=("id", "user_id"), join_type="right")
    for row in right_join:
        print(f"      {row}")
    
    print("\n   d. Full join users and orders:")
    full_join = users_table.join(orders_table, on=("id", "user_id"), join_type="full")
    for row in full_join:
        print(f"      {row}")
    
    # Test 7: Complex joins
    print("\n8. Testing complex joins:")
    print("   a. Join orders with products (match order.product to product.name):")
    order_product_join = orders_table.join(products_table, on=("product", "name"), join_type="inner")
    for row in order_product_join:
        print(f"      {row}")

    # Test 7a: JOIN operations with duplicate keys
    print("\n   b. Join with duplicate keys:")
    print("      Creating order_items table with duplicate product_id values...")
    db.create_table("order_items", ["id", "order_id", "product_id", "quantity"])
    order_items_table = db.get_table("order_items")
    order_items_table.insert({"id": 1, "order_id": 1, "product_id": 1, "quantity": 2})
    order_items_table.insert({"id": 2, "order_id": 1, "product_id": 2, "quantity": 1})
    order_items_table.insert({"id": 3, "order_id": 2, "product_id": 1, "quantity": 1})
    order_items_table.insert({"id": 4, "order_id": 3, "product_id": 3, "quantity": 3})
    order_items_table.insert({"id": 5, "order_id": 4, "product_id": 1, "quantity": 1})
    
    print("      i. Inner join products with duplicate order_items:")
    product_order_items_join = products_table.join(order_items_table, on=("id", "product_id"), join_type="inner")
    for row in product_order_items_join:
        print(f"         {row}")

    print("      ii. Left join products with duplicate order_items:")
    product_order_items_left = products_table.join(order_items_table, on=("id", "product_id"), join_type="left")
    for row in product_order_items_left:
        print(f"         {row}")

    # Test 7b: Self-join operations
    print("\n   c. Self-join operations:")
    print("      Creating employees table for self-join testing...")
    db.create_table("employees", ["id", "name", "department", "manager_id"])
    employees_table = db.get_table("employees")
    employees_table.insert({"id": 1, "name": "Alice", "department": "Management", "manager_id": None})
    employees_table.insert({"id": 2, "name": "Bob", "department": "Engineering", "manager_id": 1})
    employees_table.insert({"id": 3, "name": "Charlie", "department": "Engineering", "manager_id": 2})
    employees_table.insert({"id": 4, "name": "David", "department": "Sales", "manager_id": 1})
    employees_table.insert({"id": 5, "name": "Eve", "department": "Sales", "manager_id": 4})
    employees_table.insert({"id": 6, "name": "Frank", "department": "Engineering", "manager_id": 2})
    
    print("      i. Inner self-join to show employee-manager relationships:")
    employee_manager_join = employees_table.join(employees_table, on=("id", "manager_id"), join_type="inner")
    for row in employee_manager_join:
        employee_name = row["employees.name"]
        manager_name = row["employees.name_1"]
        print(f"         {employee_name} -> Managed by -> {manager_name}")

    print("      ii. Left self-join to show all employees including those without managers:")
    employee_manager_left = employees_table.join(employees_table, on=("id", "manager_id"), join_type="left")
    for row in employee_manager_left:
        employee_name = row["employees.name"]
        manager_name = row["employees.name_1"] if row["employees.name_1"] else "(No Manager)"
        print(f"         {employee_name} -> Managed by -> {manager_name}")
    
    print("\n      iii. Complex self-join with additional conditions:")
    print("         Employees in the same department as their manager:")
    same_dept_employees = employees_table.join(employees_table, on=("id", "manager_id"), join_type="inner")
    # Filter the join results to show only employees in the same department as their manager
    same_dept_result = []
    for row in same_dept_employees:
        if row["employees.department"] == row["employees.department_1"]:
            same_dept_result.append({
                "Employee": row["employees.name"],
                "Manager": row["employees.name_1"],
                "Department": row["employees.department"]
            })
    for emp in same_dept_result:
        print(f"         {emp['Employee']} (Manager: {emp['Manager']}) in {emp['Department']} department")
    
    print("\n      iv. Self-join to find all employees under a specific manager:")
    print("         Employees managed by Bob:")
    bob_managed_employees = employees_table.join(employees_table, on=("id", "manager_id"), join_type="inner")
    bob_team = []
    for row in bob_managed_employees:
        if row["employees.name_1"] == "Bob":
            bob_team.append(row["employees.name"])
    if bob_team:
        for emp in bob_team:
            print(f"         - {emp}")
    else:
        print("         No employees found directly managed by Bob")

    # Test 7c: Multiple chained joins
    print("\n   d. Multiple chained joins:")
    print("      Demonstrating chained joins: users -> orders -> order_items -> products")
    
    print("      i. First join: users and orders:")
    users_orders = users_table.join(orders_table, on=("id", "user_id"), join_type="inner")
    for row in users_orders:
        print(f"         {row['users.name']} ordered {row['orders.product']}")
    
    print("\n      ii. Second join: users_orders and order_items (simulated):")
    # Note: For true chained joins, we'd need to create a new table from the first join result
    # Here we'll show a two-step process by joining users to order_items via orders
    users_items = users_table.join(orders_table, on=("id", "user_id"), join_type="inner")
    # Then we'd need to create a temporary table from users_items and join with order_items
    print("         (Current implementation doesn't support direct table creation from join results)")

    # Test 8: Operations on empty table
    print("\n9. Testing operations on empty table:")
    print("   a. Select from empty table:")
    empty_result = empty_table.select()
    print(f"      Result: {empty_result} (expected: empty list)")
    
    print("   b. Join empty table with users:")
    empty_join = empty_table.join(users_table, on=("id", "id"), join_type="inner")
    print(f"      Result: {empty_join} (expected: empty list)")

    # Test 8a: JOIN operations with None values
    print("\n10. Testing JOIN operations with None values:")
    print("    Creating reviews table with some NULL product_id values...")
    db.create_table("reviews", ["id", "user_id", "product_id", "rating", "comment"])
    reviews_table = db.get_table("reviews")
    reviews_table.insert({"id": 1, "user_id": 1, "product_id": 1, "rating": 5, "comment": "Excellent!"})
    reviews_table.insert({"id": 2, "user_id": 2, "product_id": None, "rating": 3, "comment": "Not specified"})
    reviews_table.insert({"id": 3, "user_id": 1, "product_id": 2, "rating": 4, "comment": "Good product"})
    reviews_table.insert({"id": 4, "user_id": 3, "product_id": None, "rating": 2, "comment": "Disappointed"})
    reviews_table.insert({"id": 5, "user_id": 4, "product_id": 3, "rating": 4, "comment": "Great value"})
    
    print("    a. Inner join products with reviews (should exclude NULL product_id):")
    product_reviews_inner = products_table.join(reviews_table, on=("id", "product_id"), join_type="inner")
    for row in product_reviews_inner:
        print(f"       {row}")
    
    print("    b. Left join products with reviews:")
    product_reviews_left = products_table.join(reviews_table, on=("id", "product_id"), join_type="left")
    for row in product_reviews_left:
        print(f"       {row}")
    
    print("    c. Right join products with reviews (should include NULL product_id):")
    product_reviews_right = products_table.join(reviews_table, on=("id", "product_id"), join_type="right")
    for row in product_reviews_right:
        print(f"       {row}")
    
    print("    d. Full join products with reviews:")
    product_reviews_full = products_table.join(reviews_table, on=("id", "product_id"), join_type="full")
    for row in product_reviews_full:
        print(f"       {row}")
    
    # Test 9: Diverse data types
    print("\n11. Testing diverse data types:")
    print("    Creating test_data table with various data types...")
    db.create_table("test_data", ["id", "name", "numeric_value", "text_field", "is_flag", "optional_field"])
    test_data_table = db.get_table("test_data")
    
    # Insert diverse data
    test_data_table.insert({"id": 1, "name": "Item 1", "numeric_value": 100, "text_field": "Hello", "is_flag": True, "optional_field": "value"})
    test_data_table.insert({"id": 2, "name": "Item 2", "numeric_value": -50, "text_field": "", "is_flag": False, "optional_field": None})
    test_data_table.insert({"id": 3, "name": "Item 3", "numeric_value": 0, "text_field": "   ", "is_flag": True, "optional_field": ""})
    test_data_table.insert({"id": 4, "name": "Item 4", "numeric_value": 999999999, "text_field": "Long text field with spaces and special characters!@#$%^&*()", "is_flag": False, "optional_field": "special"})
    test_data_table.insert({"id": 5, "name": "Item 5", "numeric_value": 3.14159, "text_field": "Multi\nline\ntext", "is_flag": True, "optional_field": None})
    
    print("    a. Select all diverse data:")
    all_test_data = test_data_table.select()
    for row in all_test_data:
        print(f"       {row}")
    
    print("    b. Select with None values:")
    none_values = test_data_table.select(where={"optional_field": None})
    for row in none_values:
        print(f"       {row}")
    
    print("    c. Select with empty strings:")
    empty_strings = test_data_table.select(where={"text_field": ""})
    for row in empty_strings:
        print(f"       {row}")
    
    print("    d. Select with very large numbers:")
    large_numbers = test_data_table.select(where={"numeric_value": (">", 1000000)})
    for row in large_numbers:
        print(f"       {row}")
    
    print("    e. Select with float values:")
    float_values = test_data_table.select(where={"numeric_value": ("<=", 3.14159)})
    for row in float_values:
        print(f"       {row}")

    # Test 9c: More complex data types - dates, tuples, nested structures
    print("\n11.2. Testing more complex data types:")
    print("    Creating complex_data table with dates, tuples, and nested structures...")
    db.create_table("complex_data", ["id", "name", "date_field", "tuple_field", "nested_field"])
    complex_data_table = db.get_table("complex_data")
    
    # Insert data with complex types
    complex_data_table.insert({"id": 1, "name": "Event 1", "date_field": datetime(2023, 1, 1), "tuple_field": (1, "a"), "nested_field": {"key": "value1"}})
    complex_data_table.insert({"id": 2, "name": "Event 2", "date_field": datetime(2023, 6, 15), "tuple_field": (2, "b"), "nested_field": {"key": "value2", "subkey": "subvalue2"}})
    complex_data_table.insert({"id": 3, "name": "Event 3", "date_field": datetime(2023, 12, 31), "tuple_field": (3, "c"), "nested_field": {"key": "value3", "items": [1, 2, 3]}})
    complex_data_table.insert({"id": 4, "name": "Event 4", "date_field": datetime(2024, 1, 1), "tuple_field": (4, "d"), "nested_field": None})
    complex_data_table.insert({"id": 5, "name": "Event 5", "date_field": None, "tuple_field": None, "nested_field": {"key": "empty"}})
    
    print("    a. Select all complex data:")
    all_complex_data = complex_data_table.select()
    for row in all_complex_data:
        print(f"       {row}")
    
    print("    b. Select with date comparisons:")
    print("       i. Events after 2023-06-01:")
    try:
        events_after_june = complex_data_table.select(where={"date_field": (">", datetime(2023, 6, 1))})
        for row in events_after_june:
            print(f"          {row['name']} - {row['date_field']}")
    except Exception as e:
        print(f"          Error: {type(e).__name__}: {e}")
    
    print("       ii. Events between 2023-01-01 and 2023-12-31:")
    try:
        events_2023 = complex_data_table.select(where=[("date_field", ">=", datetime(2023, 1, 1)), ("date_field", "<", datetime(2024, 1, 1))])
        for row in events_2023:
            print(f"          {row['name']} - {row['date_field']}")
    except Exception as e:
        print(f"          Error: {type(e).__name__}: {e}")
    
    print("    c. Select with NULL date values:")
    null_dates = complex_data_table.select(where={"date_field": None})
    for row in null_dates:
        print(f"       {row}")
    
    print("    d. Select with tuple comparisons:")
    try:
        print("       i. Exact tuple match:")
        tuple_match = complex_data_table.select(where={"tuple_field": (2, "b")})
        for row in tuple_match:
            print(f"          {row}")
        
        print("       ii. Tuple comparison (greater than):")
        tuple_gt = complex_data_table.select(where={"tuple_field": (">", (2, "b"))})
        for row in tuple_gt:
            print(f"          {row}")
    except Exception as e:
        print(f"          Error: {type(e).__name__}: {e}")
    
    print("    e. Select with NULL tuple values:")
    null_tuples = complex_data_table.select(where={"tuple_field": None})
    for row in null_tuples:
        print(f"       {row}")
    
    print("    f. Select with nested structure considerations:")
    print("       i. All rows with non-NULL nested fields:")
    non_null_nested = complex_data_table.select(where={"nested_field": ("!=", None)})
    for row in non_null_nested:
        print(f"          {row['name']} - {type(row['nested_field']).__name__}: {row['nested_field']}")

    # Test 9d: Different column configurations
    print("\n11.3. Testing different column configurations:")
    
    print("    a. Single column table:")
    print("       Creating single_col_table with just an 'id' column...")
    db.create_table("single_col_table", ["id"])
    single_col_table = db.get_table("single_col_table")
    
    # Insert data into single column table
    single_col_table.insert({"id": 1})
    single_col_table.insert({"id": 2})
    single_col_table.insert({"id": 3})
    single_col_table.insert({"id": 4})
    single_col_table.insert({"id": 5})
    
    print("       Select all from single column table:")
    all_single_col = single_col_table.select()
    for row in all_single_col:
        print(f"          {row}")
    
    print("       Select with where clause on single column:")
    single_col_filtered = single_col_table.select(where={"id": (">", 2)})
    for row in single_col_filtered:
        print(f"          {row}")
    
    print("\n    b. Table with many columns:")
    print("       Creating many_cols_table with 10+ columns...")
    many_cols = [f"col_{i}" for i in range(1, 12)]  # col_1 to col_11
    db.create_table("many_cols_table", many_cols)
    many_cols_table = db.get_table("many_cols_table")
    
    # Insert data with many columns
    many_cols_row1 = {f"col_{i}": f"value_{i}_1" for i in range(1, 12)}
    many_cols_row1["col_1"] = 1
    many_cols_row1["col_5"] = None
    many_cols_row1["col_10"] = 100
    
    many_cols_row2 = {f"col_{i}": f"value_{i}_2" for i in range(1, 12)}
    many_cols_row2["col_1"] = 2
    many_cols_row2["col_5"] = "special_value"
    many_cols_row2["col_10"] = 200
    
    many_cols_row3 = {f"col_{i}": f"value_{i}_3" for i in range(1, 12)}
    many_cols_row3["col_1"] = 3
    many_cols_row3["col_5"] = None
    many_cols_row3["col_10"] = 300
    
    many_cols_table.insert(many_cols_row1)
    many_cols_table.insert(many_cols_row2)
    many_cols_table.insert(many_cols_row3)
    
    print("       Select specific columns from many-columns table:")
    specific_cols = many_cols_table.select(columns=["col_1", "col_5", "col_10"])
    for row in specific_cols:
        print(f"          {row}")
    
    print("       Select with where clause on specific column:")
    many_cols_filtered = many_cols_table.select(columns=["col_1", "col_2", "col_5"], where={"col_10": (">", 150)})
    for row in many_cols_filtered:
        print(f"          {row}")
    
    print("\n    c. Table with very long column names:")
    print("       Creating long_names_table with descriptive column names...")
    long_cols = [
        "this_is_a_very_long_column_name_for_testing",
        "another_extremely_long_column_name_that_goes_on",
        "short_col",
        "column_with_underscores_and_numbers_12345",
        "final_very_long_column_name_for_verification"
    ]
    db.create_table("long_names_table", long_cols)
    long_names_table = db.get_table("long_names_table")
    
    # Insert data with long column names
    long_names_table.insert({
        "this_is_a_very_long_column_name_for_testing": "value1",
        "another_extremely_long_column_name_that_goes_on": "value2",
        "short_col": 42,
        "column_with_underscores_and_numbers_12345": "test",
        "final_very_long_column_name_for_verification": "last_value"
    })
    
    print("       Select with long column names:")
    long_names_result = long_names_table.select(columns=["this_is_a_very_long_column_name_for_testing", "short_col"])
    for row in long_names_result:
        print(f"          {row}")

    # Test 9b: Comprehensive NULL value behavior
    print("\n11.5. Testing NULL value behavior in various contexts:")
    print("    Creating null_test table with NULL values in different contexts...")
    db.create_table("null_test", ["id", "name", "value1", "value2", "text_field"])
    null_test_table = db.get_table("null_test")
    null_test_table.insert({"id": 1, "name": "Item 1", "value1": 10, "value2": None, "text_field": "test"})
    null_test_table.insert({"id": 2, "name": "Item 2", "value1": None, "value2": 20, "text_field": None})
    null_test_table.insert({"id": 3, "name": "Item 3", "value1": None, "value2": None, "text_field": "contains None"})
    null_test_table.insert({"id": 4, "name": "Item 4", "value1": 40, "value2": 40, "text_field": ""})
    null_test_table.insert({"id": 5, "name": "Item 5", "value1": 50, "value2": 60, "text_field": "no null here"})
    
    print("    a. NULL values in comparisons:")
    try:
        print("       i. Where value1 > 30 (should include Item 1, 4, 5):")
        gt_30 = null_test_table.select(where={"value1": (">", 30)})
        for row in gt_30:
            print(f"          {row}")
    except Exception as e:
        print(f"          Error: {type(e).__name__}: {e}")
    
    try:
        print("       ii. Where value1 == None (should include Item 2, 3):")
        null_value1 = null_test_table.select(where={"value1": None})
        for row in null_value1:
            print(f"          {row}")
    except Exception as e:
        print(f"          Error: {type(e).__name__}: {e}")
    
    try:
        print("       iii. Where value1 != None (should include Item 1, 4, 5):")
        not_null_value1 = null_test_table.select(where={"value1": ("!=", None)})
        for row in not_null_value1:
            print(f"          {row}")
    except Exception as e:
        print(f"          Error: {type(e).__name__}: {e}")
    
    print("    b. NULL values with 'contains' operator:")
    try:
        print("       i. Where text_field contains 'test' (should include Item 1):")
        contains_test = null_test_table.select(where={"text_field": ("contains", "test")})
        for row in contains_test:
            print(f"          {row}")
    except Exception as e:
        print(f"          Error: {type(e).__name__}: {e}")
    
    try:
        print("       ii. Where text_field contains 'None' (should include Item 3):")
        contains_none = null_test_table.select(where={"text_field": ("contains", "None")})
        for row in contains_none:
            print(f"          {row}")
    except Exception as e:
        print(f"          Error: {type(e).__name__}: {e}")
    
    print("    c. Complex where clauses with NULL values:")
    try:
        print("       i. Where value1 > 30 AND value2 != None:")
        complex_null = null_test_table.select(where={"value1": (">", 30), "value2": ("!=", None)})
        for row in complex_null:
            print(f"          {row}")
    except Exception as e:
        print(f"          Error: {type(e).__name__}: {e}")

    # Test 10a: Select with no matching rows
    print("\n11.6. Testing select operations with no matching rows:")
    
    print("    a. No matches in users table:")
    print("       i. Users with age > 100 (should be empty):")
    no_old_users = users_table.select(where={"age": (">", 100)})
    print(f"          Result: {no_old_users} (expected: empty list)")
    
    print("       ii. Users with name 'Nonexistent User' (should be empty):")
    no_nonexistent = users_table.select(where={"name": "Nonexistent User"})
    print(f"          Result: {no_nonexistent} (expected: empty list)")
    
    print("       iii. Users with email containing 'nonexistentdomain' (should be empty):")
    no_nonexistent_email = users_table.select(where={"email": ("contains", "nonexistentdomain")})
    print(f"          Result: {no_nonexistent_email} (expected: empty list)")
    
    print("    b. No matches in products table:")
    print("       i. Products with price < 0 (should be empty):")
    no_negative_price = products_table.select(where={"price": ("<", 0)})
    print(f"          Result: {no_negative_price} (expected: empty list)")
    
    print("       ii. Products in category 'NonexistentCategory' (should be empty):")
    no_nonexistent_category = products_table.select(where={"category": "NonexistentCategory"})
    print(f"          Result: {no_nonexistent_category} (expected: empty list)")
    
    print("    c. No matches in diverse data table:")
    print("       i. Items with numeric_value = 999999998 (should be empty):")
    no_specific_large_number = test_data_table.select(where={"numeric_value": 999999998})
    print(f"          Result: {no_specific_large_number} (expected: empty list)")
    
    print("       ii. Items with text_field containing 'nonexistenttext' (should be empty):")
    no_nonexistent_text = test_data_table.select(where={"text_field": ("contains", "nonexistenttext")})
    print(f"          Result: {no_nonexistent_text} (expected: empty list)")

    # Test 10: Error handling scenarios
    print("\n12. Testing error handling:")
    print("    Note: Error messages should appear in logs below")
    
    try:
        # Test duplicate table creation
        db.create_table("users", ["id", "name", "age"])
        print("    ❌ Failed: Duplicate table creation should have failed")
    except ValueError:
        print("    ✓ Passed: Duplicate table creation correctly failed")
    
    try:
        # Test missing required column in insert
        users_table.insert({"id": 6, "name": "Frank", "age": 25})
        print("    ❌ Failed: Missing required column insert should have failed")
    except ValueError:
        print("    ✓ Passed: Missing required column insert correctly failed")
    
    try:
        # Test invalid column in select
        users_table.select(columns=["name", "invalid_column"])
        print("    ❌ Failed: Invalid column in select should have failed")
    except ValueError:
        print("    ✓ Passed: Invalid column in select correctly failed")
    
    try:
        # Test invalid column in where clause
        users_table.select(where={"invalid_column": 5})
        print("    ❌ Failed: Invalid column in where clause should have failed")
    except ValueError:
        print("    ✓ Passed: Invalid column in where clause correctly failed")
    
    try:
        # Test invalid join type
        users_table.join(orders_table, on=("id", "user_id"), join_type="invalid")
        print("    ❌ Failed: Invalid join type should have failed")
    except ValueError:
        print("    ✓ Passed: Invalid join type correctly failed")

    try:
        # Test unsupported where clause operator
        users_table.select(where={"age": ("<>", 25)})
        print("    ❌ Failed: Unsupported operator should have failed")
    except ValueError:
        print("    ✓ Passed: Unsupported operator correctly failed")

    try:
        # Test wrong data type in where clause comparison
        users_table.select(where={"age": ("<", "25")})
        print("    ❌ Failed: Wrong data type comparison should have failed")
    except Exception as e:
        print(f"    ✓ Passed: Wrong data type comparison correctly failed with: {type(e).__name__}")

    try:
        # Test join on non-existent column in first table
        users_table.join(orders_table, on=("non_existent_col", "user_id"), join_type="inner")
        print("    ❌ Failed: Join on non-existent column in first table should have failed")
    except ValueError:
        print("    ✓ Passed: Join on non-existent column in first table correctly failed")

    try:
        # Test join on non-existent column in second table
        users_table.join(orders_table, on=("id", "non_existent_col"), join_type="inner")
        print("    ❌ Failed: Join on non-existent column in second table should have failed")
    except ValueError:
        print("    ✓ Passed: Join on non-existent column in second table correctly failed")

    try:
        # Test drop non-existent table
        db.drop_table("non_existent_table")
        print("    ❌ Failed: Drop non-existent table should have failed")
    except ValueError:
        print("    ✓ Passed: Drop non-existent table correctly failed")

    try:
        # Test get non-existent table
        db.get_table("non_existent_table")
        print("    ❌ Failed: Get non-existent table should have failed")
    except ValueError:
        print("    ✓ Passed: Get non-existent table correctly failed")
    
    # Test 10: Table management
    print("\n13. Testing table management:")
    print("    a. Dropping empty table:")
    db.drop_table("empty_table")
    print("       ✓ Table 'empty_table' dropped successfully")
    
    try:
        # Test accessing dropped table
        db.get_table("empty_table")
        print("    ❌ Failed: Accessing dropped table should have failed")
    except ValueError:
        print("    ✓ Passed: Accessing dropped table correctly failed")
    
    # Test 11: Final state verification
    print("\n14. Final database state:")
    print("    Tables remaining:", list(db.tables.keys()))
    
    print("\n" + "=" * 80)
    print("TEST SUITE COMPLETED")
    print("=" * 80)

if __name__ == "__main__":
    test_in_memory_db()