/*
59. Spiral Matrix II
https://leetcode.com/problems/spiral-matrix-ii/
Given a positive integer n, generate a square matrix filled with elements 
from 1 to n^2 in spiral order.
following algo wont work for m*n 
*/
class Solution {
    public int[][] generateMatrix(int n) {
        if(n<=0)
            return new int[0][0];
        
        int [][]matrix = new int [n][n]; 
        int left=0, right=n-1;
        int top=0,  bottom=n-1;
        int count=1;
        while(left<=right && top<=bottom){
            for(int i=left;i<=right;i++)
                matrix[top][i] = count++;
            top++;
            for(int i=top;i<=bottom;i++)
                matrix[i][right] = count++;
            right--;
            for(int i=right;i>=left;i--)
                matrix[bottom][i] = count++;
            bottom--;
            for(int i=bottom;i>=top;i--)
                matrix[i][left] = count++;
            left++;
        }
        return matrix;
    }
}
