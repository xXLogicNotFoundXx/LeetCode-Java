/*
https://leetcode.com/problems/wildcard-matching/
'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
s = "aa"        s = "aa"        s = "adceb"
p = "*"         p = "a"         p = "*a*b"
Output: true    Output: false   Output: true

Time  - O(S*P)  
Space - O(S*P) 
if P char is '*' 
   we can just match zero Or just one char from S and do recursive calls (so 2 recursive calls)
else
   P char has to match with S or P char has to be '?' (just one recursive call)

*/
class Solution { 
    enum Result{TRUE,FALSE}
    
    public boolean isMatch(String s, String p) {
        Result[][] dp = new Result[s.length()+1][p.length()+1];
        return isMatchHelper(s,p,dp,0,0) == Result.TRUE ? true : false;
    }
    
    Result isMatchHelper(String s, String p,Result[][] dp,int i,int j){
        
        if(j==p.length())
            return i==s.length() ? Result.TRUE : Result.FALSE;
        
        if(dp[i][j]!=null)
            return  dp[i][j];
        
        Result ans; 
        if(p.charAt(j)=='*'){
            ans = isMatchHelper(s,p,dp,i,j+1); // consider zero match   
            if(ans == Result.FALSE){ 
                // consider one match
                if(i < s.length())
                    ans = isMatchHelper(s,p,dp,i+1,j); // one match
                else 
                    ans = Result.FALSE;
            }
        }else{
            // one match else it is false
            if(i < s.length() && (s.charAt(i)==p.charAt(j) || p.charAt(j)=='?'))  
                ans = isMatchHelper(s,p,dp,i+1,j+1);
            else
                ans = Result.FALSE;
        }
        
        dp[i][j] = ans;
        return dp[i][j];
    }
}
