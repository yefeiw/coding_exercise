/*
 * [793] Swap Adjacent in LR String
 *
 * https://leetcode.com/problems/swap-adjacent-in-lr-string/description/
 *
 * algorithms
 * Medium (23.86%)
 * Total Accepted:    1.6K
 * Total Submissions: 6.5K
 * Testcase Example:  '"X"\n"L"'
 *
 * In a string composed of 'L', 'R', and 'X' characters, like "RXXLRXRXL", a
 * move consists of either replacing one occurrence of "XL" with "LX", or
 * replacing one occurrence of "RX" with "XR". Given the starting string start
 * and the ending string end, return True if and only if there exists a
 * sequence of moves to transform one string to the other.
 * 
 * Example:
 * 
 * 
 * Input: start = "RXXLRXRXL", end = "XRLXXRRLX"
 * Output: True
 * Explanation:
 * We can transform start to end following these steps:
 * RXXLRXRXL ->
 * XRXLRXRXL ->
 * XRLXRXRXL ->
 * XRLXXRRXL ->
 * XRLXXRRLX
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= len(start) = len(end) <= 10000.
 * Both start and end will only consist of characters in {'L', 'R', 'X'}.
 * 
 */
class Solution {
    public boolean canTransform(String start, String end) {
         if (!start.replace("X","").equals(end.replace("X",""))) {
             return false;
         }

         int p1 = 0;
         int p2 = 0;

         while (p1 < start.length() && p2 < end.length()) {
             //get the non-X position of 2 strings
             while(p1 < start.length() && start.charAt(p1) == 'X') {
                 p1++;
             }
             while (p2 < end.length() && end.charAt(p2) == 'X') {
                 p2++;
             }

             //if both of the pointers reached the end, true
             if (p1 == start.length() && p2 == end.length()) {
                 return true;
             }
             //if only one of the pointers reached the end, false
             if (p1 == start.length() || p2 == end.length()) {
                 return false;
             }

             if (start.charAt(p1) != end.charAt(p2)) {
                 return false;
             }
             //if the character is 'L', it can only be moved to the left, therefore, p1 should be greater than p2
             if (start.charAt(p1) == 'L' && p1 < p2) {
                 return false;
             }
             //similarly...
             if (start.charAt(p1) == 'R' && p1 > p2) {
                 return false;
             }

             p1++;
             p2++;
            
         }
         return true;
    }
}
