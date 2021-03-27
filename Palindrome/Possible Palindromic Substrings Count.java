/*
https://leetcode.com/problems/palindromic-substrings/

Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".

Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
*/
class Solution {
    /* 
        Calculating substring  that would be N^2 and checking if palindrome will ne N
        That would be N^3 and trivial implementation. 
        
        We can use the same logic finding longest palindromic substring 
        expand(i,i) and expand(i,i+1) and return the count as we match the characters
        that would reduce complexity to N^2
    */
    public int countSubstrings(String s) {
        
        if(s==null || s.isEmpty())
            return 0; 
        
        int ans = 0;
        for(int i=0;i<s.length();i++){
            ans += expand(s,i,i);   // expand i as mid and odd chars 
            ans += expand(s,i,i+1); // expand as even chars 
        }
        return ans;
    }
    
    int expand(String s, int i, int j){
        int count=0;
        while(i>=0 && j<s.length() && s.charAt(i) == s.charAt(j)){
            count++;
            i--;j++;
        }
        
        return count; 
    }
}
