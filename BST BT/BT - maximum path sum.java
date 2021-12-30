/**
Hard - VIMP
DoorDash36 Facebook24 Amazon10 and so many companies!

https://leetcode.com/problems/binary-tree-maximum-path-sum/
Given a non-empty binary tree, find the maximum path sum.
For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections.
The path must contain at least one node and does not need to go through the root.
   -10
   / \
  9  20
    /  \
   15   7
  /  \
-25  -40

Output: 42 (15-20-7)
 */
class Solution {
    int max=Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        if(root==null) return 0;

        int maxLeftRight = findMaxSum(root);
        return max;
    }

    private int findMaxSum(TreeNode root){
        if(root==null)
            return 0;

        int leftGain  = Math.max(findMaxSum(root.left),  0); // having Math.max( ,0) Very Important
        int rightGain = Math.max(findMaxSum(root.right), 0); // return could be -ve values and we dont want to consider -ve value going up

        max = Math.max(max, root.val+leftGain+rightGain);
        return root.val + Math.max(leftGain, rightGain);
    }
}
