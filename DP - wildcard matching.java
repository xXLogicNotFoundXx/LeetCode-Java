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
    public boolean isMatch(String s, String p) {
        Boolean[][] dp = new Boolean[s.length()+1][p.length()+1];
        return isMatchHelper(s,p,dp,0,0).booleanValue();
    }
    
    Boolean isMatchHelper(String s, String p,Boolean[][] dp,int i,int j){
        
        if(j==p.length())
            return i==s.length() ? new Boolean("true") : new Boolean("false");
        
        if(dp[i][j]!=null)
            return  dp[i][j];
        
        Boolean ans = new Boolean("false"); 
        if(p.charAt(j)=='*'){
            ans = isMatchHelper(s,p,dp,i,j+1); // consider zero match   
            if(ans == false){ 
                // consider one match 
                if(i < s.length())
                    ans = isMatchHelper(s,p,dp,i+1,j); 
            }
        }else{
            // one match else it is false anyway
            if(i < s.length() && (s.charAt(i)==p.charAt(j) || p.charAt(j)=='?'))  
                ans = isMatchHelper(s,p,dp,i+1,j+1);
        }
        
        dp[i][j] = ans;
        return dp[i][j];
    }
}
