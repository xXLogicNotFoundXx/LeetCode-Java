/*
https://leetcode.com/problems/binary-search-tree-iterator/
Implement an iterator over a binary search tree (BST).
netx() returns the next larger number ( imagin inorder traversal of BST so sorted nodes)
hasNext() will return true if there are elements in remaining in BST 
extra space allowed is O(H) H is the height of the tree 
*/
class BSTIterator {
    Stack<TreeNode> stack;
    
    public BSTIterator(TreeNode root) {
        stack = new Stack<TreeNode>();
        pushAllLeft(root);
    }
    
    void pushAllLeft(TreeNode node){
        while(node!=null){
            stack.push(node);
            node = node.left;
        }
    }
    
    /** @return the next smallest number */
    public int next() {
        TreeNode node = stack.pop();
        pushAllLeft(node.right);
        return node.val;
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }
}
