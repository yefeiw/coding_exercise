/*
 * [17] Letter Combinations of a Phone Number
 *
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number
 *
 * algorithms
 * Medium (34.70%)
 * Total Accepted:    170.3K
 * Total Submissions: 490.7K
 * Testcase Example:  '""'
 *
 * Given a digit string, return all possible letter combinations that the
 * number could represent.
 * 
 * 
 * 
 * A mapping of digit to letters (just like on the telephone buttons) is given
 * below.
 * 
 * 
 * 
 * Input:Digit string "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 
 * 
 * 
 * Note:
 * Although the above answer is in lexicographical order, your answer could be
 * in any order you want.
 * 
 */
class Solution {
	private static final String[] map = 
	{"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    public List<String> letterCombinations(String digits) {
        List<String> ret =  new ArrayList<>();
        if(digits.length() == 0) {
        	return ret;
        }
        StringBuilder sb = new StringBuilder();
        backtrackHelper(digits, 0, ret, sb);
        return ret;
    }
    private void backtrackHelper(String digits, int pos, List<String> ret, StringBuilder sb) {
    	int m = digits.length();
    	if (pos >= m) {
    		ret.add(sb.toString());
    		return;
    	}
    	String range = map[digits.charAt(pos) - '0'];
    	for(int i = 0; i < range.length(); i++) {
    		sb.append(range.charAt(i));
    		backtrackHelper(digits, pos+1, ret, sb);
    		sb.deleteCharAt(sb.length() -1 );
    	}
    }

}
