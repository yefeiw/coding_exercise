/*

import java.util.NoSuchElementException;
 * [150] Evaluate Reverse Polish Notation
 *
 * https://leetcode.com/problems/evaluate-reverse-polish-notation
 *
 * algorithms
 * Medium (27.58%)
 * Total Accepted:    101.4K
 * Total Submissions: 367.6K
 * Testcase Example:  '["18"]'
 *
 *
 * Evaluate the value of an arithmetic expression in Reverse Polish
 * Notation.
 *
 *
 *
 * Valid operators are +, -, *, /. Each operand may be an integer or another
 * expression.
 *
 *
 *
 * Some examples:
 *
 * ⁠ ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 * ⁠ ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 *
 *
 */
class Solution {
    public int evalRPN(String[] tokens) {
        if (tokens.length == 0) {
            return 0;
        }
        Deque<Integer> stack =  new ArrayDeque();
        for(String token : tokens) {
            if (isOperator(token)){
                int second = stack.pop();
                int first = stack.pop();
                stack.push(evaluate(first, second, token));
            } else {
                int val = Integer.parseInt(token);
                stack.push(val);
            }
        }
        return stack.peek();
    }

    //util functions
    private boolean isOperator(String token) {
        String operators = "+-*/";
        return (operators.indexOf(token) != -1);
    }

    private int evaluate(int first, int second, String token) {
        if (token.equals("+")) {
            return first + second;
        } else if (token.equals("-")) {
            return first - second;
        } else if (token.equals("*")) {
            return first * second;
        } else if (token.equals("/")) {
            return first / second;
        } else {
            //throw NoSuchElementException;
            return 0;
        }
    }
}
