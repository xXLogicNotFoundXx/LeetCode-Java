/*
https://leetcode.com/problems/maximal-square/
Given a 2D binary matrix filled with 0's and 1's, find the largest SQUARE containing only 1's and return its area.
1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
Output: 4
*/
class Solution {
       
    // DP APPROACH 
    // it same as edit distance problem.
    // dp[i][j] = Math.min(Math.min( dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) 
    // think about this and let that sink in.
    
    public int maximalSquare1(char[][] matrix) {
        if(matrix==null || matrix.length ==0 || matrix[0].length ==0 )
            return 0;
        int [][]dp = new int[matrix.length+1][matrix[0].length+1];
        int maxLength = 0;
        
        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                if(matrix[i-1][j-1]=='1'){
                    
                    int min = Math.min(dp[i-1][j], dp[i][j-1]); // left and up 
                    min = Math.min(min, dp[i-1][j-1]);  // top left 
                                                
                    dp[i][j] = min+1;
                    maxLength = Math.max(maxLength, dp[i][j]);
                }
            }
        }
        
        return maxLength*maxLength; 
    }
    
    // This is better .... easy to understand ... 
    // fuckers did the character[] otherwise you dont even need dp[][]
    // [["0","1"],     need to handle this case ...
    //  ["1","0"]]
    public int maximalSquare(char[][] matrix) {
        
        int[][] intMat = new int[matrix.length][matrix[0].length];
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                intMat[i][j] = matrix[i][j] == '1' ? 1 : 0;
            }
        }
        
        int maxLength=0;
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                
                if(i==0 || j==0){
                    // [["0","1"],["1","0"]] we need to return 1. so to update maxLength=1 we need this 
                    maxLength = Math.max(maxLength, intMat[i][j]); // if we have 1 in only top row or first column ans should be 1 
                }
                else if(intMat[i][j]==1){
                    
                    int min = Math.min(intMat[i-1][j], intMat[i][j-1]); // left and up 
                    min = Math.min(min, intMat[i-1][j-1]); // top left 
                    
                    intMat[i][j] = min+1;
                    maxLength = Math.max(maxLength, intMat[i][j]);
                }
            }
        }
         
        return maxLength*maxLength;
     }
                                                
}
