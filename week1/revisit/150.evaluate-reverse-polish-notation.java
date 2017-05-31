public class Solution {
    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length < 1) {
            return 0;
        }
        Deque<Integer> stack = new ArrayDeque<Integer>();
        //construct operands table
        Set<String> operand = new HashSet<String>();
        operand.add("+");
        operand.add("-");
        operand.add("*");
        operand.add("/");
        int ret = 0;
        for(String token: tokens) {
            if (operand.contains(token)) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(calc(b,a,token));
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.getFirst();
    }
    int calc(int a, int b, String token) {
        if (token.equals("+")) {
            return a+b;
        } else if (token.equals("-")) {
            return a-b;
        } else if (token.equals("*")) {
            return a*b;
        } else if (token.equals("/")) {
            return a/b;
        } else {
            return 0;
        }
    }
}
