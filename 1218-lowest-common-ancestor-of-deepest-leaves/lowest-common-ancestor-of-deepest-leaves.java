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
    HashMap<Integer, Integer> map = new HashMap<>();
    int maxd = 0;
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        depth(root, 0);
        return lca(root);
    }
    public void depth(TreeNode root, int d){
        if(root == null)    return;
        maxd = Math.max(maxd, d);

        map.put(root.val, d);
        depth(root.left, d+1);
        depth(root.right, d+1);
    }
    public TreeNode lca(TreeNode root){
        if(root == null || map.get(root.val) == maxd){
            return root;
        }

        TreeNode leftN = lca(root.left);
        TreeNode rightN = lca(root.right);

        // If p and q are found in different subtrees, root is LCA
        if (leftN != null && rightN != null) return root;
        // Otherwise, return the non-null node
        return leftN != null ? leftN : rightN;
    }

}