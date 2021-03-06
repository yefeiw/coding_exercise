/*
 * [210] Course Schedule II
 *
 * https://leetcode.com/problems/course-schedule-ii
 *
 * algorithms
 * Medium (28.74%)
 * Total Accepted:    73K
 * Total Submissions: 253.9K
 * Testcase Example:  '2\n[[1,0]]'
 *
 *
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 *
 * Some courses may have prerequisites, for example to take course 0 you have
 * to first take course 1, which is expressed as a pair: [0,1]
 *
 *
 * Given the total number of courses and a list of prerequisite pairs, return
 * the ordering of courses you should take to finish all courses.
 *
 * There may be multiple correct orders, you just need to return one of them.
 * If it is impossible to finish all courses, return an empty array.
 *
 *
 * For example:
 * 2, [[1,0]]
 * There are a total of 2 courses to take. To take course 1 you should have
 * finished course 0. So the correct course order is [0,1]
 *
 * 4, [[1,0],[2,0],[3,1],[3,2]]
 * There are a total of 4 courses to take. To take course 3 you should have
 * finished both courses 1 and 2. Both courses 1 and 2 should be taken after
 * you finished course 0. So one correct course order is [0,1,2,3]. Another
 * correct ordering is[0,2,1,3].
 *
 * Note:
 *
 * The input prerequisites is a graph represented by a list of edges, not
 * adjacency matrices. Read more about how a graph is represented.
 * You may assume that there are no duplicate edges in the input
 * prerequisites.
 *
 *
 *
 * click to show more hints.
 *
 * Hints:
 *
 * This problem is equivalent to finding the topological order in a directed
 * graph. If a cycle exists, no topological ordering exists and therefore it
 * will be impossible to take all courses.
 * Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera
 * explaining the basic concepts of Topological Sort.
 * Topological sort could also be done via BFS.
 *
 *
 */
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] ret =  new int[numCourses];
        if (numCourses == 0) {
            return ret;
        }
        Map<Integer, List<Integer>> neighbors = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();

        for (int[] prerequisite : prerequisites) {
            int first = prerequisite[1];
            int second = prerequisite[0];
            inDegree.put(second, inDegree.getOrDefault(second,0)+1);
            if (!neighbors.containsKey(first)) {
                neighbors.put(first, new ArrayList<>());
            }
            neighbors.get(first).add(second);
        }

        Deque<Integer> queue = new ArrayDeque<>();
        int cnt = 0;
        for (int i = 0; i < numCourses; i++) {
            if (!inDegree.containsKey(i)) {
                queue.add(i);
            }
        }

        while(!queue.isEmpty())  {
            int front = queue.remove();
            ret[cnt++] = front;
            if (neighbors.containsKey(front)) {
                for(int next : neighbors.get(front)) {
                    inDegree.put(next, inDegree.get(next) - 1);
                    if (inDegree.get(next) == 0) {
                        queue.add(next);
                    }
                }
            }
        }

        if (cnt == numCourses) return ret;
        else return new int[0];

    }
}
