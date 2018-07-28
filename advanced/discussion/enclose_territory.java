import java.util.*;
class Solution {
    int[][] dirs = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    private boolean dfs(int i, int j, int m, int n, char[][] territory, int[] potW) {
        for (int[] dir : this.dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x >= 0 && x < m && y >= 0 && y < n) {
                if (territory[x][y] == 'W') {
                    potW[0] = potW[0] + 1;
                    territory[x][y] = 'M';
                    if (dfs(x, y, m, n, territory, potW))
                        return true;
                } else {
                    continue;
                }
            } else {
                return true;
            }
        }
        return false;
    }

    public int calculateEnclosed(char[][] territory) {
        int m = territory.length;
        int n = territory[0].length;
        if (m == 0 || n == 0)
            return 0;
        int numW = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (territory[i][j] == 'W') {
                    int[] potW = new int[1];
                    potW[0] = 1;
                    territory[i][j] = 'M';
                    boolean res = dfs(i, j, m, n, territory, potW);
                    if (!res)
                        numW += potW[0];
                }
            }
        }
        return numW;
    }
}

public class enclose_territory {
    public static void main(String[] args) {
        Solution s = new Solution();
        //char[][] territory = {}; IndexOutOfBounds at line 27
        char[][] territory = {{'W','W','B','B'},{'B','W','W','W'}};
        System.out.println("Number of enclosed territory is "+ s.calculateEnclosed(territory));
    }
}