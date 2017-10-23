/*
* [417] Pacific Atlantic Water Flow
*
* https://leetcode.com/problems/pacific-atlantic-water-flow
*
* algorithms
* Medium (33.82%)
* Total Accepted:    18.7K
* Total Submissions: 55.4K
* Testcase Example:  '[[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]'
*
* Given an m x n matrix of non-negative integers representing the height of
* each unit cell in a continent, the "Pacific ocean" touches the left and top
* edges of the matrix and the "Atlantic ocean" touches the right and bottom
* edges.
*
* Water can only flow in four directions (up, down, left, or right) from a
* cell to another one with height equal or lower.
*
* Find the list of grid coordinates where water can flow to both the Pacific
* and Atlantic ocean.
*
* Note:
*
* The order of returned grid coordinates does not matter.
* Both m and n are less than 150.
*
*
* Example:
*
* Given the following 5x5 matrix:
*
* ⁠ Pacific ~   ~   ~   ~   ~
* ⁠      ~  1   2   2   3  (5) *
* ⁠      ~  3   2   3  (4) (4) *
* ⁠      ~  2   4  (5)  3   1  *
* ⁠      ~ (6) (7)  1   4   5  *
* ⁠      ~ (5)  1   1   2   4  *
* ⁠         *   *   *   *   * Atlantic
*
* Return:
*
* [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with
* parentheses in above matrix).
*
*
*/
class Solution {
    public List<int[]> pacificAtlantic(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return new ArrayList<>();
        }
        int[] dx = {-1,0,1,0};
        int[] dy = {0,-1,0,1};

        int m = matrix.length;
        int n = matrix[0].length;

        List<int[]> ret = new ArrayList<>();

        boolean[][] canFlowPacific = new boolean[m][n];
        boolean[][] canFlowAtlantic = new boolean[m][n];



        for(int i = 0; i < m; i++) {
            canFlowPacific[i][0] = true;
        }
        for (int j = 0; j < n; j++) {
            canFlowPacific[0][j] = true;
        }
        for(int i  = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i-1][j] <= matrix[i][j]) {
                    canFlowPacific[i][j] |= canFlowPacific[i-1][j];
                }
                if (matrix[i][j-1] <= matrix[i][j]) {
                    canFlowPacific[i][j] |= canFlowPacific[i][j-1];
                }
            }
        }
        for (int i = 0; i < m; i++) {
            canFlowAtlantic[i][n-1] = true;
        }
        for (int j = 0; j < n; j++) {
            canFlowAtlantic[m-1][j] = true;
        }
        for(int i = m-2; i >= 0; i--) {
            for (int j= n-2; j >= 0; j--) {
                if (matrix[i+1][j] <= matrix[i][j]) {
                    canFlowAtlantic[i][j] |= canFlowAtlantic[i+1][j];
                }
                if (matrix[i][j+1] <= matrix[i][j]) {
                    canFlowAtlantic[i][j] |= canFlowAtlantic[i][j+1];
                }
            }
        }
        for(int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (canFlowAtlantic[i][j] && canFlowPacific[i][j]) {
                    ret.add(new int[]{i,j});
                }
            }
        }


        return ret;
    }
}
