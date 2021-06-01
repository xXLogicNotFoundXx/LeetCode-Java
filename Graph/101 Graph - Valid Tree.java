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

Questions:
  Are there there self loops? 
  Are there any repeated edges?

There are no self-loops or repeated edges.
  
*/
class Solution {
    // assert all num appear in the edges 0 ~ n - 1
    public boolean validTree(int n, int[][] edges) {
        if (edges == null) return true;
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) 
            map.put(i, new HashSet<>());
        for (int[] edge : edges) {
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }
        
        Set<Integer> set = new HashSet<>();
        boolean temp = dfs(0, map, set);
        
        return temp ? set.size() == n : false;
    }
    
    public boolean dfs(int cur, Map<Integer, Set<Integer>> map, Set<Integer> set) {
        if (set.contains(cur)) 
         return false;
     
        set.add(cur);
        for (Integer next : map.get(cur)) {
            map.get(next).remove(cur);        // this is VIMP  very important
            boolean temp = dfs(next, map, set);
            if (!temp) return false;
        }
        return true;
    }
}
