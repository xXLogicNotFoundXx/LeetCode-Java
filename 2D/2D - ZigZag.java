/*
Medium FB-27

Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order as shown in the below image.
 [ 1, 2, 3 ]
 [ 4, 5, 6 ]
 [ 7, 8, 9 ]
Output:  [1,2,4,7,5,3,6,8,9]
*/
class Solution {
      public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return new int[0];

        int row = 0, col = 0, pos = 0, m = matrix.length, n=matrix[0].length, output [] = new int[m * n];

        for (pos = 0; pos < m * n; pos++) {
             output[pos] = matrix[row][col];

             if ((row + col) % 2 == 0) {
                 // The direction is always up when the sum of row & col is even
                 // The order of if else condition is also very important

                 // For last column, go down
                 if (col == n-1) { row++; }

                 // For first row & non-last columns, go right
                 else if (row == 0) { col++; }

                 // For not first row & non-last columns, go up and to the right
                 else { row--; col++; }

             } else {
                 // The direction is always down when the sum of row & col is odd
                 // The order of if else condition is also very important

                 // For last row, go right
                 if (row == m-1) { col++; }

                 //  For non-last row & first column, go down
                 else if (col == 0) { row++; }

                 // For non-last row & non-first column, go down and to the left
                 else { row++; col--; }
             }
        }
        return output;
     }
}
