public class Solution {
    public boolean validTree(int n, int[][] edges) {
        if (n == 0 || edges.length == 0 ) {
            //tricky: is this valid?
            return true;
        }
        //construct inDegree
        int[] inDegree = new int[n];
        //set of visited nodes
        Set<Integer> visited = new HashSet<Integer>();
        

    }
}
