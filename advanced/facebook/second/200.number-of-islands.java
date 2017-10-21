/*
 * [200] Number of Islands
 *
 * https://leetcode.com/problems/number-of-islands
 *
 * algorithms
 * Medium (34.80%)
 * Total Accepted:    121.3K
 * Total Submissions: 348.4K
 * Testcase Example:  '["11110","11010","11000","00000"]'
 *
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of
 * islands. An island is surrounded by water and is formed by connecting
 * adjacent lands horizontally or vertically. You may assume all four edges of
 * the grid are all surrounded by water.
 * 
 * Example 1:
 * 11110110101100000000
 * Answer: 1
 * Example 2:
 * 11000110000010000011
 * Answer: 3
 * 
 * Credits:Special thanks to @mithmatt for adding this problem and creating all
 * test cases.
 */
class Solution {
    public int numIslands(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
        	return 0;
        }
        int ret = 0;
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n ; j++) {
        		if (grid[i][j] == '1') {
        			ret++;
        			bfsHelper(grid, i, j);
        		}
        	}
        }
        return ret;
    }
    //util function
    private void bfsHelper(char[][] grid, int i, int j) {
    	int m = grid.length;
    	int n = grid[0].length;
    	long[] dx = {0,1,0,-1};
    	long[] dy = {1,0,-1,0};
    	Deque<Long> queue = new ArrayDeque<>();
    	queue.add((long)i*n+j);
    	while(!queue.isEmpty()) {
    		Long front = queue.remove();
    		long x = front / n;
    		long y = front % n;
    		grid[(int)x][(int)y] = '2';
    		for(int d = 0; d < dx.length; d++) {
    			long newx = x + dx[d];
    			long newy = y + dy[d];
    			if (newx < 0 || newx >= m || newy < 0 || newy >= n) continue;
    			if (grid[(int)newx][(int)newy] == '1') {
    				queue.add((long)newx * n + newy);
    			}
    		}
    	}
    }
}
