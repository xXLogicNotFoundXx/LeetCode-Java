/*
https://leetcode.com/problems/n-ary-tree-level-order-traversal/
Given an n-ary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
We should return its level order traversal:
[
     [1],
     [3,2,4],
     [5,6]
]

BFS
*/
class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();

        if(root==null) return ans;

        Queue<Node> queue = new LinkedList<>();
        // ArrayDeque, ConcurrentLinkedDeque, ConcurrentLinkedQueue, LinkedList, PriorityQueue ....

        queue.add(root);

        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> oneLevel = new ArrayList<>();
            for(int i=0; i<size; i++){
                Node node = queue.poll();
                oneLevel.add(node.val);

                for(Node child : node.children){
                    queue.add(child);
                }

            }
            ans.add(oneLevel);
        }

        return ans;
    }
}

// DFS
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
