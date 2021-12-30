/*
Hard
Facebook47 Bloomberg12 Amazon7 Microsoft3
https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/
         3
        /\
      /   \
     9    8
    / \   / \
  /   \  /   \
  4   1 0    7
     /   \
   /      \
  5        2

Output:

[
[4],
[9,5],
[3,0,1],          <-  1 and 0 are sorted
[8,2],
[7]
]
*/
// this is same problem as https://leetcode.com/problems/binary-tree-vertical-order-traversal/
// but we want the list to be sorted if they are on the same horizontal Level.
class Solution {

    // To have a priority Queue on a calss you can provide the Comparator at the time of creation of the priorityQueue
    // OR
    // On a class you have to have implements Comparable<classname>
    // and implement =>  public int compareTo(classname other) {...}
    class TreeNodeVerticalLevel implements Comparable<TreeNodeVerticalLevel> { // Note not Comparator it is "Comparable"
        TreeNode treeNode;
        int vLevel;
        int hLevel;

        TreeNodeVerticalLevel(TreeNode treeNode, int vLevel, int hLevel){
            this.treeNode = treeNode;
            this.vLevel = vLevel;
            this.hLevel = hLevel;
        }

        @Override
        public int compareTo(TreeNodeVerticalLevel b) {
            if(this.hLevel == b.hLevel){
                return this.treeNode.val - b.treeNode.val;
            }
            return this.hLevel - b.hLevel;
        }
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        if(root==null)
          return new ArrayList();

        int minVLevel = 0;

        Map<Integer, PriorityQueue<TreeNodeVerticalLevel> > map = new HashMap();

        Queue<TreeNodeVerticalLevel> queue = new LinkedList();
        queue.add(new TreeNodeVerticalLevel(root, 0, 0));

        while(!queue.isEmpty()){

            TreeNodeVerticalLevel quNode = queue.poll();

            PriorityQueue<TreeNodeVerticalLevel>  pq = map.getOrDefault(quNode.vLevel, new PriorityQueue<TreeNodeVerticalLevel>());
            pq.add(quNode);
            map.put(quNode.vLevel, pq);

            if(quNode.treeNode.left != null) {
                queue.add(new TreeNodeVerticalLevel(quNode.treeNode.left, quNode.vLevel-1, quNode.hLevel +1 ));
                minVLevel = Math.min(minVLevel, quNode.vLevel-1);
            }

            if(quNode.treeNode.right != null)
                queue.add(new TreeNodeVerticalLevel(quNode.treeNode.right, quNode.vLevel+1, quNode.hLevel +1 ));

        }

        List<List<Integer>> ans = new ArrayList();

        for(int i=minVLevel;  map.containsKey(i); i++){
            List<Integer> subAns = new ArrayList<>();

            PriorityQueue<TreeNodeVerticalLevel> pq = map.get(i);

            while(pq.size()>=1){
                subAns.add(pq.poll().treeNode.val);
            }

            ans.add(subAns);
        }

        return ans;
    }
}
