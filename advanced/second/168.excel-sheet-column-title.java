/*
 * [168] Excel Sheet Column Title
 *
 * https://leetcode.com/problems/excel-sheet-column-title
 *
 * algorithms
 * Easy (26.18%)
 * Total Accepted:    111.3K
 * Total Submissions: 425.1K
 * Testcase Example:  '1'
 *
 * Given a positive integer, return its corresponding column title as appear in
 * an Excel sheet.
 * 
 * For example:
 * 
 * ⁠   1 -> A
 * ⁠   2 -> B
 * ⁠   3 -> C
 * ⁠   ...
 * ⁠   26 -> Z
 * ⁠   27 -> AA
 * ⁠   28 -> AB 
 * 
 * Credits:Special thanks to @ifanchu for adding this problem and creating all
 * test cases.
 */
class Solution {
    public String convertToTitle(int n) {
        if (n < 1) {
        	return "";
        }
        StringBuilder sb =  new StringBuilder();
        while(n > 0) {
         	int letter = (n-1) % 26;
        	sb.insert(0,(char)('A'+letter));
        	//BUGWARNING: what do you do with 26?
        	n = (n-1) / 26;
        }
        return sb.toString();
    }
}
