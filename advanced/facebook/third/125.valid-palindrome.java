/*
 * [125] Valid Palindrome
 *
 * https://leetcode.com/problems/valid-palindrome
 *
 * algorithms
 * Easy (26.33%)
 * Total Accepted:    176.1K
 * Total Submissions: 668.8K
 * Testcase Example:  '""'
 *
 *
 * Given a string, determine if it is a palindrome, considering only
 * alphanumeric characters and ignoring cases.
 *
 *
 *
 * For example,
 * "A man, a plan, a canal: Panama" is a palindrome.
 * "race a car" is not a palindrome.
 *
 *
 *
 * Note:
 * Have you consider that the string might be empty? This is a good question to
 * ask during an interview.
 *
 * For the purpose of this problem, we define empty string as valid palindrome.
 *
 */
class Solution {
    public boolean isPalindrome(String s) {
        if (s.length() == 0) {
            return true;
        }
        int start  = 0; int end = s.length() - 1;

        while (start < end) {
            while(start < end && !Character.isLetterOrDigit(s.charAt(start))) {
                start++;
            }
            while (start < end && !Character.isLetterOrDigit(s.charAt(end))) {
                end--;
            }
            if (s.charAt(start) != s.charAt(end)&& Character.toUpperCase(s.charAt(start)) != Character.toUpperCase(s.charAt(end))) {
                return false;
            }
            start++;
            end--;
        }
        //reaching here means there are no conflicts
        return true;
    }
}
