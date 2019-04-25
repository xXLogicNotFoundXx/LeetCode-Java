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
