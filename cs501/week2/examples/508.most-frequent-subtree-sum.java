/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public int[] findFrequentTreeSum(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        Map<Integer,Integer> map = new HashMap<Integer, Integer>();
        int[] arr = new int[2];//max, count;
        helper(root, map, arr);
        int[] res = new int[arr[1]];//important: arr[1] has the informationon the length
        int index = 0;
        for (Map.Entry<Integer,Integer> entry: map.entrySet()) {
            if (entry.getValue() == arr[0]) {
                res[index++] = entry.getKey();
            }
        }
        return res;
    }
    int helper( TreeNode root, Map<Integer, Integer> map, int[] arr) {
        if (root == null) {
            return 0;
        }
        int left = helper(root.left, map, arr);
        int right = helper(root.right,map,arr);
        int sum = left + right + root.val;
        map.put(sum, map.getOrDefault(sum,0)+1);
        if (map.get(sum) > arr[0]) {
            arr[0] = map.get(sum);
            arr[1] = 1;
        } else if (map.get(sum) == arr[0]) {
            arr[1]++;
        }
        return sum;
    }
}
