/*
HArd - IMP
DoorDash25 msft9 amzn9 google5 uber3 appl5

https://leetcode.com/problems/sudoku-solver/
Write a program to solve a Sudoku puzzle by filling the empty cells.
The '.' character indicates empty cells.

9x9 board AND It is guaranteed that the input board has only one solution.



Well you can replace '.' with 1-9 number and check if it is ok. Then move to next '.'.
How do you check if putting a number is valid?

You can have isValid mehtod that could tell us. ( Check row, check column and a block ).
it is 9x9 matric so travesring this matrix is trivial.

But if everything is '.' in a matrix then each empty space has 9 possible values.
So it will be 9^*. and there are 81 cell positions so

Time = O(9^81).
Space = O(81) 81 call stack.
*/

// Solution 1 : not the best O(9^81)
class Solution {
    public void solveSudoku(char[][] board) {
        solve(board);
    }

    boolean solve(char[][] board){
        for(int i=0;i<9;i++) {
           for(int j=0;j<9;j++) {

             if(board[i][j]=='.'){

                for(char value='1';value<='9';value++){
                     if(isValid(board,i,j,value)){
                         board[i][j]=value;

                         if(solve(board))
                            return true;
                         else
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
        // Check row and column doesnt not have that number OR char value.
        for(int i=0; i<9; i++){
            if(board[row][i]==value)
                return false; // check row

            if(board[i][col]==value)
                return false; // check col
        }

        // Check block - first position in the block
        int blRow = row/3 * 3;
        int blCol = col/3 * 3;

        for(int i=blRow; i<blRow+3 ; i++){
            for(int j=blCol; j<blCol+3; j++){
                if(board[i][j]==value)
                    return false;
            }
        }

        return true;
    }
}


// If we could somehow note that what elements are not allowed in  paricular row, column and block
// that would reduce some complexity as we go on building these row, column, block structure.
// what could be this structure ?
// we can have  HashMap<Integer,Set<Integer>> for row , similarly for column
// For block we can have  HashMap<String,Set<Integer>>, string identifies the unique block 3x3.
/*
Let's consider one row, i.e. not more than 9 cells to fill. There are not more than 9 possibilities for the first number to put,
not more than 9×8 for the second one, not more than 9×8×7 for the third one etc.
In total that results in not more than 9! possibilities for a just one row, that means not more than (9!)^9 operations in total.
the number of operations is reduced in 10^27.
9^81  = 196627050475552913618075908526912116283103450944214766927315415537966391196809
9!)^9 = 109110688415571316480344899355894085582848000000000
Time : O( (9!)^9 )
Space : O(81)
*/

// Solution 2  - (9!)^9
class Solution {
    public void solveSudoku(char[][] board) {

        HashMap<Integer,Set<Character>> row = new HashMap<>();
        HashMap<Integer,Set<Character>> column = new HashMap<>();
        HashMap<String,Set<Character>> block = new HashMap<>();


        for(int i=0; i<9; i++){
            row.putIfAbsent(i, new HashSet<Character>());

            for(int j=0; j<9; j++){
                column.putIfAbsent(j, new HashSet<Character>());
                block.putIfAbsent(i/3+"-"+j/3, new HashSet<Character>());

                // the '.' doesnt matter being in the set.
                row.get(i).add(board[i][j]);
                column.get(j).add(board[i][j]);
                block.get(i/3 +"-"+ j/3).add(board[i][j]);
            }
        }

        solve(board, row, column, block);
    }

    boolean solve(char[][] board,
                  HashMap<Integer,Set<Character>> row,
                  HashMap<Integer,Set<Character>> column,
                  HashMap<String,Set<Character>> block){


        //displayBoard(board);
        for(int i=0; i<9; i++){

           for(int j=0; j<9; j++){

             if(board[i][j]=='.'){

                for(char value='1'; value<='9'; value++){

                     if(row.get(i).contains(value) || column.get(j).contains(value) ||
                        block.get( i/3 +"-"+ j/3).contains(value) )
                         continue;

                     board[i][j]=value;

                     row.get(i).add(value);
                     column.get(j).add(value);
                     block.get(i/3 +"-"+ j/3).add(value);

                     if(solve(board, row, column, block))
                         return true;

                     row.get(i).remove(value);
                     column.get(j).remove(value);
                     block.get(i/3 +"-"+ j/3).remove(value);

                 }

                 board[i][j]='.'; // this is because we may end up not solving sudoku 1-9 and then
                 // we have to backtrack and change the value. So,  this current block value should be reset.

                 // 1-9 values did not work
                 return false;

             }

           }
        }

        return true;
    }


    void displayBoard(char[][] board){
        for(int i=0; i<9; i++){
           for(int j=0; j<9; j++){
               System.out.print(board[i][j]+" ");
           }
           System.out.println();
        }
    }
}
