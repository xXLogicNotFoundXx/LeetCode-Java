/*
https://leetcode.com/explore/featured/card/top-interview-questions-easy/127/strings/883/
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
Note: For the purpose of this problem, we define empty string as valid palindrome.
Example 1:
Input: "A man, a plan, a canal: Panama"
Output: true
Example 2:
Input: "race a car"
Output: false
*/
class Solution {
    public boolean isPalindrome(String s) {
        if(s==null) return true;
        int i=0, j=s.length()-1;
        s = s.toLowerCase();
        while(i<j){
            while(!isDigitOrChar(s.charAt(i)) && i<j) i++;
            while(!isDigitOrChar(s.charAt(j)) && i<j) j--;
            if(s.charAt(i) != s.charAt(j)){
                return false;
            }
            i++;j--;   
        }
        return true;
    }
    boolean isDigitOrChar(char c){
        if( (c >= 'a' && c <='z') ||
            (c >= '0' && c <='9'))
            return true;
        return false;
    }
}
