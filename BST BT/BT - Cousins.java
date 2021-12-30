/*
Easy - Amazon4 msft

 https://leetcode.com/problems/cousins-in-binary-tree/
 Two nodes of a binary tree are cousins if they have the same depth, but have different parents.

 Use find method twice and return (parent and depth) of a node.
 Compare depth and parent at the end of those two nodes.
*/
class Solution {
    class Pair{
      TreeNode parent;
      int height;
      Pair(TreeNode parent, int height){
        this.parent=parent;
        this.height=height;
      }
    }

    public boolean isCousins(TreeNode root, int x, int y) {
        Pair num1 = find(root,null,x,0);
        Pair num2 = find(root,null,y,0);

        if( num1 == null || num2 == null) // couldnt find x OR y
            return false;

        return num1.parent != num2.parent && num1.height == num2.height;
    }

    Pair find(TreeNode node, TreeNode par, int num, int height){
        if(node==null)
          return null;

        if(node.val == num)
            return new Pair(par,height);

        Pair res1 = find(node.left, node, num, height+1);
        Pair res2 = find(node.right, node, num, height+1);

        return res1!=null ? res1 : res2;
    }
}
