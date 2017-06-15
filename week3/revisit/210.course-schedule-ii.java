public class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses == 0) {
            return new int[0];
        }
        int[] inDegree = new int[numCourses];
        List<List<Integer>> adjList =  new ArrayList<List<Integer>>();
        //build up adj list
        for (int i  = 0; i < numCourses; i++) {
            adjList.add(new ArrayList<Integer>());
        }
        //furnish adj list and update in degree
        for(int[] pair : prerequisites ){
            int a = pair[0];
            int b = pair[1];
            inDegree[a]++;
            adjList.get(b).add(a);
        }

        //queue for BFS
        Deque<Integer> queue =  new ArrayDeque<Integer>();
        for(int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        int count = 0;//count of how many nodes are visited
        int[] ret = new int[numCourses];
        while(!queue.isEmpty()) {
            int front = queue.remove();
            ret[count] = front;
            count++;
            for (int cand : adjList.get(front)) {
                inDegree[cand]--;
                if (inDegree[cand] == 0) {
                    queue.add(cand);
                }
            }
        }
        if (count != numCourses) return new int[0];
        else return ret;
    }
}
