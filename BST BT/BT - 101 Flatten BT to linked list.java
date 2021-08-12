/**
 https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
    1
   / \
  2   5
 / \   \
3   4   6
The flattened tree should look like:
1-2-3-4-5-6 (right pointers)
Medium FB, G, MS, 
 */
class Solution {
    public void flatten(TreeNode root) {
        if(root==null) return; 
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while(cur!=null){
            if(cur.right !=null) 
                stack.push(cur.right);
            
            if(cur.left==null){
                cur.right = stack.isEmpty() ? null : stack.pop();
            } else{
                cur.right = cur.left;
                cur.left = null;
            }
            cur=cur.right;
        }
    }
}
