/*
 * [311] Sparse Matrix Multiplication
 *
 * https://leetcode.com/problems/sparse-matrix-multiplication
 *
 * algorithms
 * Medium (51.07%)
 * Total Accepted:    33.2K
 * Total Submissions: 65K
 * Testcase Example:  '[[1,0,0],[-1,0,3]]\n[[7,0,0],[0,0,0],[0,0,1]]'
 *
 * Given two sparse matrices A and B, return the result of AB.
 * 
 * You may assume that A's column number is equal to B's row number.
 * 
 * Example:
 * 
 * 
 * A = [
 * ⁠ [ 1, 0, 0],
 * ⁠ [-1, 0, 3]
 * ]
 * 
 * B = [
 * ⁠ [ 7, 0, 0 ],
 * ⁠ [ 0, 0, 0 ],
 * ⁠ [ 0, 0, 1 ]
 * ]
 * 
 * 
 * ⁠    |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
 * AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
 * ⁠                 | 0 0 1 |
 * 
 */
class Solution {
    public int[][] multiply(int[][] A, int[][] B) {
        int m = A.length;
        int n = A[0].length;
        int t = B[0].length;
        int[][] ret = new int[m][t];
        for (int i = 0; i < m; i++) {
        	for(int j =0; j < n; j++) {
        		if(A[i][j] != 0) {
        			for (int k = 0; k < t; k++) {
        				ret[i][k] += A[i][j]*B[j][k];
        			}
        		}
        	}
        }
        return ret;
    }
}
