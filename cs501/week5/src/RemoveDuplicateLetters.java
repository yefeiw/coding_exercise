import java.util.*;

class Solution {
  public String removeDuplicateLetters(String s) {
        if(s == null || s.length() == 0 ) {
            return s;
        }
        HashMap<Character, Integer> dict = new HashMap<Character,Integer>();
        Deque<Character> stack = new ArrayDeque<Character>();
        //get all statistics of all numbers
        FurnishDict(dict,s);
        for(int i = 0;i < s.length(); i++) {
            char c = s.charAt(i);
            //if stack is empty, push
            if (stack.isEmpty()) {
                stack.push(c);
            } else {
                if(stack.contains(c)) {
                    dict.put(c,dict.get(c)-1);
                    continue;
                }
                char top = stack.peek();
                while(!stack.isEmpty() && dict.get(top) > 1 && c < top) {
                    dict.put(top,dict.get(top) - 1);
                    stack.pop();
                    if(!stack.isEmpty()) {
                        top = stack.peek();
                    }
                }
                stack.push(c);
            }
        }
        StringBuffer sb =  new StringBuffer();
        while(!stack.isEmpty()) {
            sb.append(stack.pollLast());
        }
        return sb.toString();
    }
    void FurnishDict(HashMap<Character,Integer> dict, String s) {
        for(int i = 0; i < s.length(); i++) {
            char c  = s.charAt(i);
            if(!dict.containsKey(c) ) {
                dict.put(c,0);
            }
            dict.put(c,dict.get(c)+1);
        }
    }
}

public class RemoveDuplicateLetters {
   public static void main (String args[]) {
    // int testIteration = 10000;
        //small test set for manual verification
    int testIteration = 10;
    Input  testInput = new Input();
    Solution sol = new Solution();
    for(int i = 0; i < testIteration; i++) {
        String s = testInput.generateRandomString(i);
        String output = sol.removeDuplicateLetters(s);
        System.out.printf("Input %s, Output %s",s,output);
    }
    
    System.out.println("Test executed with no crashes, please manually verify output");
}
}
