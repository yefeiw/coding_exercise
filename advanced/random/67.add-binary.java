/*
 * [67] Add Binary
 *
 * https://leetcode.com/problems/add-binary
 *
 * algorithms
 * Easy (32.63%)
 * Total Accepted:    157.4K
 * Total Submissions: 482.3K
 * Testcase Example:  '"0"\n"0"'
 *
 *
 * Given two binary strings, return their sum (also a binary string).
 *
 *
 *
 * For example,
 * a = "11"
 * b = "1"
 * Return "100".
 *
 */
class Solution {
    public String addBinary(String a, String b) {
        a = new StringBuilder(a).reverse().toString();
        b = new StringBuilder(b).reverse().toString();
        StringBuilder ret = addString(a,b);
        return ret.reverse().toString();
    }
    //util function
    private StringBuilder addString (String a, String b) {
      int i = 0;
      int j = 0;
      int carry = 0;
      StringBuilder ret = new StringBuilder();
      while(i < a.length() || j < b.length() || carry > 0) {
        int left = (i < a.length()) ? a.charAt(i) - '0' : 0;
        int right = (j < b.length()) ? b.charAt(j) - '0' : 0;
        int sum = left + right + carry;
        int digit = sum % 2;
        carry = sum / 2;
        ret.append(digit);
        i++;
        j++;
      }
      return ret;
    }
}
