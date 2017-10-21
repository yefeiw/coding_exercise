/*
 * [282] Expression Add Operators
 *
 * https://leetcode.com/problems/expression-add-operators
 *
 * algorithms
 * Hard (29.79%)
 * Total Accepted:    34K
 * Total Submissions: 114K
 * Testcase Example:  '"123"\n6'
 *
 * 
 * Given a string that contains only digits 0-9 and a target value, return all
 * possibilities to add binary operators (not unary) +, -, or * between the
 * digits so they evaluate to the target value.
 * 
 * 
 * Examples: 
 * "123", 6 -> ["1+2+3", "1*2*3"] 
 * "232", 8 -> ["2*3+2", "2+3*2"]
 * "105", 5 -> ["1*0+5","10-5"]
 * "00", 0 -> ["0+0", "0-0", "0*0"]
 * "3456237490", 9191 -> []
 * 
 * 
 * Credits:Special thanks to @davidtan1890 for adding this problem and creating
 * all test cases.
 */
class Solution {
    public List<String> addOperators(String num, int target) {
    	if (num.length() == 0) {
    		return new ArrayList<>();
    	}
        List<String> ans =  new ArrayList<>();
        dfs(num,target,0, 0, "", ans);
        return ans;
    }
    //util functions
    //BUGWARNING: curValue and last should be long to avoid overflow
    private void dfs(String num, int target, long curValue, long last, String path, List<String> ans) {
    	//curValue: the current Value of path
    	//last: last element in the path
    	if (num.length() == 0) {
    		if (curValue == target) {
    			ans.add(path);
    		}
    		return;	
    	}
    	for(int i = 1; i <= num.length(); i++) {
    		String prefix = num.substring(0,i);
    		if (prefix.length() > 1 && num.charAt(0) =='0') {
    			return;
    		}
    		String suffix = num.substring(i);
    		long curNum = Long.parseLong(prefix);
    		if (path.length() > 0) {
    			dfs(suffix, target, curValue+curNum, curNum, path+"+"+prefix, ans);
    			dfs(suffix, target, curValue - curNum, -curNum, path+"-"+prefix, ans);
    			dfs(suffix, target, curValue - last + last * curNum, last*curNum, path+"*"+prefix, ans);
    		} else {
    			dfs(suffix, target, curValue+curNum, curNum, prefix, ans);
    		}
    	}
    }
}
