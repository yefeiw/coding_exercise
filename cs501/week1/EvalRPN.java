import java.util.*;

class Solution {
    public int evalRPN(String[] tokens) {
        if (!isValid(tokens)) {
            return 0;
        }
        String operators = "+-*/";
        ArrayDeque<Integer> stack =  new ArrayDeque<Integer>();
        for (String token : tokens) {
            if (!operators.contains(token)) {
                try {
                    stack.push(Integer.valueOf(token));
                } catch (NumberFormatException e) {
                    ///invalid characters detected, exit
                    return 0;
                }
                continue;
            }
            //b is before a because there is a LIFO stucture in the stacks.
            int b = (stack.isEmpty()) ? 0 : stack.pop();
            int a = (stack.isEmpty()) ? 0 : stack.pop();
            if (token.equals("+")) {
                stack.push(a+b);
            } else if (token.equals("-")) {
                stack.push(a-b);
            } else if (token.equals("*")) {
                stack.push(a*b);
            } else if (token.equals("/")) {
                if (b == 0) {
                    stack.push(0);
                } else {
                stack.push(a/b);
            }
            }
        }
        if (stack.isEmpty()) {
            return 0;
        } else {
            return stack.pop();
        }
    }
    boolean isValid(String[] tokens) {
    if (null == tokens || tokens.length ==0) {
            return false;
        }
        return true;
    }



    //Solution: Jiuzhang
     public int ref(String[] tokens) {
        Stack<Integer> s = new Stack<Integer>();
        String operators = "+-*/";
        for(String token : tokens){
            if(!operators.contains(token)){
                s.push(Integer.valueOf(token));
                continue;
            }

            int a = s.pop();
            int b = s.pop();
            if(token.equals("+")) {
                s.push(b + a);
            } else if(token.equals("-")) {
                s.push(b - a);
            } else if(token.equals("*")) {
                s.push(b * a);
            } else {
                s.push(b / a);
            }
        }
        return s.pop();
    }
}

public class EvalRPN{
 public static void main (String args[]) {
        int testIteration = 10000;
        //small test set for manual verification
    // int testIteration = 10;
    Input  testInput = new Input();
    Solution sol = new Solution();
    for (int i = 0; i < testIteration; i++) {
        String[] input = testInput.generateRandomStringArray();
        int output = sol.evalRPN(input);
        int ref = sol.ref(input);    
        if (output != ref) {
            System.out.println ("Error: Mismatch found");
            System.out.println(output);
            System.out.println("vs.");
            System.out.println(ref);
            System.exit(1);
        }
    }
    System.out.println("test passed, ready to submit");
}
}
/* test cases used at leetcode:
"/"
"/../"
"//a/b/c/../../"
"////home/foo////bar"
"../../a/b/c"
*/