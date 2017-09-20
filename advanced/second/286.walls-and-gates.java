/*
 * [286] Walls and Gates
 *
 * https://leetcode.com/problems/walls-and-gates
 *
 * algorithms
 * Medium (44.33%)
 * Total Accepted:    34.8K
 * Total Submissions: 78.6K
 * Testcase Example:  '[[2147483647,-1,0,2147483647],[2147483647,2147483647,2147483647,-1],[2147483647,-1,2147483647,-1],[0,-1,2147483647,2147483647]]'
 *
 * 
 * You are given a m x n 2D grid initialized with these three possible
 * values.
 * 
 * 
 * -1 - A wall or an obstacle.
 * 0 - A gate.
 * INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to
 * represent INF as you may assume that the distance to a gate is less than
 * 2147483647.
 * 
 * 
 * 
 * Fill each empty room with the distance to its nearest gate. If it is
 * impossible to reach a gate, it should be filled with INF.
 * 
 * 
 * For example, given the 2D grid:
 * 
 * INF  -1  0  INF
 * INF INF INF  -1
 * INF  -1 INF  -1
 * ⁠ 0  -1 INF INF
 * 
 * 
 * 
 * After running your function, the 2D grid should be:
 * 
 * ⁠ 3  -1   0   1
 * ⁠ 2   2   1  -1
 * ⁠ 1  -1   2  -1
 * ⁠ 0  -1   3   4
 * 
 */
class Solution {
    public void wallsAndGates(int[][] rooms) {
        if (rooms.length == 0 || rooms[0].length == 0) {
        	return;
        }
        int m = rooms.length;
        int n = rooms[0].length;
        Deque<Integer[]> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n ; j++) {
        		if (rooms[i][j] == 0) {
        			queue.add(new Integer[]{i,j});
        		}
        	}
        }
        bfsHelper(queue, rooms);
    }
    //util functions
    void bfsHelper(Deque<Integer[]> queue, int[][] rooms) {
    	int m = rooms.length;
    	int n = rooms[0].length;
    	int[] dx = new int[]{0,1,0,-1};
    	int[] dy = new int[]{1,0,-1,0};
    	while(!queue.isEmpty()) {
    		Integer[] front = queue.remove();
    		int val = rooms[front[0]][front[1]] + 1;
    		for(int d = 0; d < 4; d++) {
    			int nextx = front[0] + dx[d];
    			int nexty = front[1] + dy[d];
    			if(nextx < 0 || nextx >= m || nexty < 0 || nexty >= n ) {
    				continue;
    			}
    			if (rooms[nextx][nexty] > val) {
    				rooms[nextx][nexty] = val;
    				queue.add(new Integer[]{nextx,nexty});
    			}
    		}
    	}
    }

}
