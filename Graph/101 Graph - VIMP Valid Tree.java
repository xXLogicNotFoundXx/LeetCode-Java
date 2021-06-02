/*
https://leetcode.com/problems/graph-valid-tree/

Graph Valid Tree (bi-directional):
valid tree is where you visit all nodes in the graph starting from any node and there is no cycle. 
 valid       invalid 
   1             1
  /  \          / \
 2    3        2   3
 |\   |         \ /
 4 5  6          4


Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
Output: true

Edge Cases You have to think: 

 The edges are in any order, not necessarily follows the graph connection. (see edge [0,2] is not given)
 n = 3
 edges = [[1,0],[2,0]]
 Output: true
         This is tricky one, this mean you have to build bi-derectional adjacency graph.  
 
 n=1, edges = []
 Output: true

Questions:
  Are there there self loops? 
  Are there any repeated edges?
  Are there nodes out of range?

There are no self-loops or repeated edges.
  
*/
class Solution {
    // assert all num appear in the edges 0 ~ n - 1
    public boolean validTree(int n, int[][] edges) {
        if (edges == null) 
            return true;
        
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) 
            map.put(i, new HashSet<>());
        
        for (int[] edge : edges) {
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }
        
        Set<Integer> set = new HashSet<>();
        
        return dfs(0, map, set)==true ? set.size() == n : false;
    }
    
    public boolean dfs(int cur, Map<Integer, Set<Integer>> map, Set<Integer> set) {
        
        if (set.contains(cur)) 
            return false;
     
        set.add(cur);
        for (Integer next : map.get(cur)) {    // you can iterate over set like this! 
            
            map.get(next).remove(cur);// remove parent from the child ...  this is VIMP  very important
            // if we hit parent from some other path then there is a cycle
         
            if(!dfs(next, map, set))
                return false;
        }
        return true;
    }
}
