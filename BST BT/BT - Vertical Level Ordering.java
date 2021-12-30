/*
Medium
Facebook89 Bloomberg7 Amazon4 Apple2 Salesforce2

https://leetcode.com/problems/binary-tree-vertical-order-traversal/
Input: [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5)

     3
    /\
   /  \
   9   8
  /\   /\
 /  \ /  \
 4  0 1   7
    / \
   /   \
   5    2

Output:

[
  [4],
  [9,5],
  [3,0,1],
  [8,2],
  [7]
]

/**
 Here with normal DFS and maintaing only vertical level will give [2,8] for vetical_level=1  because we visit 2 first instead of 8
 but we need 8,2

 This question also asks the order of the nodes from TOP to BOTTOM.
 That is why we cant use DFS as it can visit lower nodes first ad will mess up the order .
 So, We have to use BST and maintain vertical level for each node.
 As we traverse through queue we add nodes in map with vLevel for each node.
 LeftNodeLevel = parentLeevel-1  and rightLevel = parentLevel+1;
*/
class Solution {
    // we have to have this becasue we will be traversing BST
    // and every node in queue gonna have to maintain their vertical level respectively
    class TreeNodeVerticalLevel{
        TreeNode treeNode;
        int vLevel;
        TreeNodeVerticalLevel(TreeNode treeNode, int vLevel){
            this.treeNode = treeNode;
            this.vLevel = vLevel;
        }
    }

    public List<List<Integer>> verticalOrder(TreeNode root) {
        if(root==null)
          return new ArrayList();

        Map<Integer,List<Integer>> map = new HashMap();

        Queue<TreeNodeVerticalLevel> queue = new LinkedList();
        queue.add(new TreeNodeVerticalLevel(root,0));

        while(!queue.isEmpty()){

            TreeNodeVerticalLevel quNode = queue.poll();
            List<Integer> list = map.getOrDefault(quNode.vLevel,new ArrayList<Integer>());
            list.add(quNode.treeNode.val);
            map.put(quNode.vLevel,list);

            if(quNode.treeNode.left!=null)
                queue.add(new TreeNodeVerticalLevel(quNode.treeNode.left, quNode.vLevel-1));

            if(quNode.treeNode.right!=null)
                queue.add(new TreeNodeVerticalLevel(quNode.treeNode.right, quNode.vLevel+1));
        }

        List<List<Integer>> ans = new ArrayList();

        // add 0 to -ve at the first place in the list
        for(int i=0; map.containsKey(i); i--)
            ans.add(0, map.get(i));

        for(int i=1; map.containsKey(i); i++)
            ans.add(map.get(i));

        return ans;
    }
}
