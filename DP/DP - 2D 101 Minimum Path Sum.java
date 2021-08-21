/*
https://leetcode.com/problems/minimum-path-sum/
64. Minimum Path Sum
Medium
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right 
which minimizes the sum of all numbers along its path.
Note: You can only move either down or right at any point in time.

Example:

Input:

  [1,3,1],
  [1,5,1],
  [4,2,1]

Output: 7
Explanation: Because the path 1→3→1→1→1 minimizes the sum.


Approach 1: Brute Force
The Brute Force approach involves recursion. For each element, we consider two paths, rightwards and downwards
and find the minimum sum out of those two. It specifies whether we need to take a right step or downward 
step to minimize the sum.

Time complexity : O(2^m+n) For every move, we have atmost 2 options.
Space complexity : O(m+n). Recursion of depth m+n. 

Approach 2: DP/ Memoizaton 
Time complexity : O(m+n)
Space complexity : O(m+n)
*/
// My solution - TOP-DOWN
class Solution {
    public int minPathSum(int[][] grid) {
        if(grid==null || grid.length==0)
            return 0;
        
        int m = grid.length;
        int n = grid[0].length; 
        int[][] dp = new int[m][n];
        
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                
                if(i==0 && j==0){ // special case 
                    dp[i][j] = grid[i][j];
                    continue;
                }
                
                // all other cases 
                int top  = Integer.MAX_VALUE;
                int left = Integer.MAX_VALUE;
                
                if(i!=0)
                    top = grid[i][j]+dp[i-1][j];
                if(j!=0)
                    left = grid[i][j]+dp[i][j-1];
                
                dp[i][j] = Math.min(top,left);
            }
        }
        return dp[m-1][n-1];
    }
}

// DOWN UP recursion
class Solution {
    
    public int minPathSum(int[][] grid) {
        int [][] dp = new int[grid.length][grid[0].length];
        return minPathSum(grid,0,0,dp);
    }
    
    int minPathSum(int[][] grid, int i, int j, int [][] dp){
        if(i==grid.length-1 && j==grid[0].length-1)
            return grid[i][j]; // bottom right
        
        if(dp[i][j]!=0)
            return dp[i][j];
        
        int down = Integer.MAX_VALUE;
        int right = Integer.MAX_VALUE;
        
        if(i<grid.length-1)
            down  =  minPathSum(grid,i+1,j,dp) + grid[i][j];
        if(j<grid[0].length-1)
            right =  minPathSum(grid,i,j+1,dp) + grid[i][j];
        
        dp[i][j] = Math.min(down,right); // value
        return dp[i][j];
    }
}
