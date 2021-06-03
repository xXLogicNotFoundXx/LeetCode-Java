/*
https://leetcode.com/problems/number-of-islands-ii/

A 2d grid map of m rows and n columns is initially filled with water. 
We may perform an addLand operation which turns the water at position (row, col) into a land. 
Given a list of positions to operate, count the number of islands after each addLand operation. 
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
You may assume all four edges of the grid are all surrounded by water.
Example:

Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
Output: [1,1,2,3]
Explanation:

Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

0 0 0
0 0 0
0 0 0
Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

1 0 0
0 0 0   Number of islands = 1
0 0 0
Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

1 1 0
0 0 0   Number of islands = 1
0 0 0
Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

1 1 0
0 0 1   Number of islands = 2
0 0 0
*/
class Solution {
    
    int[] parent; 
    
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        
        List<Integer> ans = new ArrayList<>();
        if(m<=0 && n<= 0)
            return ans;
        
        int[][] data = new int[m][n]; 
        
        parent = new int[m*n];
        for(int i=0; i<m*n; i++)
            parent[i]=i;
        
        int total=0;
        
        for(int i=0; i<positions.length; i++){
            total = createIsland(m, n, positions[i][0],positions[i][1], data, total);
            ans.add(total);
        }
        
        return ans;
    }
    
    
    int createIsland(int m, int n, int x, int y, int[][] data, int total){
        
        if(x<0 || x>=m || y<0 || y>=n || data[x][y]==1)
            return total; 
        
        
        data[x][y] = 1;
        int newIsland =  total + 1; 
        
        int[][] directions = new int[][]{{-1,0}, {1,0}, {0,-1}, {0,1}};
        
        for(int[] dir : directions){
            int x1 = x + dir[0];
            int y1 = y + dir[1];
            
            if(x1<0 || x1>=m || y1<0 || y1>=n || data[x1][y1]==0)
                continue; 
            
            int pos1 = x*n + y;  
            int pos2 = x1*n + y1;
            
            if(isDisjointSet(pos1,pos2)){
                newIsland--;
                union(pos1,pos2);
            }
        }
        
        return newIsland;
    }
        
    int find(int node){
        if(parent[node]==node)
            return node;
        
        parent[node] = find(parent[node]);
        return parent[node];
    }

    boolean isDisjointSet(int node1, int node2){
        return find(node1) != find(node2);
    }
    
    void union(int node1, int node2){
        int p1 = find(node1);
        int p2 = find(node2);
        
        parent[p1] = p2; 
    }
    
}
