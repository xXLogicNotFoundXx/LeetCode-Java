/*
Medium - VIMP
LinkedIn14 Amazon8 Facebook8 and many more


https://leetcode.com/problems/binary-tree-level-order-traversal
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]
*/

class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        Map<Integer,List<Integer>> map = new HashMap<Integer,List<Integer>>();
        traverse(root,0,map);
        for(int i=0;map.containsKey(i);i++)
            ans.add(map.get(i));
        return ans;
    }
    // creates a map of <level, nodes>   node is added to the beginning if level is an odd nummber
    public void traverse(TreeNode root, int level, Map<Integer,List<Integer>> map){
        if(root==null) return;

        List<Integer> subAns = map.getOrDefault(level,new ArrayList<Integer>());
        if(level%2==0)
            subAns.add(root.val);
        else
            subAns.add(0,root.val);       // List can insert the element at the beginning
        map.put(level,subAns);

        traverse(root.left,level+1,map);
        traverse(root.right,level+1,map);
    }
}
