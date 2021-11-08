/*
Game of life : 
https://leetcode.com/problems/game-of-life/
https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life

Medium 

The board is made up of an m x n grid of cells, where each cell has an initial state: live (represented by a 1) or dead (represented by a 0). 
Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

Any live cell with fewer than two live neighbors dies as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population.
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.

Solution 1 : 
We need live count to make a decision for current cell. 
Simultaniously you cant update the board. 
So create a copy and use that copy to change the original board.

Solution 2: 
Inpace.
There is a trick that you have to come up. 

*/
class Solution {
    public void gameOfLife(int[][] board) {
        int[][] board1 = new int[board.length][board[0].length];
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                board1[i][j] = board[i][j];
            }
        }
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                int live = getNeighbors(board1,i,j);
                if(live<2)
                    board[i][j] = 0;
                else if(live>3)
                    board[i][j] = 0;
                else if(live==3)
                    board[i][j] = 1;
            }
        }
    }
    
    int getNeighbors(int[][] board, int i,int j){
        int liveN = 0;
        int [][] dir = {{-1,0},{-1,1},{-1,-1},{1,0},{1,1},{1,-1},{0,-1},{0,1}};
        for(int []d : dir){
            int k=i+d[0];
            int l=j+d[1];
            if(k<0 || l< 0 || k >= board.length || l >= board[0].length ) continue;
            if(board[k][l] == 1)
                liveN++;
        }
        return liveN;
    } 
}

