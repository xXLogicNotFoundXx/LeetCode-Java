/*
https://leetcode.com/problems/search-a-2d-matrix/
Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
Eg matrix :
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]

[0][0] is the smalles element, [n][n] is the largest element.
next element is guaranteed bigger thant privous if we do normal matrix traverse
there is a calculation you can do so that you can treat as single array like index 0,1,2,(m*n-1)
Consider matrix 4X4 -- and these are the linear indexes not actually numbers in the matrix.
0  1  2  3
4  5  6  7
8  9  10 11
12 13 14 15

10th number how do you calculate row and col?
row is = 10/4(col_Number) = 2 ( so 3rd row in the matrix)
col is = 10%4(col_number) = 2 (i.e 3rd number in that row)
matrix[2][4] = 10

So in a sorted matrix you can do the binary search!!!
*/
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix==null || matrix.length==0 || matrix[0].length==0) return false;
        int row = matrix.length;
        int col = matrix[0].length;
        int left=0, right = row*col-1; // 0-15 for 4X4 matrix
        while(left<=right){
            int mid = (left + right )/2;
            int rw = mid/col;   // let this sink in  let say in is 4X4 matrix and mid = 11   rw= 11/4 = 2nd index (i.e 3rd row)
            int cl = mid%col;   // mid = 11 cl= 11%4 = 3rd index (i.e last number in that row)

            if(target==matrix[rw][cl])
                return true;

            if(target<matrix[rw][cl])
                right = mid-1;
            else
                left = mid+1;
        }
        return false;
    }
}

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
            if(target == matrix[row][col])
                return true;

            if(target > matrix[row][col])
                row++;
            else
                col--;
        }
        return false;
    }
}
