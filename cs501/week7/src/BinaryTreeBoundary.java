import java.util.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

class Solution {
     public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> ret = new ArrayList<Integer>();
        if (null  == root) {
            return ret;
        }
        ret.add(root.val);
        //get all left boundaries
        getLeftBoundary(root.left,ret);
        //get all left leaves
        leaves(root.left,ret);
        //get all right leaves
        leaves(root.right,ret);
        //get all right boundaries
        getRightBoundary(root.right,ret);
        return ret;
    }
    void getLeftBoundary(TreeNode root, List<Integer> ret) {
        if (root == null || isLeaf(root)) {
            return;
        }
        ret.add(root.val);
        if(root.left != null) getLeftBoundary(root.left,ret);
        else getLeftBoundary(root.right,ret);
    }
    void getRightBoundary(TreeNode root, List<Integer> ret) {
        if (root == null || isLeaf(root)) {
            return;
        }
        if(root.right != null) getRightBoundary(root.right,ret);
        else getRightBoundary(root.left,ret);
        //here we need to add to the node AFTER the children has been visited
        ret.add(root.val);
    }
    void leaves(TreeNode root, List<Integer> ret) {
        if(root == null) {
            return;
        }
        if (isLeaf(root)) {
            ret.add(root.val);
            return;
        }
        leaves(root.left, ret);
        leaves(root.right,ret);
    }
    boolean isLeaf(TreeNode t) {
        return(t.left == null && t.right == null);
    }


}

public class BinaryTreeBoundary {
    static public void main(String args[]) {
        int testIteration = 10;
        Input  utils = new Input();
        Solution sol = new Solution();
        try {
            List <String> lines = Files.readAllLines(Paths.get("hw3_input.txt"),StandardCharsets.UTF_8);
            testIteration = lines.size();
        for (int i = 0; i < testIteration; i++) {
            String str = lines.get(i);
            TreeNode input = utils.str2tree(str);
            List<Integer> output = sol.boundaryOfBinaryTree(input);


            System.out.println(str);
            //System.out.println(output);
            utils.printList(output);
        }
        } catch (IOException e) {
            System.out.println("Unable to read file");
            return;
        }
        
        System.out.println("Test executed without crashes, please manually verify input");
    }
}
