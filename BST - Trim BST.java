/*
https://leetcode.com/problems/trim-a-binary-search-tree/
Given a binary search tree and the lowest and highest boundaries as L and R, trim the tree so that all its elements lies in [L, R] (R >= L). 
Input: 
    3
   / \
  0   4
   \
    2
   /
  1
L = 1  R = 3
Output: 
      3
     / 
   2 


L=32 R=44
  45
 /  \
30  46
 \
  36
  
o/p: 36

*/
class Solution {
    public TreeNode trimBST(TreeNode root, int L, int R) {
        
        // Find a valid root which is used to return. For following recursive function this code block is not necessary 
        // this will be handled automatically in helper function. 
        while (root!=null && (root.val < L || root.val > R)) {
            if (root.val < L) 
                root = root.right;
            
            if (root.val > R) 
                root = root.left;
        }

        return trimBSTHelper(root,L,R);
    }
    
    public TreeNode trimBSTHelper(TreeNode root, int L, int R) {
        if (root == null) 
            return null;
        
        if (root.val < L) 
            return trimBSTHelper(root.right, L, R);
        if (root.val > R) 
            return trimBSTHelper(root.left, L, R);
        
        root.left = trimBSTHelper(root.left, L, R);
        root.right = trimBSTHelper(root.right, L, R);
        
        return root;
    }
}

// My submission 
class Solution {
    public TreeNode trimBST(TreeNode root, int L, int R) {
    
        while( root!=null && root.val < L || root.val >R){
            while(root!=null && root.val <L)
                root = root.right;
            while(root!=null && root.val>R)
                root = root.left;
        }
        
        if(root==null) return null;
        
        trimLeftBST(root,L);
        trimRightBST(root,R);
        return root;
    }
    
    public TreeNode trimLeftBST(TreeNode root, int L) {
        if(root==null) return null;
      
        if(root.val<L)
            return trimLeftBST(root.right,L);
        else
            root.left = trimLeftBST(root.left,L);
        
        return root;
    }
    public TreeNode trimRightBST(TreeNode root, int R) {
        if(root==null) return null;
        
        if(root.val>R)
            return trimRightBST(root.left,R);
        else
            root.right = trimRightBST(root.right,R);
        
        return root;
    }
}
