import java.util.Deque;

/*
 * [317] Shortest Distance from All Buildings
 *
 * https://leetcode.com/problems/shortest-distance-from-all-buildings/description/
 *
 * algorithms
 * Hard (34.95%)
 * Total Accepted:    26.4K
 * Total Submissions: 75.4K
 * Testcase Example:  '[[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]'
 *
 * You want to build a house on an empty land which reaches all buildings in
 * the shortest amount of distance. You can only move up, down, left and right.
 * You are given a 2D grid of values 0, 1 or 2, where:
 * 
 * 
 * Each 0 marks an empty land which you can pass by freely.
 * Each 1 marks a building which you cannot pass through.
 * Each 2 marks an obstacle which you cannot pass through.
 * 
 * 
 * Example:
 * 
 * 
 * Input: [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
 * 
 * 1 - 0 - 2 - 0 - 1
 * |   |   |   |   |
 * 0 - 0 - 0 - 0 - 0
 * |   |   |   |   |
 * 0 - 0 - 1 - 0 - 0
 * 
 * Output: 7 
 * 
 * Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle
 * at (0,2),
 * ⁠            the point (1,2) is an ideal empty land to build a house, as the
 * total 
 * travel distance of 3+3+1=7 is minimal. So return 7.
 * 
 * Note:
 * There will be at least one building. If it is not possible to build such
 * house according to the above rules, return -1.
 * 
 */
class Solution {
    public int shortestDistance(int[][] grid) {
            if (grid.length == 0 || grid[0].length == 0) {
                return 0;
            }
            int m = grid.length;
            int n = grid[0].length;
            int[][] numReachedBuildings = new int[m][n];
            int[][] totalDistance = new int[m][n];
            int cnt = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        //here we have found a building
                        cnt++;
                        bfs(i, j, grid, numReachedBuildings, totalDistance, cnt);
                    }
                }
            }
            //After updating all distances, trying to see which grid is the one that is reachable to all buildings while having the shortest distance.
            int result = Integer.MAX_VALUE;
            for (int i = 0; i < m ; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 0 && numReachedBuildings[i][j] == cnt) {
                        result = Math.min(result, totalDistance[i][j]);
                    }
                }
            }

            return (result == Integer.MAX_VALUE) ? -1 : result;
    }

    private void bfs(int startI, int startJ, int[][] grid,  int[][] numReachedBuildings, int[][] totalDistance, int cnt) {
        final int[][] deltas = new int[][]{{0,1},{0,-1}, {1,0}, {-1,0}};
        int step = 0;
        int m = grid.length, n = grid[0].length;
        Deque<Integer[]> queue = new ArrayDeque<>();
        queue.offer(new Integer[]{startI, startJ});
        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int i = 0;i < size; i++) {
                Integer[] front = queue.remove();
                for (int[] delta : deltas) {
                    Integer[] cand = new Integer[]{front[0]+delta[0], front[1]+ delta[1]};
                    if (cand[0] < 0 || cand[0] >= m || cand[1] < 0 || cand[1] >= n || grid[cand[0]][cand[1]] != 0 || numReachedBuildings[cand[0]][cand[1]] != cnt -1) {
                        continue;
                    }
                    numReachedBuildings[cand[0]][cand[1]]++;
                    totalDistance[cand[0]][cand[1]] += step;
                    queue.add(cand);
                }
            }
        }
    }
}
