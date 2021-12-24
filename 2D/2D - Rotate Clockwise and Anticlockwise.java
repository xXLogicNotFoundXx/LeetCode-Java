
/*
https://leetcode.com/problems/rotate-image/
Rotate the image by 90 degrees (Cloclwise)
1  2  3      7  4  1
4  5  6   => 8  5  2
7  8  9      9  6  3
*/
class Solution {
 /*.  Solution One
      1. swap the symmetry
      2. swap left <-> right
      1  2  3      1  4  7     7  4  1
      4  5  6  =>  2  5  8  => 8  5  2
      7  8  9      3  6  9     9  6  3
      */
    public void rotate(int[][] matrix) {

        for(int i = 0; i<matrix.length; i++){
            for(int j = 0; j<i; j++){   //  only half (lower left diagonal) matrix is traversed
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        for(int i =0 ; i<matrix.length; i++){
            for(int j = 0; j<matrix[0].length/2; j++){ // only half the matrix is traversed
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length-1-j]; // very IMP calculations
                matrix[i][matrix.length-1-j] = temp;   // very IMP calculations
            }
        }
    }

    /* Solution Two
      1. first reverse up <-> down,
      2. swap the symmetry

      1 2 3      7 8 9     7 4 1
      4 5 6   => 4 5 6  => 8 5 2
      7 8 9      1 2 3     9 6 3
    */
    public void rotate(int[][] matrix) {
        for(int i=0; i<matrix.length/2; i++){
            for(int j=0; j<matrix[0].length; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[matrix.length-1-i][j];
                matrix[matrix.length-1-i][j] = temp;
            }
        }

        for(int i = 0; i<matrix.length; i++){
            for(int j = 0; j<i; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
}

/*  Anticlockwise rotate 90 degree
  Solution 1
    1. swap symetry
    2. swap up<->down

    1  2  3      1  4  7      3 6 9
    4  5  6  =>  2  5  8  =>  2 5 8
    7  8  9      3  6  9      1 4 7

  Solution 2
    1. first reverse left <-> right,
    2. swap the symmetry

    1 2 3     3 2 1     3 6 9
    4 5 6  => 6 5 4  => 2 5 8
    7 8 9     9 8 7     1 4 7
*/
