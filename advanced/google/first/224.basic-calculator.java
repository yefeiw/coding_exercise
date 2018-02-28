/*
 * [224] Basic Calculator
 *
 * https://leetcode.com/problems/basic-calculator/description/
 *
 * algorithms
 * Hard (28.42%)
 * Total Accepted:    62.7K
 * Total Submissions: 220.6K
 * Testcase Example:  '"1 + 1"'
 *
 * Implement a basic calculator to evaluate a simple expression string.
 * 
 * The expression string may contain open ( and closing parentheses ), the plus
 * + or minus sign -, non-negative integers and empty spaces  .
 * 
 * You may assume that the given expression is always valid.
 * 
 * Some examples:
 * 
 * "1 + 1" = 2
 * " 2-1 + 2 " = 3
 * "(1+(4+5+2)-3)+(6+8)" = 23
 * 
 * 
 * 
 * 
 * Note: Do not use the eval built-in library function.
 * 
 */
class Solution {
    public int calculate(String s) {
       int ret = 0;
       Deque<Integer> stack = new ArrayDeque();
       //iterator
       int pos = 0;
       //current sign, 1 or -1
       int sign = 1;
       //current value to be returned;
       int curVal = 0;
       while(pos < s.length()) {
        if (s.charAt(pos) == ' ') {
            pos++;
            continue;
        } else if (Character.isDigit(s.charAt(pos))) {
           int cur = s.charAt(pos) - '0';
           //BUG: to greatly simplify the operations, we need to walk all the way into the digits till completion.
           //Otherwise the code will be not readable and also bug prone
           while(pos + 1 < s.length() && Character.isDigit(s.charAt(pos + 1))) {
               cur = 10 * cur + s.charAt(++pos) - '0';
           }
           curVal += sign * cur;
        } else if (s.charAt(pos)=='+' || s.charAt(pos) == '-') {
            sign = (s.charAt(pos) == '-') ? -1 : 1;
        } else if (s.charAt(pos) == '(') {
            stack.push(curVal);
            stack.push(sign);
            curVal = 0;
            sign = 1;
        } else if (s.charAt(pos) ==  ')') {
            int prevSign = stack.pop();
            int prevValue = stack.pop();
            curVal = prevValue + (prevSign) * curVal;
            //BUG: here since we have finished bracket processing, we should mark the sign back to 1.
            sign = 1;
        } else {
            //invalid characters, return 0;
            return 0;
        }
        pos++;
       }
       return curVal;
    }
}
