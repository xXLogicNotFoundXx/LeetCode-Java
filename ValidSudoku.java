/*
https://leetcode.com/problems/valid-sudoku/
Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

Each row must contain the digits 1-9 without repetition.
Each column must contain the digits 1-9 without repetition.
Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.
*/
class Solution {
    // The question is is there way we can generate unique combination for each row,column and block 
    // 9 rows 9 coloumns and 9 blocks it is easy  we can say R0,R1..R8 is the code for rows  
    // similaryly C0...C8 and B0..B8 for blocks ... we can store codes in hashmap with set 
    // Or we can genrate unique combination for every R,C,B board[i,j] somehows and store it in one set(not hashmap)
    public boolean isValidSudoku(char[][] board) {
        Set seen = new HashSet();
        for (int i=0; i<9; ++i) {
            for (int j=0; j<9; ++j) {
                char number = board[i][j];
                if (number != '.')
                    if (!seen.add(number + "R" + i) ||
                        !seen.add(number + "C" + j) ||
                        !seen.add(number + "B" + i/3 + j/3))
                        return false;
            }
        }
        return true;
    }
}
