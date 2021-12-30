/**
222. Count Complete Tree Nodes
https://leetcode.com/problems/count-complete-tree-nodes/
as every recursion is log(n) overall is O(log(n)^2)
Also , n + n-1 + n-2 + ... 1 = (n*(n-1)/2) => O(n^2)
here in this n is log(n) so O(log(n)^2)
 */
class Solution {
    public int countNodes(TreeNode root) {
        int leftDepth = leftDepth(root);
        int rightDepth = rightDepth(root);

        if (leftDepth == rightDepth)
            return (int)Math.pow(2,leftDepth) - 1;
        else
            return 1 /*root*/ + countNodes(root.left) + countNodes(root.right);
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
