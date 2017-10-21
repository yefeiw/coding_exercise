public class Solution {
    public String multiply(String num1, String num2) {
        num1 = new StringBuilder(num1).reverse().toString();
        num2 = new StringBuilder(num2).reverse().toString();

        int[] digits = new int[num1.length()+ num2.length()];
        for(int i = 0; i < num1.length(); i++) {
        	int d1 = num1.charAt(i) - '0';
        	for(int j = 0; j < num2.length(); j++) {
        		//key point: how to calculate
        		int d2 = num2.charAt(j) - '0';
        		digits[i+j] += d1 * d2;
        	}
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < digits.length; i++) {
        	int number = digits[i] % 10;
        	int carry = digits[i] / 10;
        	sb.insert(0,number);
        	if (i+1 < digits.length) {
        		digits[i+1] += carry;
        	}
        }

        while(sb.length() > 0 && sb.charAt(0) =='0') {
        	sb.deleteCharAt(0);
        }
        return (sb.length() == 0) ? "0" : sb.toString();


    }
}