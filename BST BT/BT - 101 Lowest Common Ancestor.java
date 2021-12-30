
/*
Medium - lots of companies
VIMP

https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
 The main thing about this problem is to understand different nodes it can get unexpected result
 May be one of the node is LCA it self and second node is down the tree?
    so we dont have to find another node down the tree just return first found
    if another node is on other side of tree then it will come up eventually and then we will return root.

 one of the node is null?
 what if one of the p OR q node doesnt exist in the tree?

*/
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if(root == null)
            return null;

        if(root == p || root == q)
            return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if(left != null && right != null)
            return root;

        return left != null ? left : right;
    }
}
