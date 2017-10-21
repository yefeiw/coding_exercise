/*
 * [139] Word Break
 *
 * https://leetcode.com/problems/word-break
 *
 * algorithms
 * Medium (30.12%)
 * Total Accepted:    162.3K
 * Total Submissions: 538.8K
 * Testcase Example:  '"leetcode"\n["leet","code"]'
 *
 * 
 * Given a non-empty string s and a dictionary wordDict containing a list of
 * non-empty words, determine if s can be segmented into a space-separated
 * sequence of one or more dictionary words. You may assume the dictionary does
 * not contain duplicate words.
 * 
 * 
 * For example, given
 * s = "leetcode",
 * dict = ["leet", "code"].
 * 
 * 
 * 
 * Return true because "leetcode" can be segmented as "leet code".
 * 
 * 
 * 
 * UPDATE (2017/1/4):
 * The wordDict parameter had been changed to a list of strings (instead of a
 * set of strings). Please reload the code definition to get the latest
 * changes.
 * 
 */
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        if (wordDict.size() == 0) {
        	return (s.length() == 0);
        }
        Set<String> set = new HashSet<>();
        for(String word : wordDict) {
        	set.add(word);
        }
        boolean dp[] = new boolean[s.length() + 1];
        dp[0] = true;
        for(int i = 1; i <= s.length(); i++) {
        	for (int j = i-1; j >= 0; j--) {
        		if (dp[j] == false) continue;
        		String cand =  s.substring(j,i);
        		if (set.contains(cand)) {
        			dp[i] = true;
        			break;
        		}
        	}
        }
        return dp[s.length()];
    }
}
