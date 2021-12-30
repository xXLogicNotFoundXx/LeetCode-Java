/**
Medium
Google6 Amazon4 Microsoft2 .. some more

222. Count Complete Tree Nodes

https://leetcode.com/problems/count-complete-tree-nodes/

Number of fully balanced binary tree is  = 2^Depth - 1
So if the leftHeight and RightHeigh of the tree is same we have nodes = 2^n-1

As every recursion is log(n) overall is O(log(n)^2)
Also , n + n-1 + n-2 + ... 1 = (n*(n-1)/2) => O(n^2)
here in this n is log(n) so O(log(n)^2)

Case :
        1
      /   \
     2     3
    /
   4

 */
class Solution {
    // recursive O (N) - O(logn) space
    public int countNodes1(TreeNode root) {
        if(root==null)
            return 0;

        return 1 + countNodes(root.left) + countNodes(root.right);
    }
    
    public int countNodes(TreeNode root) {
        int leftDepth = leftDepth(root);
        int rightDepth = rightDepth(root);

        if (leftDepth == rightDepth)
            return (int) Math.pow(2,leftDepth) - 1;
        else
            return countNodes(root.left)+ 1 /*root*/ + countNodes(root.right);
    }

    private int rightDepth(TreeNode root) {
        int dep = 0;
        while (root != null) {
            root = root.right;
            dep++;
        }
        return dep;
    }

    private int leftDepth(TreeNode root) {
        int dep = 0;
        while (root != null) {
            root = root.left;
            dep++;
        }
        return dep;
    }
}
