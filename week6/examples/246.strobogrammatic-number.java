/*
 * [246] Strobogrammatic Number
 *
 * https://leetcode.com/problems/strobogrammatic-number
 *
 * algorithms
 * Easy (39.96%)
 * Total Accepted:    29.2K
 * Total Submissions: 73.1K
 * Testcase Example:  '"1"'
 *
 * A strobogrammatic number is a number that looks the same when rotated 180
 * degrees (looked at upside down).
 * Write a function to determine if a number is strobogrammatic. The number is
 * represented as a string.
 * For example, the numbers "69", "88", and "818" are all strobogrammatic.
 */
class Solution {
    public boolean isStrobogrammatic(String num) {
       if (num.length() < 1) {
         return true;
       }
       int start = 0;
       int end = num.length() - 1;
       while(start <= end) {
         char left = num.charAt(start);
         char right = num.charAt(end);
         if (!isMatch(left,right)) {
           return false;
         }
         start++;
         end--;

       }
       return true;
    }

    private boolean isMatch(char a, char b) {
      Map<Character,Character> map = new HashMap<>();
      map.put('6','9');
      map.put('9','6');
      map.put('8','8');
      map.put('1','1');
      map.put('0','0');
      if (!map.containsKey(a)) {
        return false;
      }
      return (map.get(a) == b);
    }
}
