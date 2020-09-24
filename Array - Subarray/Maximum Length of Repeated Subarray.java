/*
https://leetcode.com/problems/maximum-length-of-repeated-subarray/
Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.
Example 1:
Input:
A: [1,2,3,2,1]
B: [3,2,1,4,7]
Output: 3
Explanation: 
The repeated subarray with maximum length is [3, 2, 1].

*/
class Solution {
    // TLE 
    // Time & space O(M*N*min(M,N))  O(M)
    public int findLength1(int[] A, int[] B) {
        
        HashMap<Integer,List<Integer>> map = new HashMap<>();
        for(int i=0;i<A.length;i++){
            List<Integer> indexes = map.getOrDefault(A[i], new ArrayList<Integer>());
            indexes.add(i);
            map.put(A[i],indexes);
        }
        
        int maxLen = 0;
        for(int i=0;i<B.length;i++){
            if(map.containsKey(B[i])){
                
                List<Integer> indexes = map.get(B[i]);
                for(int j : indexes){
                    int count=0;
                    int k=i;
                    
                    while(k<B.length && j<A.length && A[j]==B[k]){
                        count++;
                        k++;j++;
                    }
                    
                    maxLen=Math.max(maxLen,count);
                }
            }
        }
        
        return maxLen;
    }
    
 /* DP
 The idea same as DP - Longest Common Substring - (See the details below) 
 Time & space O(M*N))  O(M*N)
 
      Initial State                Final State
  
             a b c d f                  a b c d f  
           0 0 0 0 0 0                0 0 0 0 0 0 
         b 0                =>      b 0 0 1 0 0 0
         c 0                        c 0 0 0 2 0 0
         d 0                =>      d 0 0 0 0 3 0
         a 0                        a 0 1 0 0 0 0 
 */
    public int findLength(int[] A, int[] B) {
        
        if(A==null || B==null)
            return 0;
        
        int m=A.length, n =B.length;
        int[][] dp = new int[m+1][n+1];
        
        int maxLen = 0;
        
        for(int i=0;i<=m;i++){
            for(int j=0;j<=n;j++){
                
                if(i==0 || j==0){
                    dp[i][j] =0;
                    continue;
                }
                
                if(A[i-1]==B[j-1]){
                    dp[i][j]=dp[i-1][j-1]+1;
                    maxLen=Math.max(maxLen,dp[i][j]);
                }
            }
        }
        
        return maxLen;
    }
}


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
