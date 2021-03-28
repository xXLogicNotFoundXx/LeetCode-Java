/*
https://leetcode.com/problems/inorder-successor-in-bst/

root.val <= p.val In this case, root cannot be p
root.val > p.val  In this case, root can be a possible answer
*/
class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if(root==null) 
            return root;
        
        if(root.val<=p.val){ // ip 5 ( we will end up finding P here so we gotta go right)
            // we dont really care about less than p nodes alswo we dont care about P node either 
            // still we have to go one right thats why <= so we go right to the P
            // all right nodes of the P will be greater so it will end up
            // on else statement which will travers left only
            return inorderSuccessor(root.right,p); 
        } else{ 
            // if we start from here root could be the answer.
            TreeNode node = inorderSuccessor(root.left,p);
            return node!=null ? node : root;
        }
    }
    
    public TreeNode iterativeInorderSuccessor(TreeNode root, TreeNode p) {
         
        TreeNode ret = null;
        while (root != null) {
            if (root.val <= p.val) {
                root = root.right;
            } else {
                ret = root;
                root = root.left;
            }
        }
        return ret;
    }
}
