/*
Easy - 
https://leetcode.com/problems/binary-tree-paths/
Given a binary tree, return all root-to-leaf paths
   1
 /   \
2     3        Output: ["1->2->5", "1->3"]
 \
  5
*/
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        helper(res, root, sb);
        return res;
    }

    private void helper(List<String> res, TreeNode root, StringBuilder sb) {
        if(root == null) return;
        int len = sb.length();
        sb.append(root.val);
        if(root.left == null && root.right == null) {
            res.add(sb.toString());
        } else {
            sb.append("->");
            helper(res, root.left, sb);
            helper(res, root.right, sb);
        }
        sb.setLength(len); // you cant use deleteCharAt or delete (index1,index2) we dont know what is the number in val
    }
}
