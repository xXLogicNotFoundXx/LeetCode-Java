/*
Medium -
Microsoft8 Amazon5 Facebook3
545. Boundary of Binary Tree
Anti-clockwise direction starting from root.
Boundary includes left boundary, leaves, and right boundary in order without duplicate nodes.

  1                          ____1_____
   \                        /          \
    2                      2            3
   / \                    / \          /
  3   4                  4   5        6
                            / \      / \
                           7   8    9  10
Ouput:
[1, 3, 4, 2]           [1,2,4,7,8,9,10,6,3]

*/
class Solution {
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> list = new ArrayList<>();

        if(root==null) return list;
        list.add(root.val);
        // 1
        leftBorder(root.left,list);
        // 2
        leafNodes(root.left,list);
        leafNodes(root.right,list);
        // 3
        List<Integer> right = new ArrayList<>();
        rightBorder(root.right,right);
        int index = list.size();
        for(int i : right)
            list.add(index,i);

        return list;
    }

    void leftBorder(TreeNode root,List<Integer> list){
        if(root==null || (root.left==null && root.right==null)) return;
        list.add(root.val);
        leftBorder(root.left,list);
        if(root.left==null)
            leftBorder(root.right,list);
    }

    void leafNodes(TreeNode root,List<Integer> list){
        if(root==null) return;
        if((root.left==null && root.right==null))
            list.add(root.val);
        leafNodes(root.left,list);
        leafNodes(root.right,list);
    }

    void rightBorder(TreeNode root,List<Integer> list){
        if(root==null || (root.left==null && root.right==null)) return;
        list.add(root.val);
        rightBorder(root.right,list);
        if(root.right==null)
            rightBorder(root.left,list);

        // if you add root.val at this line it will give us reverse order    IMP
        //list.add(root.val); // add after child visit(reverse)
    }
}
