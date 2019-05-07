/*
https://leetcode.com/problems/search-a-2d-matrix-ii/
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
    Integers in each row are sorted in ascending from left to right.
    Integers in each column are sorted in ascending from top to bottom.
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]

[0][0] is the smalles element, [n][n] is the largest element. 
there are two position you can start with [0][n] and [n][0]. 
for [0][n]  if the target is less than [0][n]     then go to [0][n-1]  (bcz whole that nth column is greater )
            if the target is greater than [0][n]  then go to [0+1][n] (bcz 0th row is is less than [0][n] number)
O(m+n)
*/
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length ==0) 
            return false; 
        
        int row = 0;
        int col = matrix[0].length-1;
        while(row<matrix.length && col>=0){
            if(target < matrix[row][col])
                col--;
            else if(target > matrix[row][col])
                row++;
            else 
                return true;
        }
        return false;
    }
}
