/*
https://leetcode.com/problems/number-of-closed-islands/

Given a 2D grid consists of 0s (land) and 1s (water).
An island is a maximal 4-directionally connected group of 0s and
a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.

Return the number of closed islands.
*/
class Solution {

    // O(M*N) .. we are traversing the whole matrix twice 2*M*N
    // commulative of the DFS calls gonna me max M*N
    // so 3*M*N -> O(M*N)
    // Space O(M*N)
    public int closedIsland(int[][] grid) {
        int maxR = grid.length-1;
        int maxC = grid[0].length-1;

        // if boundary and zero then mark all connected as 1
        // we dont care about the island connected to the   boundary 
        for(int i=0; i<=maxR; i++){
            for(int j=0; j<=maxC; j++){
                if(grid[i][j]==0 && (i==0 || j==0 || i==maxR || j==maxC )){
                    markZerosAsOne(grid,i,j,maxR,maxC);
                }
            }
        }

        int count=0;
        for(int i=0; i<=maxR; i++){
            for(int j=0; j<=maxC; j++){
                if(grid[i][j]==0){
                    count++;
                    markZerosAsOne(grid,i,j,maxR,maxC);
                }
            }
        }

        return count;
    }

    void markZerosAsOne(int[][] grid, int x, int y, int maxR, int maxC){
        if(x<0 || y<0 || x>maxR || y>maxC)
            return;

        if(grid[x][y]==0){
            grid[x][y]=1;
            markZerosAsOne(grid,x-1,y,maxR,maxC); // up
            markZerosAsOne(grid,x+1,y,maxR,maxC); // down
            markZerosAsOne(grid,x,y-1,maxR,maxC); // left
            markZerosAsOne(grid,x,y+1,maxR,maxC); // right
        }
    }
}
