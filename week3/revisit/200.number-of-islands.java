public class Solution {
        class Location {
            int x;
            int y;
           public  Location(int x, int y) {
                this.x = x;
                this.y = y;
            }
            public void move(int dx, int dy) {
                this.x += dx;
                this.y += dy;
            }
        }
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length ==0) {
            return 0;
        }
        int ret = 0;//returning number of islands
        for (int i = 0; i < grid.length; i++) {
            for (int j  = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    bfsHelper(grid, i,j);
                    ret++;
                }
            }
        }
        return ret;
    }
    void bfsHelper(char[][] grid, int x, int y) {
        int[] dx = {0,1,0,-1};
        int[] dy = {1,0,-1,0};
        Deque<Location> queue = new ArrayDeque<Location>();
        queue.add(new Location(x,y));
        while (!queue.isEmpty()) {
            Location front =  queue.remove();
            for (int i = 0; i < dx.length; i++) {
                int nx = front.x + dx[i];
                int ny = front.y + dy[i];
                if (isValid(grid, nx, ny) && grid[nx][ny] == '1') {
                    queue.add(new Location(nx,ny));
                    grid[nx][ny] = '2';
                }
            }
        }
    }
    boolean isValid(char[][] grid, int x, int y) {
        if (x < 0 || x >= grid.length) return false;
        if (y < 0 || y >= grid[0].length) return false;
        return true;
    }
}
