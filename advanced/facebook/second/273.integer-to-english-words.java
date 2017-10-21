/*
 * [273] Integer to English Words
 *
 * https://leetcode.com/problems/integer-to-english-words
 *
 * algorithms
 * Hard (22.15%)
 * Total Accepted:    42.5K
 * Total Submissions: 192.1K
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
	String[] subTwenty = new String[]{"","One","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Eleven","Twelve","Thirteen","Fourteen","Fifteen","Sixteen","Seventeen","Eighteen","Nineteen"};
	String[] subHundred = new String[]{"","Ten","Twenty","Thirty","Forty","Fifty","Sixty","Seventy","Eighty","Ninety"};
	String[] units = new String[]{"","Thousand","Million","Billion","Trillion"};
	public String numberToWords(int num) {
		//Bug warnings:
		// 1. make sure to always check for spaces BEFORE the current unit.
		// 2. Always make sure processCurrentThousand is there as well as processCurrentHundred.
		// 3. More lines of code will not kill, but simplification will!!!!
		if (num == 0) {
			return "Zero";
		}

		int curUnit = 0;
		String ret = "";
		while(num > 0) {
			int curStage = num %1000;
			if (curStage > 0) {
				String cur = processCurrentThousand(num % 1000);
	        	if(curUnit > 0) {
	        		cur = cur + " " + units[curUnit];
	        	}
	        	if (ret.length() > 0) {
	        		cur =  cur + " ";
	        	}
	        	ret =  cur + ret;
        }
        	curUnit++;
        	num = num / 1000;
        }
        return ret;
    }
    //util functions
    private String processCurrentThousand(int number) {
    	if (number < 100) {
    		return processCurrentHundred(number);
    	} else {
    		int hundreds = number / 100;
    		int remainder = number % 100;
    		String ret = subTwenty[hundreds] + " Hundred";
    		if (remainder > 0) {
    			ret = ret + " "+processCurrentHundred(remainder);
    		}
    		return ret;
    	}	
    		
    	
    }
    private String processCurrentHundred(int number) {
    	if (number == 0) {
    		return "";
    	} else if (number < 20) {
    		return subTwenty[number];
    	} else  {
    		int tens = number / 10;
    		int ones = number % 10;
    		String answer = subTwenty[ones];
    		if (answer.length() > 0) {
    			answer  = " " + answer;
    		}
    		answer = subHundred[tens] + answer;
    		return answer;
    	}
    }
}
