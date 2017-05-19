import java.util.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

// Notes:


class Solution {
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        List<Set<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            adj.add(new HashSet<Integer>());
        }
        int[] degree = new int[26];
        Arrays.fill(degree,-1);

        for (int i = 0; i < words.length; i++) {
            for (char c : words[i].toCharArray()) {
                if (degree[c-'a'] <0) {
                    degree[c-'a'] = 0;
                }
            }
            if (i > 0) {
                String w1 = words[i-1], w2 = words[i];
                int len = Math.min(w1.length(),w2.length());
                for (int j = 0; j < len; j++) {
                    int c1 = w1.charAt(j) - 'a', c2 = w2.charAt(j) - 'a';
                    if (c1 != c2) {
                         if (!adj.get(c1).contains(c2)) {
                            adj.get(c1).add(c2);
                            degree[c2]++;
                         }
                         break;
                    }
                    if (j == w2.length() - 1 && w1.length() > w2.length()) {
                        return "";
                    }
                }
            }
        }
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i <degree.length; i++) {
            if (degree[i] == 0) {
                queue.add(i);
            }
        }

        StringBuffer sb = new StringBuffer();
        while (!queue.isEmpty()) {
            int i = queue.remove();
            sb.append((char)('a'+i));
            for (int j : adj.get(i)){
                degree[j]--;
                if (degree[j] == 0) {
                    queue.add(j);
                }

            }
        }
        for (int d : degree) {
            if (d > 0) {
                return "";

            }
        }
        return sb.toString();
    }
}

public class AlienDictionary {
    static public void main(String args[]) {
        int testIteration = 10;
        Input  utils = new Input();
        Solution sol = new Solution();
        // Generating word dictionary has been a little hard, since we will test it on leetcode.

        System.out.println("Test executed without crashes, please manually verify input");
    }
}
