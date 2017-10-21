/*
 * [301] Remove Invalid Parentheses
 *
 * https://leetcode.com/problems/remove-invalid-parentheses
 *
 * algorithms
 * Hard (35.18%)
 * Total Accepted:    48.5K
 * Total Submissions: 137.7K
 * Testcase Example:  '"()())()"'
 *
 * 
 * Remove the minimum number of invalid parentheses in order to make the input
 * string valid. Return all possible results.
 * 
 * Note: The input string may contain letters other than the parentheses ( and
 * ). 
 * 
 * 
 * 
 * Examples:
 * 
 * "()())()" -> ["()()()", "(())()"]
 * "(a)())()" -> ["(a)()()", "(a())()"]
 * ")(" -> [""]
 * 
 * 
 * 
 * Credits:Special thanks to @hpplayer for adding this problem and creating all
 * test cases.
 */
class Solution {
    public List<String> removeInvalidParentheses(String s) {
    	List<String> ans = new ArrayList<>();
    	dfsHelper(s,'(',0,ans);
    	return ans;
    }

    private void dfsHelper(String s, char ch, int last, List<String> ans) {
    	for(int i = 0, cnt = 0; i < s.length(); i++) {
    		if (s.charAt(i) == '(' || s.charAt(i) == ')') {
    			if (s.charAt(i) == ch) cnt++;
    			else cnt--;
    		}
    		if (cnt <= 0) continue;
    		for(int j = last; j <=i; j++) {
    			if (s.charAt(j) == ch && (j== last || s.charAt(j-1) != ch)) {
    				dfsHelper(s.substring(0,j)+s.substring(j+1), ch, j, ans);
    			}
    		}
    		return;
    	}
    	s = new StringBuilder(s).reverse().toString();
    	dfsHelper(s,')',0,ans);
    	ans.add(s);
    }
}
