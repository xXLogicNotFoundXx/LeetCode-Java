/* 
https://leetcode.com/problems/diameter-of-binary-tree/
Diameter of Binary Tree
          1
         / \
        2   3
       / \     
      4   5    
Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

Note : Root element does not count
Note : Diameter path may or may not pass through the root of the tree.

I tried to Pass Integer object to maxHeight but .. 
Integer as passby value and they are immutable so   
    Changing the reference inside a method won't be reflected
    into the passed-in reference in the calling method.
*/
class Solution {
    int ans;
    public int diameterOfBinaryTree(TreeNode root) {
        if(root==null) return 0;
        maxHeight(root);
        return ans;
    }
    
    int maxHeight(TreeNode root){
        if(root==null) return 0;
        
        int left = maxHeight(root.left);
        int right = maxHeight(root.right);
        
        ans = Math.max(ans,left+right); 
        
        return Math.max(left,right)+1; 
    }
}
