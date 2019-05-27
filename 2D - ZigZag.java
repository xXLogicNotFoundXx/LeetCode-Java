/*
https://leetcode.com/problems/diagonal-traverse/
Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order as shown in the below image.
 [ 1, 2, 3 ]
 [ 4, 5, 6 ]
 [ 7, 8, 9 ]
Output:  [1,2,4,7,5,3,6,8,9]
*/
class Solution {
     /*
        I tried traverasl switch with havinf layer and then layer 0,2,4 going up
        and layers 1,3,5 going down it became to complicated.
        So travering in one direction only i.e. do up only and then reverting result set whenver necessary.
      */
    public int[] findDiagonalOrder(int[][] matrix) {
        if(matrix == null || matrix.length == 0)
            return new int[0];
        
        int m = matrix.length;
        int n = matrix[0].length;
        int[] res = new int[m * n];
        int count = 0;
        
        for(int i = 0; i < m + n - 1; i++) { // number of layers diagonaly traversed
            int column = Integer.max(0, i - m + 1);
            int row = Integer.min(i, m - 1);
            int start = count;
            while(row >= 0 && column < n) {
                res[count ++] = matrix[row][column];
                row--;
                column++;
            }
            int end = count - 1;
            if(i % 2 == 1)
                reverse(res, start, end);
        }
        return res;
    }
    
    public void reverse(int[] nums, int start, int end) {
        while(start < end) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end--;
        }
    }
}
