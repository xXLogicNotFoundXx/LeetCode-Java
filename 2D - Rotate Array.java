

/*
https://leetcode.com/problems/rotate-image/
swap the symmetry  then swap lefft <-> right.
1  2  3    1  4  7    7  4  1     
4  5  6 => 2  5  8 => 8  5  2
7  8  9    3  6  9    9  6  3

after transpose, it will be swap(matrix[i][j], matrix[j][i])
swap(matrix[i][j], matrix[i][matrix.length-1-j])
*/
class Solution {
    public void rotate(int[][] matrix) {
        for(int i = 0; i<matrix.length; i++){
            for(int j = i; j<matrix[0].length; j++){ .  // see j=i only half the matrxi is traversed 
                int temp = 0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for(int i =0 ; i<matrix.length; i++){
            for(int j = 0; j<matrix.length/2; j++){ // see j<matrix.length/2 only half the matrxi is traversed 
                int temp = 0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length-1-j];
                matrix[i][matrix.length-1-j] = temp;
            }
        }
    }
}
    
  

/*
OR
    * clockwise rotate
    * first reverse up to down, then swap the symmetry 
    * 1 2 3     7 8 9     7 4 1
    * 4 5 6  => 4 5 6  => 8 5 2
    * 7 8 9     1 2 3     9 6 3
    *
    *
    * anticlockwise rotate
    * first reverse left to right, then swap the symmetry
    * 1 2 3     3 2 1     3 6 9
    * 4 5 6  => 6 5 4  => 2 5 8
    * 7 8 9     9 8 7     1 4 7
*/
public void rotate(int[][] matrix) {
    int n=matrix.length;
    for(int i=0;i<n/2;i++,i++){
        for(int j=0;j<n;j++){
            int temp = matrix[i][j];
            matrix[i][j] = matrix[n-1-i][j];
            matrix[n-1-i][j] = temp;
        }
    }

    for(int i = 0; i<matrix.length; i++){
        for(int j = i; j<matrix[0].length; j++){
            int temp = 0;
            temp = matrix[i][j];
            matrix[i][j] = matrix[j][i];
            matrix[j][i] = temp;
        }
    }
}


