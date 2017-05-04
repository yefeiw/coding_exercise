import java.util.*;

class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        if (null == wall || 0 == wall.size() || 0 == wall.get(0).size()) {
            return 0;
        }
        HashMap<Integer, Integer> edges = new HashMap<Integer,Integer>();
        int maxCut = 0;
        int numLayers = wall.size();
        for(List<Integer> layer : wall) {
            int sum = 0;
            for(int i = 0; i < layer.size() -1; i++) {
                sum += layer.get(i);
                //System.out.printf("sum is %d\n", sum);
                if(!edges.containsKey(sum)) {
                    edges.put(sum,0);
                }
                int buffer = edges.get(sum) + 1;
                edges.put(sum, buffer);
                    maxCut = Math.max(maxCut, buffer);
                    //System.out.printf("maxCut is %d\n",maxCut);
                
            }
        }
        return numLayers - maxCut;
    }
}

public class BrickWall {
	 public static void main (String args[]) {
        int testIteration = 10;
        Input  testInput = new Input();
        Solution sol = new Solution();
  
        for (int i = 0; i < testIteration; i++) {
            List<List<Integer>> input =  testInput.generateRandom2DList(testIteration);
            int out = sol.leastBricks(input);

        }  

        System.out.println("Test exeucted with no crashes, please verify on leetcode");
    }
}