/*
https://leetcode.com/problems/sudoku-solver/
Write a program to solve a Sudoku puzzle by filling the empty cells.
*/
class Solution {
    public void solveSudoku(char[][] board) {
        solve(board);
    }
    
    boolean solve(char[][] board){
        for(int i=0;i<9;i++){
           for(int j=0;j<9;j++){
             if(board[i][j]=='.'){
                 
                for(char value='1';value<='9';value++){
                     if(isValid(board,i,j,value)){
                         board[i][j]=value;
                         if(solve(board))
                             return true;
                         board[i][j]='.';
                     }   
                 }
                 return false;
             }
           }
        }
        return true;
    }
    
    boolean isValid(char[][] board, int row, int col, char value){
        for(int i=0;i<9;i++){
            if(board[row][i]==value) 
                return false; //check row 
            if(board[i][col]==value) 
                return false; //check col
        }
        
        // check block - first position in the block 
        int blRow = row/3*3;
        int blCol = col/3*3;
        
        for(int i=blRow;i<blRow+3;i++){
            for(int j=blCol;j<blCol+3;j++){
                if(board[i][j]==value) 
                    return false;  
            }
        }
        
        return true;
    }
}
