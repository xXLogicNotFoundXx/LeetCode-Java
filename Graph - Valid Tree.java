/*
Graph Valid Tree
https://leetcode.com/problems/graph-valid-tree/
Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
Output: true

1. Make sure everything is connected   [0,1] [2,3] is not a valid tree 
2. Make sure No cycle
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
        if (set.contains(cur)) return false;
        set.add(cur);
        for (Integer next : map.get(cur)) {
            map.get(next).remove(cur); // this is very important 
            boolean temp = dfs(next, map, set);
            if (!temp) return false;
        }
        return true;
    }
}
