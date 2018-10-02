/*
 * [318] Maximum Product of Word Lengths
 *
 * https://leetcode.com/problems/maximum-product-of-word-lengths/description/
 *
 * algorithms
 * Medium (46.71%)
 * Total Accepted:    69.3K
 * Total Submissions: 148.3K
 * Testcase Example:  '["abcw","baz","foo","bar","xtfn","abcdef"]'
 *
 * Given a string array words, find the maximum value of length(word[i]) *
 * length(word[j]) where the two words do not share common letters. You may
 * assume that each word will contain only lower case letters. If no such two
 * words exist, return 0.
 * 
 * Example 1:
 * 
 * 
 * Input: ["abcw","baz","foo","bar","xtfn","abcdef"]
 * Output: 16 
 * Explanation: The two words can be "abcw", "xtfn".
 * 
 * Example 2:
 * 
 * 
 * Input: ["a","ab","abc","d","cd","bcd","abcd"]
 * Output: 4 
 * Explanation: The two words can be "ab", "cd".
 * 
 * Example 3:
 * 
 * 
 * Input: ["a","aa","aaa","aaaa"]
 * Output: 0 
 * Explanation: No such pair of words.
 * 
 * 
 */
class Solution {
    public int maxProduct(String[] words) {
       List<Integer> masks = new ArrayList<>();
       Arrays.stream(words).forEach(word -> masks.add(constructMap(word)));
       int result = 0;
       for (int i = 0; i < words.length; i++) {
           for (int j = i + 1; j < words.length; j++) {
               if ((masks.get(i) & masks.get(j)) == 0){
                result = Math.max(result, words[i].length() * words[j].length());
               }
           }
       }
       return result;
    }

    static int constructMap(String input) {
        int result = 0;
       char[] inputBytes = input.toCharArray();
       for (char c : inputBytes) {
         result = result | ( 1 << c -'a');
       }
       return result;
    }
}
