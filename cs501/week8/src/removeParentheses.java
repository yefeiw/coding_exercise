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
    public List<String> removeInvalidParentheses(String s) {
        List<String> ret = new ArrayList<String>();
        if (s == null || s.length()  == 0) {
            ret.add("");
            return ret;
        }
        Deque<String> queue = new ArrayDeque<String>();
        queue.add(s);
        Set<String> visited = new HashSet<String>();
        visited.add(s);
        boolean found = false;
        while (!queue.isEmpty()) {
            String head = queue.remove();
            if (isValid(head)) {
                ret.add(head);
                found = true;
            }
            if(found == true) continue;
            //to avoid string logic I choose to use stringBuffer
            for (int i = 0; i < head.length(); i++) {
                if (head.charAt(i) != '(' && head.charAt(i) != ')') {
                    continue;
                }
                String key = head.substring(0, i) + head.substring(i + 1);
                if (!visited.contains(key)) {
                    queue.add(key);
                    visited.add(key);
                }
            }

        }
        return ret;
    }
    public boolean isValid(String s) {
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') cnt++;
            if (s.charAt(i) == ')') {
                cnt--;
                if (cnt < 0) return false;
            }
        }
        return (cnt == 0);
    }

}

public class removeParentheses {
    static public void main(String args[]) {
        int testIteration = 10;
        Input  utils = new Input();
        Solution sol = new Solution();
        try {
            List <String> lines = Files.readAllLines(Paths.get("./week8/tst/hw1_input.txt"), StandardCharsets.UTF_8);
            testIteration = lines.size();
            for (int i = 0; i < testIteration; i++) {
                String input = lines.get(i);
                List<String> output = sol.removeInvalidParentheses(input);
                //print
                System.out.println(input);
                for (String out : output) {
                    if (!sol.isValid(out)) {
                        System.out.println("error: " + out + " is not valid");
                        return  ;
                    }
                    System.out.println(out);
                }
                //System.out.println(output);
            }
        } catch (IOException e) {
            System.out.println("Unable to read file");
            return;
        }

        System.out.println("Test executed without crashes, please manually verify input");
    }
}
