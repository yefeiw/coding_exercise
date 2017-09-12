/*
 * [28] Implement strStr()
 *
 * https://leetcode.com/problems/implement-strstr
 *
 * algorithms
 * Easy (28.24%)
 * Total Accepted:    204.6K
 * Total Submissions: 724.5K
 * Testcase Example:  '""\n""'
 *
 * 
 * Implement strStr().
 * 
 * 
 * Returns the index of the first occurrence of needle in haystack, or -1 if
 * needle is not part of haystack.
 * 
 */
class Solution {
    public int strStr(String haystack, String needle) {
    	if(needle.length() == 0) return 0;
    	if(haystack.length() == 0) return -1;
        int ret = -1;
        String buffer = needle + "#" + haystack;
        return kmpHelper(buffer, needle.length()+1);
    }
    private int kmpHelper(String buffer, int start) {
    	int[] border = new int[buffer.length()];
    	int curBorder = 0;
    	for(int i = 1; i < border.length; i++) {
    		while (curBorder > 0 && buffer.charAt(i)!= buffer.charAt(curBorder)) {
    			curBorder = border[curBorder-1];
    		}
    		if(buffer.charAt(i) == buffer.charAt(curBorder)) {
    			curBorder++;
    		} else {
    			curBorder = 0;
    		}
    		border[i] = curBorder;
    		//break position
    		if (border[i] == start - 1) {
    			// System.out.println("found match at "+i);
    			return i-(start-1) - start+1;
    		}
    	}
    	//reaching here means there is no match
    	return -1;
    }

}
