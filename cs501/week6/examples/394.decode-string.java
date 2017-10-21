/*
 * [394] Decode String
 *
 * https://leetcode.com/problems/decode-string
 *
 * algorithms
 * Medium (41.46%)
 * Total Accepted:    37.8K
 * Total Submissions: 91K
 * Testcase Example:  '"3[a]2[bc]"'
 *
 *
 * Given an encoded string, return it's decoded string.
 *
 *
 * The encoding rule is: k[encoded_string], where the encoded_string inside the
 * square brackets is being repeated exactly k times. Note that k is guaranteed
 * to be a positive integer.
 *
 *
 * You may assume that the input string is always valid; No extra white spaces,
 * square brackets are well-formed, etc.
 *
 * Furthermore, you may assume that the original data does not contain any
 * digits and that digits are only for those repeat numbers, k. For example,
 * there won't be input like 3a or 2[4].
 *
 *
 * Examples:
 *
 * s = "3[a]2[bc]", return "aaabcbc".
 * s = "3[a2[c]]", return "accaccacc".
 * s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 *
 *
 */
class Solution {
    public String decodeString(String s) {
        if (s.length() == 0) {
          return s;
        }
        StringBuilder ret = new StringBuilder();
        Deque<String> seq = new ArrayDeque<>();
        seq.push("");
        Deque<Integer> nums = new ArrayDeque<>();
        int numBuffer = 0;
        for(int i = 0; i < s.length(); i++)  {
          char cand = s.charAt(i);//candidate character;
          if (cand == '[') {
            nums.push(numBuffer);
            seq.push("");
            numBuffer = 0;
          } else if (Character.isDigit(cand)) {
            numBuffer *= 10;
            numBuffer += (cand - '0');
          } else if (cand == ']') {
            int iteration = nums.pop();
            StringBuilder temp = new StringBuilder();
            String strTop = seq.pop();
            for (int j = 0; j < iteration; j++) {
              temp.append(strTop);
            }
            seq.push(seq.isEmpty()  ? temp.toString() : seq.pop() + temp.toString());
          } else {
            seq.push(seq.pop() + cand);
          }
        }
        return seq.pop();
    }
}
