/*

https://leetcode.com/problems/recover-binary-search-tree/

You are given the root of a binary search tree (BST), where exactly two nodes of the tree were swapped by mistake. Recover the tree without changing its structure.

Assumption is that we have distinct values. 

May be you can do inorder traversal and save in the list. 
Then if we find the a[i-1]>a[i] then we can swap that and be done. 
^ This doesnt work.
Why?
  1
3       => inorder -> 2,3,1      
  2     => we will end up swaping 2 and 3. 
           But we need to swap 1 and 3 
  
well may be we need to find two ... positions like a[i-1]>a[i] and swap those.
Which should we swap?
looks like, we take notes of only ith index two times and swap?

will there be always 2 positions like that?
   3
 1    4    => inorder -> 1 3 2 4 
     2        There is only one position a[i-1]>a[i] like this

*/
class Solution {
    
    // Time O(N) and Space O(N)
    public void recoverTree1(TreeNode root) {
        List<TreeNode> list = new ArrayList<TreeNode>();
        
        inorderTraverse(root,list);
        
        int first =-1, second=-1;
            
        for(int i=1;i<list.size();i++){
            
            TreeNode nodeiminus = list.get(i-1);
            TreeNode nodei = list.get(i);
            
            if(nodeiminus.val > nodei.val){
                if(first==-1){
                    first=i-1;
                    second=i; // if we find only one a[i-1]>a[i]
                } else { 
                    second=i;
                }
            }
        }
        
        int temp = list.get(first).val;
        list.get(first).val = list.get(second).val;
        list.get(second).val = temp;
    }
    
    void inorderTraverse(TreeNode root, List<TreeNode> list){
        if(root==null)
            return;
        
        inorderTraverse(root.left, list);
        list.add(root);
        inorderTraverse(root.right, list);
    }
    
    // now if recursive then it will be O(log*N) space. 
    // So how would you do this in recursive ? 
    // We know we need two pointers and they could be apart ...
    // we dont want to swap immediate nodex .. we have to travel further up ...
    // and then make a change ... that mean we have to traverse whole tree first and make a note of first and second pointer 
    
    // Time O(N) Space O(Log.N)
    TreeNode first=null, second=null;
    TreeNode prev = new TreeNode(Integer.MIN_VALUE); 
    public void recoverTree(TreeNode root) {
        
        
        inorderTraverse(root);
        
        // Swap the values of the two nodes
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
    
    void inorderTraverse(TreeNode root){
        if(root==null)
            return;
        
        inorderTraverse(root.left);
        
        // prev.value should always be less 
        if(prev.val>root.val && first==null){
            first=prev;
            second=root;
        }
        
        if(prev.val>root.val && first!=null){
            second=root;
        }
        
        prev = root;
        
        inorderTraverse(root.right);
    }
    
    // There is a threaded binary tree solution if you want to check that out. space O(1)
}
