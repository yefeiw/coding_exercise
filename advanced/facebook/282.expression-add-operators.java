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
    	List<String> result = new ArrayList<>();
        dfsHelper(num,target,0,0,"",result);
        return result;
    }
    private void dfsHelper(String num, int target, long curValue, long last, String path, List<String> result) {
    	if (num.length() == 0) {
    		if (curValue == target) {
    			result.add(path);
    		}
    		return;
    	}
    	//Consider all possibilities
    	for (int i = 1; i <= num.length(); i++) {
    		String prefix = num.substring(0,i);
    		//BUGWARNING: if there are multiple digits, the first one could not be zero.
    		if (prefix.length() > 1 && prefix.charAt(0) =='0') {
    			return;
    		}
    		String suffix = num.substring(i);
    		long currNum = Long.parseLong(prefix);
    		if (path.length() > 0) {
    			//if this is not the first number
    			//multiplication
    			dfsHelper(suffix, target, (curValue - last) + last * currNum, last * currNum, path + "*" + currNum,result);
    			//addition
    			dfsHelper(suffix, target, curValue + currNum, currNum, path + "+" + currNum, result);
    			//substraction
    			dfsHelper(suffix, target, curValue - currNum, -currNum, path + "-" + currNum, result);
    		} else {
    			//if this is the first number, always addition
    			dfsHelper(suffix, target, currNum, currNum, prefix, result);
    		}
    	}

    }


}
