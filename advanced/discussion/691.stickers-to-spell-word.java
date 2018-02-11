/*
 * [691] Stickers to Spell Word
 *
 * https://leetcode.com/problems/stickers-to-spell-word/description/
 *
 * algorithms
 * Hard (34.26%)
 * Total Accepted:    3.4K
 * Total Submissions: 9.8K
 * Testcase Example:  '["with","example","science"]\n"thehat"'
 *
 * 
 * We are given N different types of stickers.  Each sticker has a lowercase
 * English word on it.
 * 
 * You would like to spell out the given target string by cutting individual
 * letters from your collection of stickers and rearranging them.
 * 
 * You can use each sticker more than once if you want, and you have infinite
 * quantities of each sticker.
 * 
 * What is the minimum number of stickers that you need to spell out the
 * target?  If the task is impossible, return -1.
 * 
 * 
 * Example 1:
 * Input:
 * ["with", "example", "science"], "thehat"
 * 
 * 
 * Output:
 * 3
 * 
 * 
 * Explanation:
 * We can use 2 "with" stickers, and 1 "example" sticker.
 * After cutting and rearrange the letters of those stickers, we can form the
 * target "thehat".
 * Also, this is the minimum number of stickers necessary to form the target
 * string.
 * 
 * 
 * Example 2:
 * Input:
 * ["notice", "possible"], "basicbasic"
 * 
 * 
 * Output:
 * -1
 * 
 * 
 * Explanation:
 * We can't form the target "basicbasic" from cutting letters from the given
 * stickers.
 * 
 * 
 * Note:
 * stickers has length in the range [1, 50].
 * stickers consists of lowercase English words (without apostrophes).
 * target has length in the range [1, 15], and consists of lowercase English
 * letters.
 * In all test cases, all words were chosen randomly from the 1000 most common
 * US English words, and the target was chosen as a concatenation of two random
 * words.
 * The time limit may be more challenging than usual.  It is expected that a 50
 * sticker test case can be solved within 35ms on average.
 * 
 */
class Solution {
    public int minStickers(String[] stickers, String target) {
       int m = stickers.length;
       int[][] map = new int[m][26];
        Map<String, Integer> dp = new HashMap();
        
        //build the stickers word counts.
        for(int i = 0; i < m ; i++) {
            for (char c : stickers[i].toCharArray())map[i][c-'a']++;
        }

        dp.put("",0);
        return helper(map,dp,target);
    }

    private int helper(int[][] map, Map<String, Integer> dp, String target) {
        //Memorization
        if (dp.containsKey(target)) {
            return dp.get(target);
        }
        //min-recording variables.
        int ans = Integer.MAX_VALUE;
        int[] targetMap = new int[26];
        for(char c : target.toCharArray()) targetMap[c-'a']++;
        //try every sticker
        for (int i = 0; i < map.length; i++) {
            if (map[i][target.charAt(0)-'a'] == 0) {
                continue;
            }
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < 26; j++) {
                if(targetMap[j] > 0) {
                    for (int k = 0; k < Math.max(0,targetMap[j] - map[i][j]); k++) {
                        sb.append((char)('a'+j));
                    }
                }
            }
            String s = sb.toString();
            int tmp = helper(map,dp,s);
            if (tmp != -1) ans = Math.min(ans, tmp+1);
        }
        dp.put(target,(ans == Integer.MAX_VALUE) ? -1 : ans);
        return dp.get(target);
    }
}
