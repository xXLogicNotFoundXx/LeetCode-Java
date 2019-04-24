/**
https://leetcode.com/problems/binary-tree-maximum-path-sum/
Given a non-empty binary tree, find the maximum path sum.
For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.
   -10
   / \
  9  20
    /  \
   15   7

Output: 42 (15-20-7)
 */
class Solution {
    public int maxPathSum(TreeNode root) {
        if(root==null) return 0;
        int []max = new int[1];
        max[0] = root.val;
        int maxLeftRight = maxPathSum(root,max);
        return Math.max(max[0],maxLeftRight); // though math.max() is not needed just return max[0]
    }
    
    private int maxPathSum(TreeNode root, int[]max){
        if(root==null)
            return 0;
        
        int leftGain  = Math.max(maxPathSum(root.left,max),0); // Very Important 
        int rightGain = Math.max(maxPathSum(root.right,max),0);// Math.max( ,0) return could be -ve values 
        
        max[0] = Math.max(max[0],root.val+leftGain+rightGain);
        return root.val + Math.max(leftGain,rightGain);
    }
}
