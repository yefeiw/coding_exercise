/*
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
        Deque<Integer> stack = new ArrayDeque<>();
        Set<String> operands = new HashSet<>();
        operands.add("+");
        operands.add("-");
        operands.add("*");
        operands.add("/");
        for (int i = 0; i < tokens.length; i++) {
            if (!operands.contains(tokens[i])) {
                stack.push(Integer.parseInt(tokens[i]));
            } else{
                int second = stack.pop();
                int first = stack.pop();
                stack.push(calculate(first,second, tokens[i]));
            }
        }
        return stack.peek();
    }

    //util functions
    private int calculate(int first, int second, String token) {
        if (token.equals("+")) {
            return first + second;
        } else if (token.equals("-")) {
            return first - second;
        } else if (token.equals("*")) {
            return first * second;
        } else {
            return first / second;
        }
    }
}
