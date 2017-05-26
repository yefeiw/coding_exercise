import java.util.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        if (matrix  == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;

        int result = Integer.MIN_VALUE;

        for (int c1 = 0; c1 < n; c1++) {
            int []each = new int[m];
            for(int c2=c1; c2 >= 0; c2--) {
                for (int r=0; r<m; r++) {
                    each[r] += matrix[r][c2];
                }
                result = Math.max(result, getLargestSumCLoseToK(each,k));
            }
        }
        return result;
    }
    public int getLargestSumCLoseToK(int[] array, int k) {
        int  sum = 0;
        TreeSet<Integer> set = new TreeSet<Integer>();
        int result = Integer.MIN_VALUE;
        set.add(0);
        for (int  i = 0; i < array.length; i++) {
            sum = sum + array[i];
            Integer ceiling = set.ceiling(sum-k);
            if (ceiling != null) {
                result = Math.max(result, sum - ceiling);
            }
            set.add(sum);
        }
        return result;
    }
}

public class MaxRectangleSum{
    static public void main(String args[]) {
        int testIteration = 10;
        Input  utils = new Input();
        Solution sol = new Solution();

        for (int i = 0; i < testIteration; i++) {
            int [][] input = utils.generateRandomMatrix(testIteration);
            int k = utils.generateRandomInt();
            int output = sol.maxSumSubmatrix(input, k);
            //print
            utils.printMatrix(input);
            System.out.println(k);
            System.out.println(output);

        }
            System.out.println("Test executed without crashes, please manually verify input");
    }
}
