public class Solution {
    public int minPathSum(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int[] col = new int[grid[0].length];
        //initiate first line
        col[0] = grid[0][0];
        for(int j = 1; j < grid[0].length; j++) {
            col[j] = grid[0][j] + col[j-1];
        }
        for (int i = 1; i < grid.length; i++) {
            //calculate first item
            col[0] += grid[i][0];
            for (int j = 1; j < grid[i].length; j++) {
                col[j] = grid[i][j] + Math.min(col[j],col[j-1]);
            }
        }

        return col[grid[0].length-1];

    }
}
