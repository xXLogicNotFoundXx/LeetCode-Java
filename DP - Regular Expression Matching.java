/*
https://leetcode.com/problems/regular-expression-matching/
Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
'.' Matches any single character.
'*' Matches zero or more of the preceding element.
s = "aa"            s = "aab"      
p = "a*"            p = "c*a*b"
Output: true        Output: true    
// using memoization 
//Time  - O(S*P)   - 2 ms
//Space - O(S*P)
*/
class Solution {
    
    public boolean isMatch(String s, String p) {
        // p starting with * or containing ** is not correct pattern so false 
        if(p!=null && !p.isEmpty() && (p.charAt(0)=='*' || p.contains("**")))
            return false; 
        Boolean[][] dp = new Boolean[s.length()+1][p.length()+1];
        
        return isMatchHelper(s,p,dp,0,0);
    }
    
    public Boolean isMatchHelper(String s, String p, Boolean[][] dp, int i,int j){
        
        if(j==p.length())
            return i==s.length() ? true: false;
        
        if(dp[i][j]!=null)
            return dp[i][j];
        
        Boolean ans = new Boolean("false");
        if(j+1 < p.length() && p.charAt(j+1)=='*'){
            // zero match 
            ans = isMatchHelper(s,p,dp,i,j+2);
            if(ans==false){
                // one match or it is false
                if(i < s.length() && (s.charAt(i)==p.charAt(j) || p.charAt(j)=='.'))
                    ans = isMatchHelper(s,p,dp,i+1,j);
            }
            
        } else {
            // one match or it is false
            if(i < s.length() && (s.charAt(i)==p.charAt(j) || p.charAt(j)=='.'))
               ans = isMatchHelper(s,p,dp,i+1,j+1); 
        }
        
        dp[i][j] =  ans;
        return dp[i][j];
    }
}
