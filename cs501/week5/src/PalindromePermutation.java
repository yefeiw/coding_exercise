import java.util.*;

class Solution {
   List<String> ans;
   HashSet<String> dict;
    public List<String> generatePalindromes(String s) {
        ans = new ArrayList<String>();
        dict = new HashSet<String>();
        if (null == s || s.length() == 0) {
            return ans;
        }
        StringBuffer sb = new StringBuffer();
        boolean[] used = new boolean[s.length()];
        Arrays.fill(used,false);
        backtrack(s,used,sb);
        return ans;
    }
    void backtrack(String s, boolean[] used, StringBuffer sb) {
        if (sb.length() == s.length()) {
            if(isPalindrome(sb) && !dict.contains(sb.toString())) {
                ans.add(sb.toString());
                dict.add(sb.toString());
            }
            return;
        }
        for(int i = 0; i < s.length(); i++) {
            if(!used[i]) {
                used[i] = true;
                sb.append(s.charAt(i));
                backtrack(s,used, sb);
                sb.deleteCharAt(sb.length() - 1);
                used[i] = false;
            }
        }
    }
    boolean isPalindrome (StringBuffer sb) {
        int start = 0; 
        int end = sb.length() -1;
        while(start < end) {
            if (sb.charAt(start)!= sb.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
public class PalindromePermutation {
   public static void main (String args[]) {
    // int testIteration = 10000;
        //small test set for manual verification
    int testIteration = 10;
    Input  testInput = new Input();
    Solution sol = new Solution();
    for(int i = 0; i < testIteration; i++) {
        String s = testInput.generateRandomString(i);
        List<String> output = sol.generatePalindromes(s);
    }
    
    System.out.println("Test executed with no crashes, please verify on LeetCode");
}
}
