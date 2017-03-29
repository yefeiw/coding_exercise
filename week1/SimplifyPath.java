import java.util.*;

class Solution {
    public String simplifyPath(String path) {
        if (!isValid(path)) {
            return path;
        }
        //remember this in Java, it's a lot more handy than C++;
        String[] input = path.split("/");
        ArrayDeque<String> stack = new ArrayDeque<String>();
        for (String s : input) {
            if (s.equals(".")) {
                //current directory, nothing changed
                continue;
            } else if (s.equals("")) {
                //empty directory usually caused by many slashes
                continue;
            } else if (s.equals("..")) {
                //pop stack if possible, return error if not
                if (stack.isEmpty()) {
                    continue;
                } else {
                    stack.pop();
                }
            } else {
                //for everything else, push onto the stack
                stack.push(s);
            }
        }
        //assemble the results;
        if (stack.isEmpty()) {
            return "/";
        } else {
            String ret = "";
            for (String s : stack) {
                ret = "/" + s + ret;
            }
            return ret;
        }

    }

    boolean isValid(String s) {
        if (null == s || s.length() == 0) {
            return false;
        }
        return true;
    }



    //Solution: Jiuzhang
    public String ref(String path) {
        String result = "/";
        String[] stubs = path.split("/+");
        ArrayList<String> paths = new ArrayList<String>();
        for (String s : stubs){
            if(s.equals("..")){
                if(paths.size() > 0){
                    paths.remove(paths.size() - 1);
                }
            }
            else if (!s.equals(".") && !s.equals("")){
                paths.add(s);
            }
        }
        for (String s : paths){
            result += s + "/";
        }
        if (result.length() > 1)
            result = result.substring(0, result.length() - 1);
        return result;
    }
}

public class SimplifyPath{
 public static void main (String args[]) {
        int testIteration = 10000;
        //small test set for manual verification
    // int testIteration = 10;
    Input  testInput = new Input();
    Solution sol = new Solution();
    for (int i = 0; i < testIteration; i++) {
        String input = testInput.generateRandomString();
        System.out.println(input);
        String output = sol.simplifyPath(input);
        String ref = sol.ref(input);    
        System.out.println(output);
        if (!output.equals(ref)) {
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