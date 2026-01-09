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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Base case: if root is null or equals p or q, return root
        if(root == null || root == p || root == q)  return root;

        // Recurse on left and right subtrees
        TreeNode leftN = lowestCommonAncestor(root.left, p,q);
        TreeNode rightN = lowestCommonAncestor(root.right, p,q);

        // If p and q are found in different subtrees, root is LCA
        if (leftN != null && rightN != null) return root;
        // Otherwise, return the non-null node
        return leftN != null ? leftN : rightN;
    }
}