/*
 * [311] Sparse Matrix Multiplication
 *
 * https://leetcode.com/problems/sparse-matrix-multiplication
 *
 * algorithms
 * Medium (50.83%)
 * Total Accepted:    31.5K
 * Total Submissions: 62K
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
        int[][] C = new int[A.length][B[0].length];
        for (int i = 0; i < A.length; i++) {
        	for (int j = 0; j < A[0].length; j++) {
        		//idea: since this is a sparse matrix, don't do it if A[i][j] is zero.
        		if (A[i][j] != 0) {
        			for (int k = 0; k < B[0].length; k++) {
        				C[i][k] += A[i][j] * B[j][k];
        			}
        		}
        	}
        }
        return C;
    }
}
