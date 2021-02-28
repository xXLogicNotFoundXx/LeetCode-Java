/*
https://leetcode.com/problems/number-of-islands/
1 is land and 0 is water 
Input:
11000
11000
00100
00011
Output: 3
I have used another grid to track visited which I dint need and I could just modify original grid
*/
class Solution {
    public int numIslands(char[][] grid) {
        if(grid==null || grid.length ==0) return 0;
        
        int m = grid.length;
        int n = grid[0].length;
        int count=0;
        
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                
                if(grid[i][j]=='1'){
                    traverse(grid,i,j);
                    count++; 
                }  
            }
        }
        return count;
    }
    
    void traverse(char[][] grid, int m, int n){
        if(m<0 || n <0 || m>=grid.length || n >=grid[0].length || grid[m][n] == '0')
            return;
        
        grid[m][n]='0';
        traverse(grid,m+1,n);
        traverse(grid,m-1,n);
        traverse(grid,m,n+1);
        traverse(grid,m,n-1);
 
    }
}

/*
Another similar problem : 
https://leetcode.com/problems/max-area-of-island/
so counting the numbers of 1's in the island 
*/

class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        for(int i=0;i<grid.length;i++){
           for(int j=0;j<grid[0].length;j++){
                max = Math.max(max, findArea(i,j,grid));
           }
        }
        return max; 
    }
    
    int findArea(int i, int j, int[][] grid){
        
        if( i<0 || i >= grid.length || j<0 || j >= grid[0].length || grid[i][j]==0)
            return 0;
        
        grid[i][j]=0;
        int area  = findArea(i+1,j,grid) + 
                    findArea(i-1,j,grid) +
                    findArea(i,j+1,grid) +
                    findArea(i,j-1,grid) ;
        return 1 + area;
    }
}

