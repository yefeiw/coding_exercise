/*
 * [20] Valid Parentheses
 *
 * https://leetcode.com/problems/valid-parentheses
 *
 * algorithms
 * Easy (33.48%)
 * Total Accepted:    234.2K
 * Total Submissions: 699.6K
 * Testcase Example:  '"["'
 *
 * Given a string containing just the characters '(', ')', '{', '}', '[' and
 * ']', determine if the input string is valid.
 * 
 * The brackets must close in the correct order, "()" and "()[]{}" are all
 * valid but "(]" and "([)]" are not.
 * 
 */
class Solution {
    public boolean isValid(String s) {
    	Deque<Character> stack = new ArrayDeque<>();
    	for(int i = 0; i < s.length(); i++) {
    		char cand =  s.charAt(i);
    		if (cand == '(' || cand == '[' || cand == '{') {
    			stack.push(cand);
    		} else if (cand == ')') {
    			if (stack.isEmpty() || stack.peek() != '(') {
    				return false;
    			}
    			stack.pop();
    		} else if (cand ==']') {
    			if (stack.isEmpty() || stack.peek() != '[') {
    				return false;
    			}
    			stack.pop();
    		} else if (cand == '}') {
    			if (stack.isEmpty() || stack.peek() != '{') {
    				return false;
    			}
    			stack.pop();
    		}
    	}
    	return (stack.isEmpty());
    } 
}
