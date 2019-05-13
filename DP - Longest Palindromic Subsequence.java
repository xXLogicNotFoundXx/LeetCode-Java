/*
https://leetcode.com/problems/longest-palindromic-subsequence/
516. Longest Palindromic Subsequence .  
Subsequence mean you can drop some characters in the string 
Given a string s, find the longest palindromic subsequence's length in s. 
You may assume that the maximum length of s is 1000.
Input:"bbbab"
Output:4    bcz we can form palindrom "bbbb".
Input:"bcbd"
Output:2

i=0 and j=n-1 
if the chars are same 
    then call recurse (i+1,j-1)
else
    recurse (i+1,j) OR recurse (i,j-1) ( drop start char OR end char) 

*/
public class Solution {
    public int longestPalindromeSubseq(String s) {
        return helper(s, 0, s.length() - 1, new Integer[s.length()][s.length()]);
    }
    
    private int helper(String s, int i, int j, Integer[][] memo) {
        if (memo[i][j] != null) {
            return memo[i][j];
        }
        if (i > j)      return 0;
        if (i == j)     return 1;
        
        if (s.charAt(i) == s.charAt(j)) {
            memo[i][j] = helper(s, i + 1, j - 1, memo) + 2;
        } else {
            memo[i][j] = Math.max(helper(s, i + 1, j, memo), helper(s, i, j - 1, memo));
        }
        return memo[i][j];
    }
}
