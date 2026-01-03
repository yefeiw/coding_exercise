import logging
from datetime import datetime
from typing import Any

# Configure logging
logging.basicConfig(level=logging.DEBUG, format='%(asctime)s - %(levelname)s - %(message)s')
logger = logging.getLogger(__name__)

class Database:
    ## The database consists of several tables. The table object should be a map of {name -> table}
    def __init__(self):
        # TODO: Initialize database storage for tables
        self.tables = {}
        pass
    
    def create_table(self, name: str, columns: list[str]) -> None:
        # TODO: Create a new table with given name and columns
        # Validate that table doesn't already exist
        if name in self.tables:
            logger.error(f"Table name {name} exists")
            raise ValueError(f"Table name {name} exists")
        # Create and store the table
        self.tables[name] = Table(name, columns)
        logger.info(f"Table {name} created")
    
    def drop_table(self, name: str) -> None:
        # TODO: Drop a table by name
        # Validate that table exists
        if name not in self.tables:
            logger.error(f"Table name {name} does not exist")
            raise ValueError(f"Table name {name} does not exist")
        # Remove the table from storage
        del self.tables[name]
        logger.info(f"Table {name} dropped")
    def get_table(self, name: str) -> 'Table':
        # TODO: Get a table by name
        # Validate that table exists
        if name not in self.tables:
            logger.error(f"Table {name} does not exist")
            raise ValueError(f"Table {name} does not exist")
        # Return the table instance
        return self.tables[name]
class Table:
    """
        This table data structure resembles a csv structure, name, columns and rows
    """
    def __init__(self, name: str, columns: list[str]):
        # TODO: Initialize table with name and columns
        # Set up storage for rows
        self.name = name
        self.columns = columns
        self.rows = []
    
    def insert(self, row: dict[str, any]) -> None:
        # TODO: Insert a new row into the table
        # Validate that all required columns are present
        missing_col = [col for col in self.columns if col not in row]
        if missing_col:
            raise ValueError(f"{missing_col} columns are missing")
        # Add the row to storage
        filtered_row = {col : row[col] for col in self.columns}
        self.rows.append(filtered_row)
        logger.info(f"Inserted row {filtered_row}")
    
    def select(self, columns: list[str] = None, where: list[tuple] = None) -> list[dict[str, any]]:
        # TODO: Select rows from the table
        # Filter columns if specified
        if columns is None:
            columns = self.columns
        else:
            missing_columns = [col for col in columns if col not in self.columns]
            if missing_columns:
                raise ValueError(f"{missing_columns} columns are missing")
    
        # Apply where clause if specified
        where_conditions = []
        if where:
            for condition in where:
                if len(condition) != 3:
                    raise ValueError(f"Where clause condition {condition} is invalid")
                col, op, val = condition
                if col not in self.columns:
                    raise ValueError(f"Column {col} in where clause condition {condition} is not in table {self.name} {self.columns}")
                where_conditions.append((col, op, val))
        # Return the matching rows
        results = []
        for row in self.rows:
            for col, op, val in where_conditions:
                if op == "=" and row[col] != val:
                    break
                elif op == ">" and row[col] <= val:
                    break
                elif op == "<" and row[col] >= val:
                    break
            else:
                results.append({col : row[col] for col in columns})
        return results
    
    def join(self, other_table: 'Table', on: tuple[str, str], join_type: str = "inner") -> list[dict[str, Any]]:
        # TODO: Join this table with another table
        # Support inner, left, right, full joins
        # Handle column name conflicts
        if other_table == self:
            raise ValueError(f"Cannot join table {self.name} with itself")
        on_col, other_col = on
        if on_col not in self.columns or other_col not in other_table.columns:
            raise ValueError(f"Join columns {on} are not in tables {self.name} and {other_table.name}")
        results = []
        for row in self.rows:
            for other_row in other_table.rows:
                if row[on_col] == other_row[other_col]:
                    # Match found, create joined row
                    joined_row = {**row, **{f"{other_table.name}_{col}": other_row[col] for col in other_table.columns}}
                    results.append(joined_row)
        # Return the joined results
        return results

# Test code to demonstrate usage
def test_in_memory_db():
    print("=" * 80)
    print("IN-MEMORY DATABASE PRACTICE TEST")
    print("=" * 80)
    
    # Create database and tables
    db = Database()
    print("\n1. Database initialized")
    
    # Test 1: Create tables
    print("\n2. Creating tables...")
    db.create_table("users", ["id", "name", "age", "email", "is_active"])
    db.create_table("orders", ["id", "user_id", "product", "amount", "status"])
    db.create_table("products", ["id", "name", "category", "price", "in_stock"])
    print("   ✓ Tables created successfully")
    
    # Get tables
    users_table = db.get_table("users")
    orders_table = db.get_table("orders")
    products_table = db.get_table("products")
    
    # Test 2: Insert data
    print("\n3. Inserting data...")
    users_table.insert({"id": 1, "name": "Alice", "age": 28, "email": "alice@example.com", "is_active": True})
    users_table.insert({"id": 2, "name": "Bob", "age": 35, "email": "bob@example.com", "is_active": True})
    
    orders_table.insert({"id": 1, "user_id": 1, "product": "Laptop", "amount": 999.99, "status": "shipped"})
    orders_table.insert({"id": 2, "user_id": 1, "product": "Mouse", "amount": 29.99, "status": "delivered"})
    
    products_table.insert({"id": 1, "name": "Laptop", "category": "Electronics", "price": 999.99, "in_stock": 10})
    products_table.insert({"id": 2, "name": "Mouse", "category": "Electronics", "price": 29.99, "in_stock": 50})
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
    
    # Test 4: Select with where clause
    print("\n5. Testing SELECT with WHERE clause:")
    print("   a. Users older than 30:")
    old_users = users_table.select(where=[("age", ">", 30)])
    for user in old_users:
        print(f"      {user}")
    
    # Test 5: Join tables
    print("\n6. Testing JOIN operations:")
    print("   a. Inner join users and orders:")
    user_orders = users_table.join(orders_table, ("id", "user_id"))
    for order in user_orders:
        print(f"      {order}")

if __name__ == "__main__":
    test_in_memory_db()
