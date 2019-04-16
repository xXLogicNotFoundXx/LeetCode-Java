/*
https://leetcode.com/problems/palindromic-substrings/
Given a string, your task is to count how many palindromic substrings in this string.
Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".

Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".

    
Man with N^2 you can just take every index and expand towards both side (considering even and odd palindrom)
this is same algorithm we use to find Largest palindrom substring in a given string.
 4ms
*/
class Solution {
    public int countSubstrings(String s) {
        int result = 0;
        for(int i=0;i<s.length();i++){
            result += expand(s, i, i) + expand(s, i, i+1);
        }
        return result;
    }
    
    private int expand(String s, int i, int j){
        if(i<0 || j<0 || i>=s.length() || j>=s.length()) return 0;
        int count = 0;
        while(i>=0 && j<=s.length()-1){
            if(s.charAt(i--)==s.charAt(j++))
                count++;
            else
                break;
        }
        return count;
    }
}
