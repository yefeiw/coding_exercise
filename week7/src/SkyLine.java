import java.util.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

class Solution {
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> result = new ArrayList<int[]>();
        if(buildings == null || buildings.length == 0 || buildings[0].length == 0) {
            return result;
        }
        List<int[]> height = new ArrayList<int[]>();//store each start and end point.
        for(int[] building : buildings) {
            height.add(new int[]{building[0],-building[2]});
            height.add(new int[]{building[1],building[2]});
        }
        //Here we need to sort the height
        Collections.sort(height,(a,b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });

        Queue<Integer> pq = new PriorityQueue<>((a,b) -> (b - a));
        pq.offer(0);
        int prevHeight = 0;
        for (int[] h : height) {
            if (h[1] < 0) {
                //this is a start point, add height;
                pq.offer(-h[1]);
            } else  {
                pq.remove(h[1]);
            }
            int cur =  pq.peek();
            if (prevHeight != cur) {
                //if the top has been moved, we need to update the result
                result.add(new int[]{h[0],cur});
                prevHeight = cur;
            }
        }
        return result;
    }
}

public class SkyLine {
    static public void main(String args[]) {
        int testIteration = 10;
        Input  utils = new Input();
        Solution sol = new Solution();
        for (int i = 0; i < testIteration; i++) {
            int[][] input = utils.generateRandomMatrix(testIteration);
            List<int[]> output = sol.getSkyline(input);
            //print
            //System.out.println(input);
            //System.out.println(output);
        }
        
        System.out.println("Test executed without crashes, please manually verify input");
    }
}
