https://leetcode.com/problems/n-ary-tree-level-order-traversal/
Given an n-ary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
We should return its level order traversal:
[
     [1],
     [3,2,4],
     [5,6]
]

Build a map of <level,nodes> while traversing the tree and then iterate through map.
class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        Map<Integer,List<Integer>> map = new HashMap<Integer,List<Integer>>();
        
        traverse(root,0,map);
        for(int i=0;map.containsKey(i);i++)
            ans.add(map.get(i));
        return ans;
    }
    // creates a map of <level, nodes>
    public void traverse(Node root, int level, Map<Integer,List<Integer>> map){
        if(root==null) return;
        
        List<Integer> subAns = map.getOrDefault(level,new ArrayList<Integer>());     // getOrDefault method sweet 
        subAns.add(root.val);
        map.put(level,subAns);
        
        for(Node n : root.children){
            traverse(n,level+1,map);
        }
    }
}
