/*
https://leetcode.com/problems/minesweeper/
Just like minesweeper playing but only with click ( no right click no double click)
You are given a 2D char matrix representing the game board. 
'M' represents an unrevealed mine, 
'E' represents an unrevealed empty square.

When click happens.
If a mine ('M') is revealed, 
    then the game is over - change it to 'X' and return.
If a empty ('E') is revealed, 2 things could happen: 
    1 mark it to 'B' (represents a revealed blank square that has no adjacent mine) and then we gotta reveal 8 neighbours. 
OR  2 mark it to ('1' to '8') representing the number of adjacent mines and stop . 
*/
class Solution {
    int directions[][] = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
    public char[][] updateBoard(char[][] board, int[] click) {
        int i=click[0], j=click[1];
        
        if(board[i][j]=='M')
            board[i][j]='X';
        else
            updateBoard(board, i, j);
        
        return board;
    }
    
    private void updateBoard(char[][] board, int i, int j){
        if(i>=board.length || i<0 || j>=board[0].length || j<0 || board[i][j]!='E')
            return;
        
        int minesNearMe = minesNearMe(board,i,j);
        if(minesNearMe>0){
            // this is number we gotta stop after this.
            board[i][j]= (char) ('0' + minesNearMe);
        } else{
            // this is B so we have to explore recursively 
            board[i][j]='B';
            for(int []dir : directions){
                updateBoard(board,i+dir[0],j+dir[1]);
            }
        }
    }
    
    int minesNearMe(char[][] board, int i, int j){
        int count=0;
        for(int []dir : directions){
            count += hasMine(board,i+dir[0],j+dir[1]);
        }
        return count;
    }
    
    int hasMine(char[][] board, int i, int j){
        if(i>=board.length || i<0 || j>=board[0].length || j<0 || board[i][j]!='M')
            return 0;
        return 1;
    }
}
