/*
 * [685] Redundant Connection II
 *
 * https://leetcode.com/problems/redundant-connection-ii/description/
 *
 * algorithms
 * Hard (28.38%)
 * Total Accepted:    4.3K
 * Total Submissions: 15.2K
 * Testcase Example:  '[[1,2],[1,3],[2,3]]'
 *
 * 
 * In this problem, a rooted tree is a directed graph such that, there is
 * exactly one node (the root) for which all other nodes are descendants of
 * this node, plus every node has exactly one parent, except for the root node
 * which has no parents.
 * 
 * The given input is a directed graph that started as a rooted tree with N
 * nodes (with distinct values 1, 2, ..., N), with one additional directed edge
 * added.  The added edge has two different vertices chosen from 1 to N, and
 * was not an edge that already existed.
 * 
 * The resulting graph is given as a 2D-array of edges.  Each element of edges
 * is a pair [u, v] that represents a directed edge connecting nodes u and v,
 * where u is a parent of child v.
 * 
 * Return an edge that can be removed so that the resulting graph is a rooted
 * tree of N nodes.  If there are multiple answers, return the answer that
 * occurs last in the given 2D-array.
 * Example 1:
 * 
 * Input: [[1,2], [1,3], [2,3]]
 * Output: [2,3]
 * Explanation: The given directed graph will be like this:
 * ⁠ 1
 * ⁠/ \
 * v   v
 * 2-->3
 * 
 * 
 * Example 2:
 * 
 * Input: [[1,2], [2,3], [3,4], [4,1], [1,5]]
 * Output: [4,1]
 * Explanation: The given directed graph will be like this:
 * 5  2
 * ⁠    ^    |
 * ⁠    |    v
 * ⁠    4 
 * 
 * Note:
 * The size of the input 2D-array will be between 3 and 1000.
 * Every integer represented in the 2D-array will be between 1 and N, where N
 * is the size of the input array.
 * 
 */
class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        //ideas learned from https://leetcode.com/problems/redundant-connection-ii/discuss/108045/C++Java-Union-Find-with-explanation-O(n)
        
        //two candidates. N vertex with N edges -> at most one solution.
        int[] cand1 = {-1,-1};
        int[] cand2 = {-1,-1};

       int[] parent = new int[edges.length+1];
       // The only thing special about this is the possibility to have one node with two parents.
       //1, detect if there is any node with two parents.
       for (int[] edge : edges) {
           if (parent[edge[1]] == 0) {
               parent[edge[1]] = edge[0];
           } else {
               //reaching here means parent[edge[1]] is already defined.
               cand1 = new int[] {parent[edge[1]],edge[1]};
               cand2 = new int[] {edge[0], edge[1]};
               edge[1] = 0;
           }
       }

       //2. Do a normal union find anyway to detect problems.
       // It covers all three possibilities.
       // a. normal 
       // b. cycle but no double-parenting
       // c. cycle with double parenting, but with right guess.
       // Then, if the normal union find fails, it only means the guess is wrong.
       for (int i = 0; i < parent.length; i++) {
           parent[i] = i;
       }

       for (int[] edge : edges) {
           if (edge[1] == 0) {
               // this is the previously removed.
               //marked invalid, continue;
               continue;
           }
           int father = edge[0];
           int child = edge[1];
           if (find(parent,father) == child) {
               //there is a loop.
               if (cand1[0] == -1) {
                   return edge;
               } else {
                   //after removing cand2, there is still a cycle. guess of cand 2 is wrong, return cand1.
                   return cand1;
               }
           }
           parent[child] = father;
       }
       //after removing cand2, there is no cycles, guess of cand2 is right, return cand2.
       return cand2;
    }

    //util functions
    private int find(int[] parent, int input) {
        if (parent[input]!= input) {
            parent[input] = find(parent, parent[input]);
        }
        return parent[input];
    }
}
