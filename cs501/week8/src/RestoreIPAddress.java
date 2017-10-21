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
     public List<String> restoreIpAddresses(String s) {
        List<String> ret = new ArrayList<String>();
        if (s == null || s. length() < 4 || s.length() > 12) {
            return ret;
        }
        StringBuffer sb =  new StringBuffer(s);
        Set<String> visited =  new HashSet<String>();
        backtrack(ret,visited,sb,0,1);
        return ret;
    }
    void backtrack(List<String> ret, Set<String> visited, StringBuffer sb, int numDots, int start) {
        // System.out.println("testing "+sb.toString());
        if(numDots == 3) {
            // System.out.println("candidate is "+sb.toString());
            if (isValid(sb.toString()) && !visited.contains(sb.toString())) {
                visited.add(sb.toString());
                ret.add(sb.toString());
            }
            return;
        }
        // Make sure 12 bits + 3 dots == 15. Hence limit it that way
        if (start >= sb.length() || sb.length() > 15||numDots > 3) {
            return;
        }
        for(int i = start; i < sb.length(); i++) {
            sb.insert(i,'.');
            backtrack(ret, visited, sb, numDots+1, start+1);
            sb.deleteCharAt(i);
        }
    }
    public boolean isValid(String s) {
        // System.out.println("candidate is "+s);
        String[] components = s.split("\\.");
        // System.out.println(Arrays.toString(components));
        if (components.length != 4) return false;
        for(String number : components) {
            if (number.length() == 0|| number.length()  > 3) return false;
            if (number.length() > 1 && number.charAt(0) =='0') return false;
            int val = Integer.valueOf(number);
            if (val < 0 || val > 255) return false;
        }
        return true; 
    }
}

public class RestoreIPAddress {
    static public void main(String args[]) {
        int testIteration = 10;
        Input  utils = new Input();
        Solution sol = new Solution();
        try {
            List <String> lines = Files.readAllLines(Paths.get("./week8/tst/hw2_input.txt"), StandardCharsets.UTF_8);
            testIteration = lines.size();
            for (int i = 0; i < testIteration; i++) {
                String input = lines.get(i);
                //omit if commented;
                if (input.charAt(0) == '#') continue;
                List<String> output = sol.restoreIpAddresses(input);
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
