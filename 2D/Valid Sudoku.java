/*
https://leetcode.com/problems/valid-sudoku/
Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

Each row must contain the digits 1-9 without repetition.
Each column must contain the digits 1-9 without repetition.
Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.
*/
class Solution {
    // The question is is there way we can generate unique combination for each row,column and block
    // 9 rows 9 coloumns and 9 blocks it is easy
    // we can say R0,R1..R8 is the code for rows
    // similaryly C0...C8 and
    // B00 B01 B02,
    // B10 B11 B12 for blocks ... we can store it in a set if set has already that nubmer return false 
    // Or we can genrate unique combination for every i,j somehows and store it in one set(not hashmap)
    public boolean isValidSudoku(char[][] board) {
        Set<String> set = new HashSet<>();

        for (int i=0; i<9; ++i) {
            for (int j=0; j<9; ++j) {
                char number = board[i][j];

                if (number != '.'){

                    String row =  "R" + i + number;
                    String col =  "C" + j + number;
                    String block =  "B" + i/3 + j/3 + number;

                    if(set.contains(row) || set.contains(col) || set.contains(block))
                        return false;

                    set.add(row);
                    set.add(col);
                    set.add(block);
                }
            }
        }
        return true;
    }

    public boolean isValidSudoku(char[][] board) {
        Map<String,Set<Character>> map = new HashMap<>();

        for (int i=0; i<9; ++i) {
            for (int j=0; j<9; ++j) {
                char number = board[i][j];

                if (number != '.'){


                    String row =  "R" + i + number;
                    String col =  "C" + j + number;
                    String block =  "B" + i/3 + j/3 + number;

                    map.putIfAbsent(row, new HashSet<Character>());
                    map.putIfAbsent(col, new HashSet<Character>());
                    map.putIfAbsent(block, new HashSet<Character>());

                    if(map.get(row).contains(number) || map.get(col).contains(number) || map.get(block).contains(number))
                        return false;

                    map.get(row).add(number);
                    map.get(col).add(number);
                    map.get(block).add(number);
                }
            }
        }
        return true;
    }

}
