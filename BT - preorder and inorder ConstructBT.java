/**
https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
preorder = [3,9,20,15,7]
inorder = [9,3,15,20,7]

    3
   / \
  9  20
    /  \
   15   7

 */
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<inorder.length;i++)
            map.put(inorder[i],i);
        
        return buildTree(preorder, inorder, 0, 0, inorder.length-1, map);
    }
    TreeNode buildTree(int[] preorder, int[] inorder, int pInd , int inStart, int inEnd, Map<Integer,Integer> map){
        if(inStart>inEnd)
            return null;
        TreeNode node = new TreeNode(preorder[pInd]);
        int rootInd =map.get(preorder[pInd]);
        node.left  = buildTree(preorder,inorder, pInd+1, inStart, rootInd-1,map);
        node.right = buildTree(preorder,inorder, pInd+(rootInd-inStart)+1, rootInd+1, inEnd, map);
                                               /* ^ this is IMP have sample trees helps*/
        return node;
    }
}
