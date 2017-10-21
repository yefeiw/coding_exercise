/*
 * [341] Flatten Nested List Iterator
 *
 * https://leetcode.com/problems/flatten-nested-list-iterator
 *
 * algorithms
 * Medium (41.61%)
 * Total Accepted:    42.1K
 * Total Submissions: 101.2K
 * Testcase Example:  '[[1,1],2,[1,1]]'
 *
 * Given a nested list of integers, implement an iterator to flatten it.
 * 
 * Each element is either an integer, or a list -- whose elements may also be
 * integers or other lists.
 * 
 * Example 1:
 * Given the list [[1,1],2,[1,1]],
 * 
 * By calling next repeatedly until hasNext returns false, the order of
 * elements returned by next should be: [1,1,2,1,1].
 * 
 * 
 * 
 * Example 2:
 * Given the list [1,[4,[6]]],
 * 
 * By calling next repeatedly until hasNext returns false, the order of
 * elements returned by next should be: [1,4,6].
 * 
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
public class NestedIterator implements Iterator<Integer> {
	//Data structures
	Deque<NestedInteger> stack;

	public NestedIterator(List<NestedInteger> nestedList) {
		stack = new ArrayDeque<>();
		if(nestedList.isEmpty()) return;
		for(int i = nestedList.size() -1; i >= 0; i--) {
			stack.push(nestedList.get(i));
		}
	}

	@Override
	public Integer next() {
		return stack.pop().getInteger();
	}	

	@Override
	public boolean hasNext() {
		while(!stack.isEmpty()) {
			NestedInteger top = stack.getFirst();
			if(top.isInteger()) {
				return true;
			} else {
				//BUGWARNING:
				//1. need to make sure we pop something here if top is not integer.
				//2. Make sure the check for integer has to be in hasNext, not next
				List<NestedInteger> list = top.getList();
				stack.pop();
				if(list.isEmpty()) {
					continue;
				}
				for(int i =  list.size() -1; i >= 0 ;i--) {
					stack.push(list.get(i));
				}
			}
		}
		return false;
	}
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
