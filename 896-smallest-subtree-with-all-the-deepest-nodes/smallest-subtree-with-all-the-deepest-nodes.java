/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    HashMap<Integer, Integer> map = new HashMap<>();    // stores depth of each node
    int maxd = 0;    // maximum depth in the tree

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        depth(root, 0);     // first pass: compute depth of each node and max depth
        return lca(root);   // second pass: find LCA of deepest leaves
    }

    public void depth(TreeNode root, int d){
        if(root == null)    return;
        maxd = Math.max(maxd, d);   // update max depth

        map.put(root.val, d);   // store depth of current node
        depth(root.left, d+1);
        depth(root.right, d+1);
    }

    public TreeNode lca(TreeNode root){
        // If null or this node itself is a deepest leaf, return it
        if(root == null || map.get(root.val) == maxd){
            return root;
        }

        // Recurse into left and right subtrees
        TreeNode leftN = lca(root.left);
        TreeNode rightN = lca(root.right);

        // If p and q are found in different subtrees, root is LCA
        if (leftN != null && rightN != null) return root;
        /// Otherwise return the side that contains deepest nodes
        return leftN != null ? leftN : rightN;
    }

}