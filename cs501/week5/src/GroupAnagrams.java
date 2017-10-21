import java.util.*;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ret = new ArrayList<List<String>>();
        if (strs == null || strs.length == 0) {
            return ret;
        }
        HashMap<String,List<String>> dict = new HashMap<String,List<String>>();
        for(String str : strs) {
            String key = generateKey(str);
            if (!dict.containsKey(key)) {
                List<String> cand = new ArrayList<String>();
                ret.add(cand);
                dict.put(key,cand);
            }
            dict.get(key).add(str);
        }
        return ret;
        
    }
    String generateKey(String input) {
        char[] buffer = input.toCharArray();
        Arrays.sort(buffer);
        return String.valueOf(buffer);
    }
}

public class GroupAnagrams {
   public static void main (String args[]) {
    // int testIteration = 10000;
        //small test set for manual verification
    int testIteration = 10;
    Input  testInput = new Input();
    Solution sol = new Solution();
    for(int i = 0; i < testIteration; i++) {
        String[] s = testInput.generateRandomStringArray();
        List<List<String>> output = sol.groupAnagrams(s);
    }
    
    System.out.println("Test executed with no crashes, please verify on LeetCode");
}
}
