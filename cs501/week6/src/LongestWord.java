import java.util.*;

class Solution {
    public String findLongestWord(String s, List<String> d) {
        StringBuffer sb = new StringBuffer();
        for(String word : d) {
            if (isValid(s, word)) {
                if (word.length() > sb.length() || (word.length() == sb.length() && word.compareTo(sb.toString()) < 0)) {
                    sb = new StringBuffer(word);
                }
            }
        }
        return sb.toString();
    }
    boolean isValid(String input, String word) {
        int i = 0;
        for (int j = 0; j < input.length(); j++) {
            if (i < word.length() && word.charAt(i) == input.charAt(j)) i++;
        }
        return (i == word.length());
    }
}

public class LongestWord {
     public static void main (String args[]) {
        int testIteration = 10;
        Input  testInput = new Input();
        Solution sol = new Solution();
  
        for (int i = 0; i < testIteration; i++) {
            String input =  testInput.generateRandomString(testIteration);
            List<String> dict = new ArrayList<String>();
            for(int j = 0; j < testIteration; j++) {
                dict.add(testInput.generateRandomString(testIteration));
            }
            String output = sol.findLongestWord(input, dict);
        }  

        System.out.println("Test exeucted with no crashes, please verify on leetcode");
    }
}