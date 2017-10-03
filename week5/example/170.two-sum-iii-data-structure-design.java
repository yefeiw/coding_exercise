/*
 * [170] Two Sum III - Data structure design
 *
 * https://leetcode.com/problems/two-sum-iii-data-structure-design
 *
 * algorithms
 * Easy (24.89%)
 * Total Accepted:    30.1K
 * Total Submissions: 120.9K
 * Testcase Example:  '["TwoSum","add","add","add","find","find"]\n[[],[1],[3],[5],[4],[7]]'
 *
 * Design and implement a TwoSum class. It should support the following
 * operations: add and find.
 * 
 * 
 * add - Add the number to an internal data structure.
 * find - Find if there exists any pair of numbers which sum is equal to the
 * value.
 * 
 * 
 * 
 * For example,
 * 
 * add(1); add(3); add(5);
 * find(4) -> true
 * find(7) -> false
 * 
 * 
 */
class TwoSum {
	private Map<Integer, Integer> map;
	private List<Integer> list = new ArrayList<>();

    /** Initialize your data structure here. */
    public TwoSum() {
        map = new HashMap<Integer, Integer>();
        list = new ArrayList<Integer>();
    }
    
    /** Add the number to an internal data structure.. */
    public void add(int number) {
        list.add(number);
        Integer frequency = map.get(number);
        if (frequency ==  null) {
        	map.put(number, 1);
        } else {
        	map.put(number, frequency+1);
        }

    }
    
    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        for (int curKey : list) {
        	int target = value - curKey;
        	Integer count = map.get(target);
        	if (count != null) {
        		if (curKey != target || (curKey == target && count > 1)) {
        			return true;
        		}
        	}
        }
        return false;
    }
}

/**
 * Your TwoSum object will be instantiated and called as such:
 * TwoSum obj = new TwoSum();
 * obj.add(number);
 * boolean param_2 = obj.find(value);
 */
