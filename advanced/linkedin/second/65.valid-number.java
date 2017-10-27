/*
 * [65] Valid Number
 *
 * https://leetcode.com/problems/valid-number
 *
 * algorithms
 * Hard (12.79%)
 * Total Accepted:    73.8K
 * Total Submissions: 577.2K
 * Testcase Example:  '"3"'
 *
 * Validate if a given string is numeric.
 *
 *
 * Some examples:
 * "0" => true
 * "   0.1  " => true
 * "abc" => false
 * "1 a" => false
 * "2e10" => true
 *
 *
 * Note: It is intended for the problem statement to be ambiguous. You should
 * gather all requirements up front before implementing one.
 *
 *
 *
 * Update (2015-02-10):
 * The signature of the C++ function had been updated. If you still see your
 * function signature accepts a const char * argument, please click the reload
 * button  to reset your code definition.
 *
 */
class Solution {
    public boolean isNumber(String s) {
        //get rid of spaces at the beginning and the end;
        while(s.length() > 0 && s.charAt(0) ==' ') {
            s = s.substring(1);
        }
        while(s.length() > 0 && s.charAt(s.length() -1) == ' ') {
            s = s.substring(0, s.length()-1);
        }
        int m = s.length();
        if (m == 0) {
            return false;
        }
        for(int i = 0; i < m; i++) {
            char cand = s.charAt(i);
            if (cand == 'e') {
                return (isDecimalNumber(s,0,i-1,false) && isDecimalNumber(s,i+1,m-1,true));
            }
        }
        //reaching here means there is no e
        return isDecimalNumber(s,0,m-1,false);
    }

    private boolean isDecimalNumber(String s, int start, int end, boolean disallowPoint) {
        if(start > end) {
            return false;
        }
        boolean isDotSeen =disallowPoint;
        int digitCnt = 0;
        for(int i = start; i <= end; i++) {
            char cand = s.charAt(i);
            if (Character.isDigit(cand)) {
                //should be valid?
                digitCnt++;
            }else if (cand =='-'|| cand =='+') {
                if (i != start) {
                    return false;
                }
            } else if (cand == '.') {
                if (isDotSeen) {
                    return false;
                } else {

                    isDotSeen = true;
                }
            } else if (cand == ' ') {
                //we have already removed the spaces, return false now
                return false;
            }
            else {
                return false;
            }
        }
        return (digitCnt > 0);
    }
}
