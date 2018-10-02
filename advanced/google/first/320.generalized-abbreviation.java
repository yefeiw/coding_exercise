/*
 * [320] Generalized Abbreviation
 *
 * https://leetcode.com/problems/generalized-abbreviation/description/
 *
 * algorithms
 * Medium (47.10%)
 * Total Accepted:    32.7K
 * Total Submissions: 69.5K
 * Testcase Example:  '"word"'
 *
 * Write a function to generate the generalized abbreviations of a word. 
 * 
 * Note: The order of the output does not matter.
 * 
 * Example:
 * 
 * 
 * Input: "word"
 * Output:
 * ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d",
 * "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 * 
 * 
 * 
 * 
 */
class Solution {
    public List<String> generateAbbreviations(String word) {
       List<String> result = new ArrayList<>();
       dfs(word.toCharArray(), result, new StringBuilder(), 0, 0); 
       return result;
    }
    void dfs(char[] word, List<String> result, StringBuilder sb, int i, int num) {
        int len = sb.length();
        if (i == word.length) {
            if(num > 0) sb.append(num);
            result.add(sb.toString());
        } else {
            dfs(word, result, sb, i+1, num+1);
            if (num > 0) sb.append(num);
            dfs(word, result, sb.append(word[i]), i+1, 0);
        }
        sb.setLength(len);
    }

}
