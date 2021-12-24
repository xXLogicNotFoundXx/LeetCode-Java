/*
https://leetcode.com/problems/redundant-connection/

Return an edge that can be removed so that the resulting graph is a tree of N nodes.
Input: [[1,2], [1,3], [2,3]]
Output: [2,3]
Explanation: The given undirected graph will be like this:
  1
 / \
2 - 3    => 2 connecting to 3 is redudant bcz they were already connected by 1. 

Input: [[1,2], [2,3], [3,4], [1,4], [1,5]]
Output: [1,4]
Explanation: The given undirected graph will be like this:
5 - 1 - 2
    |   |
    4 - 3

https://en.wikipedia.org/wiki/Disjoint-set_data_structure
A disjoint-set forest consists of a number of elements each of which stores an id, a parent pointer, 
Find(x) follows the chain of parent pointers from x up the tree until it reaches a root element, whose parent is itself. 
Path compression flattens the structure of the tree by making every node point to the root whenever Find is used on it. 
This is valid, since each element visited on the way to a root is part of the same set. The resulting flatter tree speeds up 
future operations not only on these elements, but also on those referencing them.
// there is Union by ranking and size too but we havent used either of that here. 
*/
class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int []parents = new int[edges.length+1];
        for(int i=1;i<edges.length+1;i++)
            parents[i]=i;
        
        for(int [] e : edges){
            if(!union(parents,e[0],e[1]))
                return e;
        }
        return new int[]{0,0};
    }
    
    boolean union(int [] parents, int x, int y){
        int xRoot = find(parents, x);
        int yRoot = find(parents, y);
        
        if(xRoot==yRoot)
            return false; 
        
        parents[yRoot] = xRoot ;
        // OR parents[xRoot] = yRoot; // doesnt matter 
        return true;
    }
    
    int find(int []parents, int x){
        if(parents[x]!=x){
            parents[x] = find(parents,parents[x]); // path compression
        }
        return parents[x];
    }
}
