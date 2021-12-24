/*
https://leetcode.com/problems/toeplitz-matrix/
A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same element.
Now given an M x N matrix, return True if and only if the matrix is Toeplitz.
  [1,2,3,4],
  [5,1,2,3],
  [9,5,1,2]
Output: True
Explanation:
In the above grid, the diagonals are:
"[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]".
In each diagonal all elements are the same, so the answer is True.
*/
class Solution {
    public boolean isToeplitzMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length - 1; i++) {
            for (int j = 0; j < matrix[i].length - 1; j++) {
                if (matrix[i][j] != matrix[i + 1][j + 1]) return false;
            }
        }
        return true;
    }
}

/*
https://leetcode.com/problems/transpose-matrix/
867. Transpose Matrix : Rows becomes column

                                  (This is why you need another 2D array)

[1,2,3]         [1,4,7]           [1,2]      [1,3,5]
[4,5,6]    =>   [2,5,8]       OR  [3,4]  =>  [2,4,6]
[7,8,9]         [3,6,9]           [5,6]
*/
class Solution {
    public int[][] transpose(int[][] A) {
        int M = A.length, N = A[0].length;
        int[][] B = new int[N][M];
        for (int j = 0; j < N; j++)
            for (int i = 0; i < M; i++)
                B[j][i] = A[i][j];
        return B;
    }
}

/*
https://leetcode.com/problems/sparse-matrix-multiplication/
Given two sparse matrices A and B, return the result of AB.
You may assume that A's column number is equal to B's row number.
A
  [ 1, 0, 0],
  [-1, 0, 3]
B
  [ 7, 0, 0 ],
  [ 0, 0, 0 ],
  [ 0, 0, 1 ]
Output:
     |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
                  | 0 0 1 |
*/
public class Solution {
    public int[][] multiply(int[][] A, int[][] B) {
        int m = A.length, n = A[0].length, nB = B[0].length;
        int[][] C = new int[m][nB];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                for (int k = 0; k < nB; k++) {
                        C[i][k] += A[i][j] * B[j][k];
                }
            }
        }
        return C;
    }
}
