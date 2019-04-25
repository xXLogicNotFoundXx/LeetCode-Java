/*
https://leetcode.com/problems/regular-expression-matching/
Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
'.' Matches any single character.
'*' Matches zero or more of the preceding element.

s = "aa"            s = "aab"      
p = "a*"            p = "c*a*b"
Output: true        Output: true    

Time  - O((S+P)*2^(S+P/2))
Space - O(S^2 + P^2)
Let this solution sink in 
*/
class Solution {
    public boolean isMatch(String s, String p) {
        // p starting with * or containing ** is not correct pattern so false 
        if(p!=null && !p.isEmpty() && (p.charAt(0)=='*' || p.contains("**")))
            return false; 
        return isMatchHelper(s,p);
    }
    public boolean isMatchHelper(String s, String p){
        if(p.isEmpty())
            return s.isEmpty();
        
        if (p.length() > 1 && p.charAt(1) == '*'){
            
            if(isMatchHelper(s,p.substring(2))) // zero match 
                return true; 
            
             if( s.length() > 0 && (s.charAt(0)==p.charAt(0) || p.charAt(0)=='.') ){ // one match
                 return isMatchHelper(s.substring(1),p);
             }
            
             return false;
        } else {
            
            if( s.length() > 0 && (s.charAt(0)==p.charAt(0) || p.charAt(0)=='.')){ // has to match one 
                return isMatchHelper(s.substring(1),p.substring(1));
            }
            return false;
        }
    }
}

// using memoization 
//Time  - O(S*P)   - 2 ms
//Space - O(S*P)
class Solution {
    
    enum Result{TRUE,FALSE}
    
    public boolean isMatch(String s, String p) {
        // p starting with * or containing ** is not correct pattern so false 
        if(p!=null && !p.isEmpty() && (p.charAt(0)=='*' || p.contains("**")))
            return false; 
        Result[][] dp = new Result[s.length()+1][p.length()+1];
        
        return isMatchHelper(s,p,dp,0,0) == Result.TRUE ? true : false;
    }
    
    public Result isMatchHelper(String s, String p, Result[][] dp, int i,int j){
        
        if(j==p.length()){
            return i==s.length() ? Result.TRUE : Result.FALSE;
        }
        
        if(dp[i][j]!=null){
            return dp[i][j];
        }
        
        Result ans;
        if(j+1 < p.length() && p.charAt(j+1)=='*'){
            // zero match 
            ans = isMatchHelper(s,p,dp,i,j+2);
            if(ans==Result.FALSE){
                // one match
                if(i < s.length() && (s.charAt(i)==p.charAt(j) || p.charAt(j)=='.')){
                    ans = isMatchHelper(s,p,dp,i+1,j);
                } else {
                    ans = Result.FALSE;  
                }
            }
            
        } else {
            if(i < s.length() && (s.charAt(i)==p.charAt(j) || p.charAt(j)=='.')){
               ans = isMatchHelper(s,p,dp,i+1,j+1); 
            } else {
               ans = Result.FALSE;
            }
        }
        
        dp[i][j] =  ans;
        return dp[i][j];
    }
}
