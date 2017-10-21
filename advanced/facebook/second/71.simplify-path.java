/*
 * [71] Simplify Path
 *
 * https://leetcode.com/problems/simplify-path
 *
 * algorithms
 * Medium (25.41%)
 * Total Accepted:    93.6K
 * Total Submissions: 368.2K
 * Testcase Example:  '"/"'
 *
 * Given an absolute path for a file (Unix-style), simplify it.
 * 
 * For example,
 * path = "/home/", => "/home"
 * path = "/a/./b/../../c/", => "/c"
 * 
 * 
 * click to show corner cases.
 * 
 * Corner Cases:
 * 
 * 
 * 
 * Did you consider the case where path = "/../"?
 * In this case, you should return "/".
 * Another corner case is the path might contain multiple slashes '/' together,
 * such as "/home//foo/".
 * In this case, you should ignore redundant slashes and return "/home/foo".
 * 
 * 
 */
class Solution {
    public String simplifyPath(String path) {
        if (path.length() == 0) {
        	return "/";
        }
        String[] dirs = path.split("/");
        Deque<String> stack = new ArrayDeque<>();
        for (int i = 0; i < dirs.length; i++) {
        	String cand = dirs[i];
        	//BUGWARNING: there might be empty strings
        	if (cand.equals(".")||cand.length() == 0) {
        		continue;
        	}else if (cand.equals("..")) {
        		if (!stack.isEmpty()) {
        			stack.pop();
        		}
        	} else {
        		stack.push(cand);
        	}
        }
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
        	String cur = stack.removeLast();
        	if (cur.length() > 0) {
        		sb.append("/");
        		sb.append(cur);
        	}
        }
        return (sb.length() == 0) ? "/" : sb.toString();
    }
}
