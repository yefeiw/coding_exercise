/*
 * [314] Binary Tree Vertical Order Traversal
 *
 * https://leetcode.com/problems/binary-tree-vertical-order-traversal
 *
 * algorithms
 * Medium (36.61%)
 * Total Accepted:    29.6K
 * Total Submissions: 80.9K
 * Testcase Example:  '[3,9,20,null,null,15,7]'
 *
 * Given a binary tree, return the vertical order traversal of its nodes'
 * values. (ie, from top to bottom, column by column).
 * If two nodes are in the same row and column, the order should be from left
 * to right.
 * 
 * Examples:
 * 
 * 
 * Given binary tree [3,9,20,null,null,15,7],
 * 
 * ⁠  3
 * ⁠ /\
 * ⁠/  \
 * ⁠9  20
 * ⁠   /\
 * ⁠  /  \
 * ⁠ 15   7
 * 
 * 
 * 
 * return its vertical order traversal as:
 * 
 * [
 * ⁠ [9],
 * ⁠ [3,15],
 * ⁠ [20],
 * ⁠ [7]
 * ]
 * 
 * 
 * 
 * Given binary tree [3,9,8,4,0,1,7],
 * 
 * ⁠    3
 * ⁠   /\
 * ⁠  /  \
 * ⁠  9   8
 * ⁠ /\  /\
 * ⁠/  \/  \
 * ⁠4  01   7
 * 
 * 
 * 
 * return its vertical order traversal as:
 * 
 * [
 * ⁠ [4],
 * ⁠ [9],
 * ⁠ [3,0,1],
 * ⁠ [8],
 * ⁠ [7]
 * ]
 * 
 * 
 * 
 * Given binary tree [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2
 * and 1's left child is 5),
 * 
 * ⁠    3
 * ⁠   /\
 * ⁠  /  \
 * ⁠  9   8
 * ⁠ /\  /\
 * ⁠/  \/  \
 * ⁠4  01   7
 * ⁠   /\
 * ⁠  /  \
 * ⁠  5   2
 * 
 * 
 * 
 * return its vertical order traversal as:
 * 
 * [
 * ⁠ [4],
 * ⁠ [9,5],
 * ⁠ [3,0,1],
 * ⁠ [8,2],
 * ⁠ [7]
 * ]
 * 
 * 
 * 
 * 
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
	class Position {
		public int pos;
		public TreeNode iter;
		public Position(int pos, TreeNode iter) {
			this.pos = pos;
			this.iter = iter;
		}

	}
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) {
        	return ret;
        }
        Deque<Position> queue = new ArrayDeque<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        //BUGWARNING: key in map is not sorted, so we need to track
        int minLevel = 0;
        int maxLevel = 0;
        queue.add(new Position(0,root));
        while(!queue.isEmpty()) {
        	int size = queue.size();
        	for(int i = 0; i < size; i++) {
        		Position front = queue.remove();
        		minLevel = Math.min(minLevel, front.pos);
        		maxLevel = Math.max(maxLevel, front.pos);
        		if (!map.containsKey(front.pos)) {
        			map.put(front.pos, new ArrayList<>());
        		}
        		List<Integer> list = map.get(front.pos);
        		list.add(front.iter.val);
        		if (front.iter.left != null) {
        			queue.add(new Position(front.pos -1, front.iter.left));
        		}
        		if (front.iter.right != null) {
        			queue.add(new Position(front.pos + 1, front.iter.right));
        		}
        	}
        }
        //reaching here mean we can extract the numbers;
        for(int i = minLevel; i <= maxLevel;i++) {
        	if (map.containsKey(i)) {
        	ret.add(map.get(i));
        }
        }
        return ret;
    }
}
