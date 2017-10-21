/*
* [85] Maximal Rectangle
*
* https://leetcode.com/problems/maximal-rectangle
*
* algorithms
* Hard (28.15%)
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
    if (matrix.length == 0 || matrix[0].length == 0) {
      return 0;
    }
    //constants
    int m = matrix.length;
    int n = matrix[0].length;
    //return value
    int ret = 0;
    int[] height = new int[n];
    for(int i = 0; i < m; i++) {
      updateHeight(matrix[i],height);
      int localMax = getMaxRectangle(height);
      //System.out.printf("result for line %d is %d\n",i,localMax);
      ret  = Math.max(ret,localMax);
    }
    return ret;
  }
  //util functions
  private void updateHeight(char[] line, int[] height) {
    for(int i = 0; i < line.length; i++) {
      if (line[i] == '1') {
        height[i]++;
      } else {
        height[i] = 0;
      }
    }
  }

  private int getMaxRectangle(int[] height) {
    Deque<Integer> stack = new ArrayDeque<>();
    int ret = 0;
    for(int i = 0; i < height.length; i++) {
      while(!stack.isEmpty() && height[i] <= height[stack.peek()]) {
        int recent = stack.pop();
        //BUG; need to carefully think about the maximum length here.
        ret = Math.max(ret, height[recent] * ((stack.isEmpty()  ? i : i - stack.peek() - 1)));
      }
      stack.push(i);
    }
    //calculate once more at the very end;
    if (!stack.isEmpty()) {
      //BUG: here the last element will always be inserted. so the end point will always be the upper bound of the array.
      int endpoint = height.length;
      while(!stack.isEmpty()) {
        int recent = stack.pop();
        ret = Math.max(ret,  height[recent]*(stack.isEmpty() ? endpoint : endpoint - stack.peek() - 1));

      }
    }
    return ret;
  }
}
