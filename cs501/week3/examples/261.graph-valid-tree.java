//Notes:
//1. When doing BFS, remember to init visited to containing the starting node.
//2. Note that we need to search, just judging if something is null is not good enough.
//3. Some of the adj list may be empty. Need to consider that.
public class Solution {
    public boolean validTree(int n, int[][] edges) {
        if (n == 0) {
            //tricky: is this valid?
            return true;
        }
        if(n == 1) {
            return true;
        }
        //quick judgement: whether the number of edges is n-1;
        if(edges.length != n - 1) {
            return false;
        }
        //set of visited nodes
        Set<Integer> visited = new HashSet<Integer>();
        //queue for BFS
        Deque<Integer> queue = new ArrayDeque<Integer>();
        //Adjacency List
        List<Integer>[] adj = new List[n];

        //First iteration: get adjacency list
        for(int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            if(adj[a] == null) adj[a] = new ArrayList<Integer>();
            if(adj[b] == null) adj[b] = new ArrayList<Integer>();
            adj[a].add(b);
            adj[b].add(a);
        }

        //Second iteration: BFS and count the visited nodes;
        int count = 0;//counting how many nodes are visited;
        queue.add(0);
        visited.add(0);
        while(!queue.isEmpty()) {
            int front = queue.remove();
            count++;
            if(adj[front] == null) return false;
            for(int neighbor : adj[front]) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        return(count == n);

    }
}
