class Solution {
    public int maxCount(int m, int n, int[][] ops) {
        int minRow = m;
        int minCol = n;
        for (int[] square : ops) {
        	int row = square[0];
        	int col = square[1];
        	minRow = Math.min(minRow, row);
        	minCol = Math.min(minCol, col);
        }
        return minRow * minCol;
    }
}