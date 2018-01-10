/*
 * [537] Complex Number Multiplication
 *
 * https://leetcode.com/problems/complex-number-multiplication/description/
 *
 * algorithms
 * Medium (63.95%)
 * Total Accepted:    19.2K
 * Total Submissions: 30K
 * Testcase Example:  '"1+1i"\n"1+1i"'
 *
 *
 * Given two strings representing two complex numbers.
 *
 *
 * You need to return a string representing their multiplication. Note i2 = -1
 * according to the definition.
 *
 *
 * Example 1:
 *
 * Input: "1+1i", "1+1i"
 * Output: "0+2i"
 * Explanation: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i, and you need convert
 * it to the form of 0+2i.
 *
 *
 *
 * Example 2:
 *
 * Input: "1+-1i", "1+-1i"
 * Output: "0+-2i"
 * Explanation: (1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i, and you need convert
 * it to the form of 0+-2i.
 *
 *
 *
 * Note:
 *
 * The input strings will not have extra blank.
 * The input strings will be given in the form of a+bi, where the integer a and
 * b will both belong to the range of [-100, 100]. And the output should be
 * also in this form.
 *
 *
 */
class Solution {
    public String complexNumberMultiply(String a, String b) {
        int[] numberA = decode(a);
        int[] numberB = decode(b);
        int[] result = calculate(numberA,numberB);
        return encode(result);
    }

    public int[] decode(String input) {
        String[] buf = input.split("\\+");
        int[] ret = new int[2];
        ret[0] = Integer.parseInt(buf[0]);
        ret[1] = Integer.parseInt(buf[1].substring(0,buf[1].length()-1));
        return ret;
    }

    public String encode(int[] input) {
        return Integer.toString(input[0])+"+"+Integer.toString(input[1])+"i";
    }

    public int[] calculate(int[] a, int[] b) {
        int[] ret = new int[2];
        ret[0] = a[0]*b[0] - a[1]*b[1];
        ret[1] = a[1]*b[0] + a[0]*b[1];

        return ret;
    }
}
