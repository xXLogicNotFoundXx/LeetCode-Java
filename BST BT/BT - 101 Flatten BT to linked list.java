/**
Medium - IMP
Facebook14 Amazon7 Microsoft7 Google3

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

        Deque<TreeNode> stack = new ArrayDeque<>();

        TreeNode head = root;
        while(root!=null){

            if(root.right!=null)
                stack.push(root.right);
            
            if(root.left!=null){
                root.right = root.left;
                root.left = null;
            } else {
                root.right = stack.isEmpty() ? null : stack.pop();
            }

            root = root.right;
        }

    }
}
