/*
 * [85] Maximal Rectangle
 *
 * https://leetcode.com/problems/maximal-rectangle
 *
 * algorithms
 * Hard (28.16%)
 * Total Accepted:    71.1K
 * Total Submissions: 252.5K
 * Testcase Example:  '["10100","10111","11111","10010"]'
 *
 * 
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle
 * containing only 1's and return its area.
 * 
 * 
 * For example, given the following matrix:
 * 
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * 
 * Return 6.
 * 
 */
class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length  == 0 || matrix[0].length == 0) {
        	return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int ret = 0;
        int[] heights = new int[n];
        for(int i = 0; i < m ;i++) {
        	char[] line = matrix[i];
        	update(heights, line);
        	ret = Math.max(ret, largestRectangle(heights));
        }
        return ret;
    }
    //util function
    private void update(int[] heights, char[] line) {
    	for(int i = 0; i < line.length; i++) {
    		if(line[i] =='1') heights[i]++;
    		else heights[i] = 0;
    	}
    }
    private int largestRectangle(int[] heights) {
    	Deque<Integer> stack = new ArrayDeque<>();
    	int ret = 0;
    	int i = 0;
    	while (i < heights.length) {
    		if (stack.isEmpty() || heights[i] > heights[stack.peek()]) {
    			//current height is larger, push onto the stack and continue;
    			stack.push(i);
    			i++;
    		} else {
    			int recent = stack.pop();
    			//idea: after drawing the diagram, we know that from i-1 to peek, the height is all heigths[recent]
    			int width = (stack.isEmpty() ? i : i - stack.peek() -1);
    			int curArea = width*heights[recent];
    			ret = Math.max(curArea, ret);
    		}
    	}
    	//here there must be something left, and i is now at the last element
    	while(!stack.isEmpty()) {
    		int recent = stack.pop();
    		int width = (stack.isEmpty() ? i : i - stack.peek() - 1);
    		int curArea = width * heights[recent];
    		ret = Math.max(curArea, ret);
    	}
    	return ret;
    }
}
