/*
 https://leetcode.com/problems/one-edit-distance/
 Given two strings s and t, determine if they are both one edit distance apart.
    Note: 
    There are 3 possiblities to satisify one edit distance apart:
    Insert a character into s to get t
    Delete a character from s to get t
    Replace a character of s to get t
 
 If both are equal we shuould return true/false?
 Do we need insert operation?
    No, we can just take longer string and use delete instead of insert on smaller string.
*/
class Solution {
    public boolean isOneEditDistance(String s, String t) {
        if(s==null || t==null || s.equals(t)) 
            return false;
        
        if(s.length()==t.length()) 
            return isOneReplaceApart(s,t);
        
        if(s.length()>t.length()) 
            return isOneDeleteApart(s,t);
        
        return isOneDeleteApart(t,s);
    }
    
    boolean isOneDeleteApart(String s, String t){
        // s is bigger string 
        for(int i=0;i<t.length();i++){
            if(s.charAt(i)!=t.charAt(i)){
                return s.substring(i+1).equals(t.substring(i));
            }
        }
        //all charrs matched now the sie difference should be one 
        return (s.length()-t.length() == 1);
    }
    
    boolean isOneReplaceApart(String s, String t){
        // s and t are same length
        for(int i=0;i<t.length()-1;i++){
            if(s.charAt(i)!=t.charAt(i)){
                return s.substring(i+1).equals(t.substring(i+1));
            }
        }
        return true;
    }
    
}
