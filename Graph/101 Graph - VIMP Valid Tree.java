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
    /*
        Approach One : 
           Check if there are any cycles. 
           Once you visite all edges see if we visited all nodes. 
           
        10ms 
        Time - O(N+E)     N - Creating Adjacency Map.  E - We traverse all edges. 
        Space - O(N+E)
    */
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

// This is one is really smart i you consider the fact that we need exactly n-1 edges and we should be able visit all nodes. 
// Kinda trick that you have to know. 
class Solution {
    /*
    Approach two : 
        Graph Theroy - For the graph to be a valid tree, it must have exactly n - 1 edges and all nodes must be visited when you traverse the graph.
        
        Going by this definition, our algorithm needs to do the following:
            Check whether or not there are n - 1 edges. If there's not, then return false.
            Check whether or not the graph is fully connected. Return true if it is, false if otherwise.
     1 ms
     Time - O(N)   - We always check if there are n-1 edges. So O(N (Map creation) + (N-1) edges) => O(N)
     Space - O(N)
    */
    public boolean validTree(int n, int[][] edges) {
        
        if(edges.length != n-1)
            return false; 
        
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) 
            map.put(i, new HashSet<>());
        
        for (int[] edge : edges) {
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }
        
        Set<Integer> set = new HashSet<>();
        dfs(0, map, set);
        return set.size() == n;
    }
    
    public void dfs(int cur, Map<Integer, Set<Integer>> map, Set<Integer> set) {
        
        set.add(cur);
        for (Integer next : map.get(cur)) {    // you can iterate over set like this! 
            if(set.contains(next))
                continue;
            dfs(next, map, set);
        }
    }
}


class Solution {
    /*
    Approach Three : 
        Graph Theroy - For the graph to be a valid tree, it must have exactly n - 1 edges.
        Cycle detection - by Union Find 
        IF graph has n - 1 edges and no cycle then it is a valid tree. (Provides edges does not have duplicate data)
        
     Time - O(N)   (E is a varient of N which is n-1 so it becomes 2N -> O(N))
     Space - O(N)
    */
    int[] parent; 
    
    public boolean validTree(int n, int[][] edges) {
        
        if(edges.length != n-1)
            return false; 
        
        parent = new int[n];
        for(int i=0; i<n; i++)
            parent[i] = i;
        
        for(int[] edge : edges){
            if(!union(edge[0], edge[1]))
                return false;
        }
        
        // we traverse all n-1 edges and there was no cycle it is a valid tree 
        return true ; 
    }
    
    int find(int node){
        if(parent[node]==node)
            return node; 
        
        parent[node] = find(parent[node]);
        return parent[node];
    }
    
    // if cycle found returns true 
    boolean union(int node1, int node2){
        int oneParent = find(node1);
        int twoParent = find(node2);
        
        if(oneParent == twoParent)
            return false; 
        
        parent[oneParent] = twoParent;
        return true;
    }
    
}
