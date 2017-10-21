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
        StringBuilder ret = new StringBuilder();
        int[] buffers = new int[a.length()+b.length()];
        for(int i = 0; i < Math.max(a.length(), b.length()); i++) {
        	int x = (i < a.length()) ? a.charAt(i) - '0' : 0;
        	int y = (i < b.length()) ? b.charAt(i) - '0' : 0;
        	buffers[i] = x+y;	
        }
        int carry = 0;
        for(int i = 0; i < buffers.length; i++) {
        	buffers[i]+=carry;
        	int val = buffers[i] & 1;
        	carry = buffers[i] >> 1;
        	ret.insert(0,val >0 ? '1' :'0');
        }
        while(ret.length() > 0 && ret.charAt(0) =='0') {
        	ret.deleteCharAt(0);
        }
        return (ret.length() == 0) ? "0": ret.toString();

    }
}
