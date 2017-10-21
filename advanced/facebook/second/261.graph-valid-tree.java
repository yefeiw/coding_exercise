/*
 * [261] Graph Valid Tree
 *
 * https://leetcode.com/problems/graph-valid-tree
 *
 * algorithms
 * Medium (37.87%)
 * Total Accepted:    40.8K
 * Total Submissions: 107.7K
 * Testcase Example:  '5\n[[0,1],[0,2],[2,3],[2,4]]'
 *
 * 
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each
 * edge is a pair of nodes), write a function to check whether these edges make
 * up a valid tree.
 * 
 * 
 * 
 * For example:
 * 
 * 
 * Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
 * 
 * 
 * Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return
 * false.
 * 
 * 
 * 
 * Note: you can assume that no duplicate edges will appear in edges. Since all
 * edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear
 * together in edges.
 * 
 */
class Solution {
    public boolean validTree(int n, int[][] edges) {
        if (n < 1) return false;
        if (edges.length + 1 != n) {
        	return false;
        }
        Map<Integer, List<Integer>> neighbors = new HashMap<>();
        for(int i = 0; i < n; i++) {
        	neighbors.put(i, new ArrayList<>());
        }
        for(int[] edge : edges) {
        	neighbors.get(edge[0]).add(edge[1]);
        	neighbors.get(edge[1]).add(edge[0]);
        }
        Set<Integer> visited = new HashSet<>();
        Deque<Integer> queue =  new ArrayDeque<>();

        queue.add(0);
        visited.add(0);
        while(!queue.isEmpty()) {
        	int front = queue.remove();
        	for(int next : neighbors.get(front)) {
        		if (!visited.contains(next)) {
        			queue.add(next);
        			visited.add(next);
        		}
        	}
        }

        return (visited.size() == n);

    }
}
