/*
 * [305] Number of Islands II
 *
 * https://leetcode.com/problems/number-of-islands-ii/description/
 *
 * algorithms
 * Hard (39.72%)
 * Total Accepted:    35.2K
 * Total Submissions: 88.7K
 * Testcase Example:  '3\n3\n[[0,0],[0,1],[1,2],[2,1]]'
 *
 * A 2d grid map of m rows and n columns is initially filled with water. We may
 * perform an addLand operation which turns the water at position (row, col)
 * into a land. Given a list of positions to operate, count the number of
 * islands after each addLand operation. An island is surrounded by water and
 * is formed by connecting adjacent lands horizontally or vertically. You may
 * assume all four edges of the grid are all surrounded by water.
 * 
 * Example:
 * 
 * 
 * Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
 * Output: [1,1,2,3]
 * 
 * 
 * Explanation:
 * 
 * Initially, the 2d grid grid is filled with water. (Assume 0 represents water
 * and 1 represents land).
 * 
 * 
 * 0 0 0
 * 0 0 0
 * 0 0 0
 * 
 * 
 * Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.
 * 
 * 
 * 1 0 0
 * 0 0 0   Number of islands = 1
 * 0 0 0
 * 
 * 
 * Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.
 * 
 * 
 * 1 1 0
 * 0 0 0   Number of islands = 1
 * 0 0 0
 * 
 * 
 * Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.
 * 
 * 
 * 1 1 0
 * 0 0 1   Number of islands = 2
 * 0 0 0
 * 
 * 
 * Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.
 * 
 * 
 * 1 1 0
 * 0 0 1   Number of islands = 3
 * 0 1 0
 * 
 * 
 * Follow up:
 * 
 * Can you do it in time complexity O(k log mn), where k is the length of the
 * positions?
 * 
 */
class Solution {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
       List<Integer> ret = new ArrayList<>();
       if (m == 0 || n == 0 || positions.length == 0){
           return ret;
       } 
       UnionFind uf = new UnionFind(m,n);
       for (int[] position : positions) {
           int x = position[0];
           int y = position[1];
           uf.setLand(x,y);
           int[] deltax = {-1,0,1,0};
           int[] deltay = {0,1,0,-1};
           for (int d = 0; d < deltax.length; d++) {
               int newx = x + deltax[d];
               int newy = y + deltay[d];
               if (newx >=0 && newx < m && newy >= 0 && newy < n && uf.isLand(newx, newy)) {
                   int newroot = uf.find(newx, newy);
                   int root = uf.find(x, y);
                   uf.union(root, newroot);

               }
           }
           ret.add(uf.count());
       }
       return ret;
    }

    class UnionFind {
        int[][] values;
        int[] root;
        int cnt;

        public UnionFind(int m, int n) {
            values = new int[m][n];
            root = new int[m*n];
        }

        public void setLand(int x, int y) {
            if (x >= 0 && x < values.length && y >= 0 && y < values[0].length) {
                values[x][y] = 1;
                int id = x*values[0].length + y;
                root[id] = id;
                cnt++;
            }
        }

        public boolean isLand(int x, int y) {
            return (values[x][y] == 1);
        }

        public int find(int x, int y) {
            int rowLength = values[0].length;
            int id = x * rowLength + y;
            return find(id);
        }

        private int find(int id) {
            while(id != root[id]) {
                root[id] = root[root[id]];
                id = root[id];
            }
            return root[id];
        }

        public void union (int left, int right) {
            int leftRoot = find(left);
            int rightRoot = find(right);
            if (leftRoot != rightRoot) {
                //no ranking for now. Will implement later.
                root[leftRoot] = rightRoot;
                cnt--;
            }
        }
        public int count() {
            return this.cnt;
        }

    }
}
