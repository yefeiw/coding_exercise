import java.util.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

// Notes:
// Normal BFS will do the job. No need to think of harder algorithms.
// Note on the logic of string removal. StringBuffer will be very dangerous.
// Note especially when borrowing help from the solution. Remember to check the code flow before hitting submit button!

class Solution {
    public String parseTernary(String expression) {
        while (expression.length() != 1) {
            int i = expression.lastIndexOf("?");
            char tmp;
            if (expression.charAt(i - 1) == 'T') {
                tmp = expression.charAt(i + 1);
            } else {
                tmp = expression.charAt(i + 3);
            }
            expression = expression.substring(0, i - 1) + tmp + expression.substring(i + 4);
        }
        return expression;
    }

}

public class TernaryExpression {
    static public void main(String args[]) {
        int testIteration = 10;
        Input  utils = new Input();
        Solution sol = new Solution();
        try {
            List <String> lines = Files.readAllLines(Paths.get("./week8/tst/ternary_input.txt"), StandardCharsets.UTF_8);
            testIteration = lines.size();
            for (int i = 0; i < testIteration; i++) {
                String input = lines.get(i);
                String output = sol.parseTernary(input);
                //print
                System.out.println(input);
                System.out.println(output);
            }
        } catch (IOException e) {
            System.out.println("Unable to read file");
            return;
        }

        System.out.println("Test executed without crashes, please manually verify input");
    }
}
