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
            for(int j = i; j<matrix[0].length; j++){ .  // see j=i only half the matrxi is traversed 
                int temp = 0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for(int i =0 ; i<matrix.length; i++){
            for(int j = 0; j<matrix.length/2; j++){ // see j<matrix.length/2 only half the matrix is traversed 
                int temp = 0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length-1-j];
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
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
}

/*  Anticlockwise rotate 90 degree
    
    1. first reverse left to right,
    2. swap the symmetry
    
    1 2 3     3 2 1     3 6 9
    4 5 6  => 6 5 4  => 2 5 8
    7 8 9     9 8 7     1 4 7
*/

/*
https://leetcode.com/problems/spiral-matrix/
Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
*/
class Solution {
    public List<Integer> spiralOrder(int[][] a) {
       
        List<Integer> list = new ArrayList<Integer>();
        if(a.length == 0) return list;

        int left=0, right=a[0].length-1;
        int top=0,  bottom=a.length-1;
        
        while(left<=right && top<=bottom){
            for(int i=left;i<=right;i++) 
                list.add(a[top][i]); 
            top++;
            for(int i=top;i<=bottom;i++) 
                list.add(a[i][right]);
            right--;
            
            if(top<=bottom){    // IMP 
                for(int i=right;i>=left;i--) 
                    list.add(a[bottom][i]);
                bottom--;
            }
            if(left<=right){   // IMP 
                for(int i=bottom;i>=top;i--) 
                    list.add(a[i][left]);
                left++;
            }
        }
        return list;
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
