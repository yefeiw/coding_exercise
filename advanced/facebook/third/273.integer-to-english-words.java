/*
 * [273] Integer to English Words
 *
 * https://leetcode.com/problems/integer-to-english-words
 *
 * algorithms
 * Hard (22.35%)
 * Total Accepted:    46.8K
 * Total Submissions: 209.2K
 * Testcase Example:  '123'
 *
 *
 * Convert a non-negative integer to its english words representation. Given
 * input is guaranteed to be less than 231 - 1.
 *
 *
 * For example,
 *
 * 123 -> "One Hundred Twenty Three"
 * 12345 -> "Twelve Thousand Three Hundred Forty Five"
 * 1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty
 * Seven"
 */
class Solution {
    private String[] belowTwenty = {"","One","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten",
"Eleven","Twelve","Thirteen","Fourteen","Fifteen","Sixteen","Seventeen","Eighteen","Nineteen"};
    private String[] tens = {"","Ten","Twenty","Thirty","Forty","Fifty","Sixty","Seventy","Eighty","Ninety"};
    private String[] units = {"","Thousand","Million","Billion","Trillion"};
    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        StringBuilder sb = new StringBuilder();
        int unit = 0;
        while (num > 0) {
            int cur = num % 1000;
            String buf = outputCurThousand(cur);
            if (buf.length() > 0) {
                sb.insert(0,buf+units[unit]);
            }
            unit++;
            num  = num / 1000;
        }
        //before returning, insert spaces to the result
        for (int i = 0; i < sb.length(); i++) {
            if (i > 0 && Character.isUpperCase(sb.charAt(i))) {
                sb.insert(i," ");
                i++;
            }
        }
        return sb.toString();
    }

    //util functions
    private String outputCurThousand (int input) {
        if (input == 0) {
            return "";
        }
        String result = "";
        int hundreds = input /100;
        if (hundreds > 0) {
            result = result + belowTwenty[hundreds]+"Hundred";
        }
        input = input % 100;
        int tensDigit = input / 10;
        if (tensDigit >= 2) {
            result = result + tens[tensDigit];
            input = input % 10;
        }
        result = result + belowTwenty[input];
        return result;
    }
}
