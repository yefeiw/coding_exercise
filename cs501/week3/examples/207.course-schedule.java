public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses < 2) {
            return true;
        }
        if (prerequisites.length == 0) {
            return true;
        }
        List<List<Integer>> neighborList = new ArrayList<List<Integer>>();
        initList(prerequisites,neighborList, numCourses);
        //Using status table
        int[] status = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            //find an entrance
            if (status[i] == 0 && !dfsHelper(neighborList, status, i)) {
                return false;
            }
        }
        return true;
    }
    private void initList(int[][] edges, List<List<Integer>> neighbors, int n) {
        for (int i = 0; i < n; i++) {
            List<Integer> neighbor = new ArrayList<Integer>();
            neighbors.add(neighbor);
        }

        for(int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            neighbors.get(a).add(b);
            neighbors.get(b).add(a);
        }
    }

    private boolean dfsHelper(List<List<Integer>> neighborList, int[] status, int cur) {
        status[cur] = -1;
        for (int i : neighborList.get(cur)) {
            if (status[i] == -1 || !dfsHelper(neighborList, status, i)) {
                return false;
            }
        }
            status[cur] = 1;
            return true;
    }        
}
