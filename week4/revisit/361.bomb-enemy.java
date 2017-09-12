/*
 * [361] Bomb Enemy
 *
 * https://leetcode.com/problems/bomb-enemy
 *
 * algorithms
 * Medium (38.87%)
 * Total Accepted:    16.9K
 * Total Submissions: 43.4K
 * Testcase Example:  '["0E00","E0WE","0E00"]'
 *
 * Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0'
 * (the number zero), return the maximum enemies you can kill using one bomb.
 * The bomb kills all the enemies in the same row and column from the planted
 * point until it hits the wall since the wall is too strong to be destroyed.
 * ⁠Note that you can only put the bomb at an empty cell. 
 * 
 * Example:
 * 
 * For the given grid
 * 
 * 0 E 0 0
 * E 0 W E
 * 0 E 0 0
 * 
 * return 3. (Placing a bomb at (1,1) kills 3 enemies)
 * 
 * 
 * 
 * Credits:Special thanks to @memoryless for adding this problem and creating
 * all test cases.
 */
class Solution {
    public int maxKilledEnemies(char[][] grid) {
        
        if (grid.length == 0 || grid[0].length == 0) {
        	return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        //general idea: save line and cols.
        //line is constant, since for each line, we will go through every columns.
        //We plan to reuse both the current line and the current columns unless there is wall.
        int ret = 0;
        int row = 0;
        int[] cols = new int[n];
        for(int i = 0; i < m ; i++) {
        	for(int j = 0; j < n ; j++) {
        		//check update conditions for row, if not needed, use the saved result.
        		if(j == 0 || grid[i][j-1] =='W') {
        			//update row
        			int iter = j;
        			row = 0;
        			while(iter < n && grid[i][iter] != 'W') {
        				if (grid[i][iter] == 'E') {
        					row++;
        				}
        				iter++;
        			}
        		}
        		//check update conditions for column. if not needed, use the saved result.
        		if (i == 0 || grid[i-1][j] == 'W') {
        			//update the current column;
        			int iter = i;
        			cols[j] = 0;
        			while(iter < m && grid[iter][j] != 'W') {
        				if (grid[iter][j] == 'E') {
        					cols[j]++;
        				}
        				iter++;
        			}
        		}
        		//at this point, row and cols[j] are both correct. 
        		//check for maximum;
        		if(grid[i][j] =='0') {
        			ret = Math.max(ret, row + cols[j]);
        		}
        	}
        }
        return ret;
    }
}
