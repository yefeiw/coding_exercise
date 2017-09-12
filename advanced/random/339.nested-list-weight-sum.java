/*
 * [339] Nested List Weight Sum
 *
 * https://leetcode.com/problems/nested-list-weight-sum
 *
 * algorithms
 * Easy (61.94%)
 * Total Accepted:    24.5K
 * Total Submissions: 39.5K
 * Testcase Example:  '[[1,1],2,[1,1]]'
 *
 * Given a nested list of integers, return the sum of all integers in the list
 * weighted by their depth.
 * 
 * Each element is either an integer, or a list -- whose elements may also be
 * integers or other lists.
 * 
 * Example 1:
 * Given the list [[1,1],2,[1,1]], return 10. (four 1's at depth 2, one 2 at
 * depth 1)
 * 
 * Example 2:
 * Given the list [1,[4,[6]]], return 27. (one 1 at depth 1, one 4 at depth 2,
 * and one 6 at depth 3; 1 + 4*2 + 6*3 = 27)
 * 
 */
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class Solution {
    public int depthSum(List<NestedInteger> nestedList) {
        int ret = 0;
        if (nestedList.isEmpty()) {
        	return ret;
        }
        for (NestedInteger number : nestedList) {
        	ret += helper(number, 1);
        }
        return ret;
    }
    //util function 
    private int helper(NestedInteger input, int level) {
    	if (input.isInteger()) {
    		return input.getInteger() * level;
    	} else {
    		int ret = 0;
    		List<NestedInteger> list = input.getList();
    		for (NestedInteger number : list) {
    			ret += helper(number, level+1);
    		}
    		return ret;
    	}
    }
}
