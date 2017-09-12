/*
 * [13] Roman to Integer
 *
 * https://leetcode.com/problems/roman-to-integer
 *
 * algorithms
 * Easy (46.01%)
 * Total Accepted:    170.2K
 * Total Submissions: 370K
 * Testcase Example:  '"DCXXI"'
 *
 * Given a roman numeral, convert it to an integer.
 * 
 * Input is guaranteed to be within the range from 1 to 3999.
 */
class Solution {
    public int romanToInt(String s) {
    	int ans = 0;
    	Map<Character, Integer> lookup = new HashMap<Character, Integer>();
    	lookup.put('I',1);
    	lookup.put('V',5);
    	lookup.put('X', 10);
    	lookup.put('L', 50);
    	lookup.put('C',100);
    	lookup.put('D',500);
    	lookup.put('M',1000);

    	for(int i = 0; i < s.length(); i++) {
    		int cand = lookup.get(s.charAt(i));
    		int ref = (i < s.length() - 1) ? lookup.get(s.charAt(i+1)) : 0;
    		if (cand < ref) {
    			ans -= cand;
    		} else {
    			ans += cand;
    		}
    	}

    	return ans;
    }
}
