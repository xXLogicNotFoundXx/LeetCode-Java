/**
https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
preorder = [3,9,20,15,7]
inorder = [9,3,15,20,7]

    3
   / \
  9  20
    /  \
   15   7
   
preorder = [3,20,15,7]
inorder = [3,15,20,7]

    3
     \
     20
    /  \
   15   7

 */
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<inorder.length;i++){
            map.put(inorder[i],i);
        }
        return build(0,preorder.length-1,preorder,0,inorder.length-1,inorder,map);
    }
    
    TreeNode build(int preStart, int preEnd, int[] preorder, int inStart, int inEnd, int[] inorder, HashMap<Integer,Integer> map){
        if(preStart>preEnd || inStart>inEnd)
            return null;
        
        // Doing index calculations here helps a lot 
        // I got so confused when I tried to do the calculation in the function call itself 
        int rootInIdx=  map.get(preorder[preStart]);
        int numsOnLeft = rootInIdx - inStart;
        
        TreeNode node = new TreeNode(preorder[preStart],null,null);
        node.left  = build( preStart+1, preStart+numsOnLeft, preorder,  inStart,  rootInIdx-1, inorder, map);
        node.right = build( preStart+numsOnLeft+1, preEnd,   preorder, rootInIdx+1, inEnd, inorder, map);
        return node;
    }
}
