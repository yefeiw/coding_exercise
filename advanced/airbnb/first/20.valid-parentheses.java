/*
 * @lc app=leetcode id=20 lang=java
 *
 * [20] Valid Parentheses
 *
 * https://leetcode.com/problems/valid-parentheses/description/
 *
 * algorithms
 * Easy (39.47%)
 * Total Accepted:    1.2M
 * Total Submissions: 3.1M
 * Testcase Example:  '"()"'
 *
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and
 * ']', determine if the input string is valid.
 * 
 * An input string is valid if:
 * 
 * 
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "()"
 * Output: true
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "()[]{}"
 * Output: true
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "(]"
 * Output: false
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: s = "([)]"
 * Output: false
 * 
 * 
 * Example 5:
 * 
 * 
 * Input: s = "{[]}"
 * Output: true
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 10^4
 * s consists of parentheses only '()[]{}'.
 * 
 * 
 */
class Solution {
    public boolean isValid(String s) {
       Deque<Character> stack = new ArrayDeque<>();
       Map<Character, Character> ridx = Map.of(
           ')', '(',
           ']', '[',
           '}', '{'
       );
       for (int i = 0; i < s.length(); i++) {
           char c = s.charAt(i);
           if (!ridx.containsKey(c)) {
               stack.addFirst(c);
           } else {
               if (stack.isEmpty()) {
                   return false;
               }
               if (stack.peekFirst() != ridx.get(c)) {
                   return false;
               }
               stack.removeFirst();
           }
       }
       return (stack.isEmpty());
    }
}
