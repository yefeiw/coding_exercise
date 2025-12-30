import bisect
class AccountBalance:
    """Account balance management class"""
    
    def __init__(self):
        """Initialize the account management class"""
        # Keep track of the grants in ascending order of expiration_time
        self.grants = []
        # Keep track of the spends in ascending order of timestamp  
        self.spends = []
    
    def addGrant(self, amount: float, expiration_time: int):
        """Add a grant.
        
        Args:
            amount: Grant amount, must be positive
            expiration_time: Expiration timestamp (Unix timestamp, integer)
            
        Raises:
            ValueError: If amount is not positive
        """
        if amount <= 0:
            raise ValueError(f"The amount {amount} is not positive")
        position = bisect.bisect_left(self.grants, expiration_time, key=lambda x: x[1])
        self.grants.insert(position, (amount, expiration_time))
        
    
    def addSpend(self, amount: float, timestamp: int):
        """Add a spend transaction.
        
        Args:
            amount: Spend amount, must be positive
            timestamp: Spend timestamp (Unix timestamp, integer)
            
        Raises:
            ValueError: If amount is not positive
        """
        if amount <= 0:
            raise ValueError(f"The amount {amount} is not positive")
        position = bisect.bisect_left(self.spends, timestamp, key=lambda x: x[1])
        self.spends.insert(position, (amount, timestamp))
    
    def getBalance(self, timestamp: int) -> float:
        """Query the account balance at the specified timestamp.
        
        Args:
            timestamp: Query timestamp (Unix timestamp, integer)
            
        Returns:
            float: Account balance
        """
        grant_pos = bisect.bisect_left(self.grants, timestamp, key=lambda x: x[1])
        total_grant = sum(grant for (grant, _) in self.grants[grant_pos:])  # Grants that haven't expired yet
        spend_pos = bisect.bisect_right(self.spends, timestamp, key=lambda x: x[1])
        total_spend = sum(spend for (spend, _) in self.spends[:spend_pos])
        return total_grant - total_spend



def test_account_balance():
    """Test cases"""
    print("Running AccountBalance tests...")
    
    # Test 1: Basic functionality - grant without expiration
    print("\nTest 1: Basic grant and balance")
    account = AccountBalance()
    account.addGrant(100.0, 1000)  # 100 units, expires at timestamp 1000
    balance_500 = account.getBalance(500)  # Should be 100
    balance_1000 = account.getBalance(1000)  # Should be 100 (expires at timestamp)
    balance_1500 = account.getBalance(1500)  # Should be 0 (expired)
    print(f"  Balance at 500: {balance_500}, at 1000: {balance_1000}, at 1500: {balance_1500}")
    
    # Test 2: Add spend transaction
    print("\nTest 2: Add spend transaction")
    account = AccountBalance()
    account.addGrant(200.0, 2000)
    account.addSpend(50.0, 500)
    balance_750 = account.getBalance(750)  # Should be 150
    print(f"  Balance after spending 50: {balance_750}")
    
    # Test 3: Multiple grants and spends
    print("\nTest 3: Multiple grants and spends")
    account = AccountBalance()
    account.addGrant(100.0, 1000)
    account.addGrant(150.0, 2000)
    account.addSpend(75.0, 500)
    account.addSpend(100.0, 1500)
    balance_750 = account.getBalance(750)  # Should be 100 - 75 = 25
    balance_1750 = account.getBalance(1750)  # Should be 150 - 175 = -25
    balance_2500 = account.getBalance(2500)  # Should be 0 - 175 = -175
    print(f"  Balance at 750: {balance_750}, at 1750: {balance_1750}, at 2500: {balance_2500}")
    
    # Test 4: Validation - non-positive amounts
    print("\nTest 4: Validation (non-positive amounts)")
    account = AccountBalance()
    try:
        account.addGrant(-50.0, 1000)
        print("  ERROR: Should have rejected negative grant amount")
    except ValueError as e:
        print(f"  CORRECT: Rejected negative grant: {e}")
    
    try:
        account.addGrant(0.0, 1000)
        print("  ERROR: Should have rejected zero grant amount")
    except ValueError as e:
        print(f"  CORRECT: Rejected zero grant: {e}")
    
    try:
        account.addSpend(-25.0, 500)
        print("  ERROR: Should have rejected negative spend amount")
    except ValueError as e:
        print(f"  CORRECT: Rejected negative spend: {e}")
    
    try:
        account.addSpend(0.0, 500)
        print("  ERROR: Should have rejected zero spend amount")
    except ValueError as e:
        print(f"  CORRECT: Rejected zero spend: {e}")
    
    # Test 5: Edge case - exactly at expiration time
    print("\nTest 5: Exact expiration time")
    account = AccountBalance()
    account.addGrant(50.0, 1000)
    balance_1000 = account.getBalance(1000)  # Should include the grant (expires at timestamp)
    print(f"  Balance at exact expiration time: {balance_1000}")
    
    # Test 6: Overdraft scenario
    print("\nTest 6: Overdraft scenario")
    account = AccountBalance()
    account.addGrant(100.0, 2000)
    account.addSpend(150.0, 500)
    balance = account.getBalance(750)  # Should be -50
    print(f"  Overdraft balance: {balance}")
    
    # Test 7: Grants added out of order
    print("\nTest 7: Grants added out of order")
    account = AccountBalance()
    account.addGrant(150.0, 2000)  # Later expiration first
    account.addGrant(100.0, 1000)  # Earlier expiration second
    balance_1500 = account.getBalance(1500)  # Should be 150
    print(f"  Balance with out-of-order grants: {balance_1500}")
    
    # Test 8: Spends added out of order
    print("\nTest 8: Spends added out of order")
    account = AccountBalance()
    account.addGrant(300.0, 3000)
    account.addSpend(100.0, 2000)  # Later spend first
    account.addSpend(75.0, 1000)   # Earlier spend second
    balance_1500 = account.getBalance(1500)  # Should be 300 - 75 = 225
    balance_2500 = account.getBalance(2500)  # Should be 300 - 175 = 125
    print(f"  Balance at 1500: {balance_1500}, at 2500: {balance_2500}")
    
    print("\nAll tests completed!")


if __name__ == "__main__":
    test_account_balance()