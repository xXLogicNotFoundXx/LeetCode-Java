/*
There is no leetcode problem for this but very common problem. 
Given two strings, find the length of the longest common substring.
abcdf 
bcda
output = 3 

https://www.youtube.com/watch?v=BysNXJHzCEs

  Initial State                Final State
  
     a b c d f                  a b c d f  
   0 0 0 0 0 0                0 0 0 0 0 0 
 b 0                =>      b 0 0 1 0 0 0
 c 0                        c 0 0 0 2 0 0
 d 0                =>      d 0 0 0 0 3 0
 a 0                        a 0 1 0 0 0 0 
*/

class Solution {
    public int longestCommonSubstring(String A, String B) {
        if(A == null || B == null) 
          return 0;
          
        int m = A.size();
        int n = B.size();
        int max = 0;
        
        int[][] dp = new int[m + 1][n + 1];
        for(int i = 0;i <= m;i++){
            for(int j = 0;j <= n;j++){
              
              if(i == 0 || j == 0){
                 dp[i][j] = 0;
                 continue;   
              }
              
              if(A.charAt(i-1) == B.charAt(j-1)){
                  dp[i][j] = 1 + dp[i-1][j-1];
                  max = Math.max(max,dp[i][j]);
              }
            }
        }
        return max;
    }
}
