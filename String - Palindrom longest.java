https://leetcode.com/problems/longest-palindromic-substring/
Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
// This is N^2 solution but even with DP there is no better solution 
// there is O(n) by Manacher's Algorithm but no one expects that 

public class Solution {
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2)
            return s;
        
        int []info = new int[2]; // stores start of the palindrom and length of it 
        for (int i = 0; i < len-1; i++) {
            extendPalindrome(s, i, i, info);  //assume odd length, try to extend Palindrome both sides
            extendPalindrome(s, i, i+1, info); //assume even length, try to extend Palindrome both sides
        }
        return s.substring(info[0], info[0] + info[1]);
    }

    private void extendPalindrome(String s, int start, int end, int []info) {
        while (start >= 0 && end < s.length() && 
               s.charAt(start) == s.charAt(end)) {
            start--;
            end++;
        }
        if (info[1] < end - start - 1) {
            info[0] = start + 1;
            info[1] = end - start - 1;
        }
    }
}
