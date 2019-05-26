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
    // very simeple everytime you find 1 expand and note down the max ... messed up lot of indexes 
    // though looks simple but be careful about manipulating indexes 
    // (mn)^2 
    public int maximalSquare1(char[][] matrix) {
        if(matrix==null || matrix.length ==0 || matrix[0].length ==0 )
            return 0;
        int rows = matrix.length;
        int cols = matrix[0].length; 
        int maxLength = 0;
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                if(matrix[i][j]=='1'){
                    int length = 1;
                    boolean flag = true;
                    while(flag && i+length<rows && j+length<cols){
                        // check all colums of a last row 
                        for(int k = j; k <= j+length; k++){ // IMP index calculation 
                            if(matrix[i+length][k]!='1'){
                                flag=false;
                                break;
                            }
                        }
                        // check all row of a last column 
                        for(int k = i; k <= i+length; k++){ // IMP index calculation 
                            if(matrix[k][j+length]!='1'){
                                flag=false;
                                break;
                            }
                        }
                        
                        if(flag)
                            length++;
                    }
                    maxLength = Math.max(maxLength,length);    
                }
            }
        }
        return maxLength*maxLength;
    }
    
    
    
    // DP APPROACH 
    // it same as edit distance problem.
    // dp[i][j] = Math.min(Math.min([i-1][j],[i][j-1]), [i-1][j-1]) 
    // think about this and let that sink in.
    public int maximalSquare(char[][] matrix) {
        if(matrix==null || matrix.length ==0 || matrix[0].length ==0 )
            return 0;
        int [][]dp = new int[matrix.length+1][matrix[0].length+1];
        int maxLength = 0;
        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                if(matrix[i-1][j-1]=='1'){
                    dp[i][j] = Math.min(Math.min(dp[i-1][j],dp[i][j-1]), dp[i-1][j-1])+1;
                    maxLength = Math.max(maxLength, dp[i][j]);
                }
            }
        }
        return maxLength*maxLength; 
    }
}
