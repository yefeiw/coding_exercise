/*
 * [98] Validate Binary Search Tree
 *
 * https://leetcode.com/problems/validate-binary-search-tree
 *
 * Medium (23.09%)
 * Total Accepted:    
 * Total Submissions: 
 * Testcase Example:  '[2,1,3]'
 *
 * Can you solve this problem? 🤔
 */
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    bool isValidBST(TreeNode* root) {
        if (!root) {
            //not a BST, let a lone a valid one
            return false;
        }
        int min = root->val;
        int max = root->val;
        return helper(root, min , max);
    }
    bool helper(TreeNode *root, int &min, int &max) {
        if (!root) {
            //return true in helper to not mess up
            return true;
        }
        //min and max values
        int leftMin, leftMax;
        int rightMin, rightMax;
        bool left = helper(root->left, leftMin, leftMax);
        bool right = helper(root->right, rightMin, rightMax);
        if (left == false || right == false) return false;
        if (root->left &&leftMax >= root->val) return false;
        if (root->right && rightMin <= root->val) return false;
        min = (root->left)? leftMin : root->val;
        max = (root->right)? rightMax : root->val;
        return true;

    }
};
