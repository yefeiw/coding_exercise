import java.util.*;

class Solution {
   public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses == 0 || prerequisites == null) {
            return new int[0];
        }
        int[] inOrder = new int[numCourses];
        List[] adj = new ArrayList[numCourses];
        //step 1. Build graph
        for(int i = 0; i < numCourses; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < prerequisites.length; i++) {
            int[] cand = prerequisites[i];
            //increase inorder
            inOrder[cand[0]]++;
            //build up
            adj[cand[1]].add(cand[0]);
        }
        //step 2. BFS with inOrder and sort
        int[] ret = new int[numCourses];
        //fill original elements
        ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
        for (int i = 0; i < numCourses; i++) {
            if (inOrder[i] == 0 ) {
                queue.add(i);
            }
        }
        int count = 0;
        //main body of topological sort
        while(!queue.isEmpty()) {
            int top = queue.remove();
            ret[count++] = top;
            int adjSize = adj[top].size();
            for (int i =0; i < adjSize; i++) {
                int pointer = (int)adj[top].get(i);
                inOrder[pointer]--;
                if (0 == inOrder[pointer]) {
                    queue.add(pointer);
                }
            }
        }
        if (count == numCourses){
        return ret;
        } else return new int[0];
   }
}

public class CourseSchedule{
    public static void main (String args[]) {
        // int testIteration = 10000;
        //small test set for manual verification
        int testIteration = 10;
        Input  testInput = new Input();
        Solution sol = new Solution();
        for (int i = 0; i < testIteration; i++) {
            int numCourses = testInput.generateRandomInt();
            int[][] link = testInput.generateRandomMatrix(testIteration);
            int[] output = sol.findOrder(numCourses,link);
        }
        System.out.println("Test Executed, please manually check on leetcode");
    }
}
/* test cases used at leetcode:
[]
[0]
[0,1,2,3,4,5,6,7]
[0,1]
[0,1,2]
*/