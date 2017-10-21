import java.util.*;

class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int[] map = new int[26];//all inputs are lowercase
        Arrays.fill(map,0);

        int len1 = s1.length();
        int len2 = s2.length();

        if (len2 < len1) {
            return false;
        }

        for (int i = 0; i < len1; i++) {
            map[s1.charAt(i)-'a']++;
            map[s2.charAt(i)-'a']--;
        }
        int pos  = len1;
        while(pos < len2 && !allZero(map)) {
            map[s2.charAt(pos)-'a']--;
            map[s2.charAt(pos-len1)-'a']++;
            pos++;
        }
        //Have to check here again, in case matches comes in the last round
        return (allZero(map) || allpos != len2);
    }
    boolean allZero(int[] input) {
        for (int i : input) {
            if (i != 0) return false;
        }
        return true;
    }

//    Note: Backtracking TLE Solution below  //
    // String base;//to hold the value of s2
    // public boolean checkInclusion(String s1, String s2) {
    //     if(s2 == null || s2.length() == 0) {
    //         return false;
    //     } else if (s1 == null || s1.length() == 0) {
    //         return true;
    //     }
    //     base = s2;
    //     StringBuilder sb = new StringBuilder();
    //     boolean[] visited  = new boolean[s1.length()];
    //     Arrays.fill(visited,false);    
    //     if(foundInBacktrack(s1,sb,visited)) {
    //         return true;
    //     } else {
    //         return false;
    //     }
    // }
    // boolean foundInBacktrack(String s1, StringBuilder sb, boolean[] visited) {
    //     if (sb.length() == s1.length()) {
    //         return kmp(sb.toString(), base);
    //     }
    //     for (int i = 0; i < s1.length(); i++) {
    //         if (visited[i]) {
    //             continue;
    //         }
    //         visited[i] = true;
    //         sb.append(s1.charAt(i));
    //         if(foundInBacktrack(s1,sb,visited)) {
    //             return true;
    //         }
    //         sb.setLength(sb.length() -1);
    //         visited[i] = false;
    //     }
    //     //reaching here means nothing is found
    //     return false;
    // }
    // boolean kmp(String s1, String s2) {
    //     String buffer = s1 + "#" + s2;
    //     int[] prefix = new int[buffer.length()];
    //     prefix[0] = 0;
    //     int border = 0;
    //     for(int i = 1; i < prefix.length; i++) {
    //         while(border > 0 && buffer.charAt(border) != buffer.charAt(i)) {
    //             border = prefix[border-1];
    //         }
    //         if(buffer.charAt(border) == buffer.charAt(i)) {
    //             border++;
    //         } else {
    //             border = 0;
    //         }
    //         prefix[i] = border;
    //         if (border >= s1.length()) {
    //             return true;
    //         }
    //     }
    //     //reaching here means no match has been found.
    //     return false;
    // }
}

public class PermutationString {
    static public void main(String args[]) {
        int testIteration = 10;
        Input  utils = new Input();
        Solution sol = new Solution();

        for (int i = 0; i < testIteration; i++) {
            String s1 = utils.generateRandomString('a','z');
            String s2 = utils.generateRandomString('a','z');
            boolean output = sol.checkInclusion(s1,s2);
            //print
            System.out.println(s1);
            System.out.println(s2);
            System.out.println(output);
        }
        System.out.println("Test executed without crashes, please manually verify input");
    }
}
