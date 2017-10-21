/*
 * [43] Multiply Strings
 *
 * https://leetcode.com/problems/multiply-strings
 *
 * algorithms
 * Medium (26.99%)
 * Total Accepted:    106.9K
 * Total Submissions: 395.9K
 * Testcase Example:  '"0"\n"0"'
 *
 * Given two non-negative integers num1 and num2 represented as strings, return
 * the product of num1 and num2.
 * 
 * Note:
 * 
 * The length of both num1 and num2 is < 110.
 * Both num1 and num2 contains only digits 0-9.
 * Both num1 and num2 does not contain any leading zero.
 * You must not use any built-in BigInteger library or convert the inputs to
 * integer directly.
 * 
 * 
 */
public class Solution {
	public String multiply(String num1, String num2) {
		num1 = new StringBuilder(num1).reverse().toString();
		num2 = new StringBuilder(num2).reverse().toString();
		int m = num1.length();
		int n = num2.length();
		int[] digits = new int[m+n];
        //calculate digits
		for(int i = 0; i < m ; i++) {
			int digit1 = num1.charAt(i)- '0';
			for (int j = 0; j < n; j++) {
				int digit2 = num2.charAt(j) - '0';
				digits[i+j] += digit1 * digit2;
			}
		}
        //translate
		int carry = 0;
		for (int i = 0; i < m+n; i++) {
			digits[i] += carry;
			int value = digits[i] %10;
			carry = digits[i] / 10;
			digits[i] =  value;
		}
        //output
		StringBuilder sb = new StringBuilder();
		for (int i = m+n-1; i >=0; i--) {
			sb.append(digits[i]);
		}
		while(sb.length() > 0 && sb.charAt(0) == '0') sb.deleteCharAt(0);
		return (sb.length() == 0)? "0" : sb.toString();
	}
}
	