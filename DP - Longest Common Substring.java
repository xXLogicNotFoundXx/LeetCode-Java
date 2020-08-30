/*
There is no leetcode problem for this but very common problem. 
Given two strings, find the length of the longest common substring.
abcdf 
bcda
output = 3 

https://www.youtube.com/watch?v=BysNXJHzCEs
*/

class Solution {
    public int longestCommonSubstring(String A, String B) {
        if(A == null||B == null) 
          return 0;
          
        int m = A.size();
        int n = B.size();
        int max = 0;
        
        int[][] dp = new int[m + 1][n + 1];
        for(int i = 1;i <= m;i++){
            for(int j = 1;j <= n;j++){
              if(A.charAt(i-1) == B.charAt(j-1)){
                  dp[i][j] = 1 + dp[i-1][j-1];
                  max = Math.max(max,dp[i][j]);
              }
            }
        }
        return max;
    }
}

