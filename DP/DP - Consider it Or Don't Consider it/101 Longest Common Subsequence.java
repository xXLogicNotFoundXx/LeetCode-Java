
/*
https://leetcode.com/problems/longest-common-subsequence/


This is almost the same problem as Longest Common Substring OR Longest Common Subarray.
But in this problem it is asking for the subsequence.... that mean you can jump from one char to another char

SUBSEQUENCE - not a substring/subarray

Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.
A subsequence of a string is a new string generated from the original string with some characters (can be none)
deleted without changing the relative order of the remaining characters.

Input: text1 = "abcde", text2 = "ace"
Output: 3

Input: text1 = "abc", text2 = "def"
Output: 0
*/

// Memoization
class Solution {
    // without memoization it would be 2^N. where is Max(length1,length2)
    // for every call we would do max two recursive calls and depth of the tree would be Max(length1,length2)
    // With memo
    public int longestCommonSubsequence(String text1, String text2) {
        Integer[][] memo = new Integer[text1.length()][text2.length()];
        return longestCommonSubsequenceHelper(text1, 0, text2, 0, memo);
    }

    int longestCommonSubsequenceHelper(String text1, int i, String text2,  int j, Integer[][] memo){
        if(i==text1.length() || j==text2.length())
            return 0;

        if(memo[i][j]!=null)
            return memo[i][j];

        if(text1.charAt(i) == text2.charAt(j)){
            memo[i][j] = longestCommonSubsequenceHelper(text1, i+1, text2, j+1, memo) + 1;
        } else {
            memo[i][j] =  Math.max( longestCommonSubsequenceHelper(text1, i+1, text2, j, memo),
                                    longestCommonSubsequenceHelper(text1, i, text2, j+1, memo));
        }

        return memo[i][j];
    }
}

// refer https://leetcode.com/problems/longest-common-subsequence/discuss/351689/JavaPython-3-Two-DP-codes-of-O(mn)-and-O(min(m-n))-spaces-w-picture-and-analysis
// DP array 
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {

        if(text1==null || text2==null)
            return 0;

        int[][] dp = new int[text1.length() + 1][text2.length() + 1];


        for(int i=0; i<text1.length(); i++){
            for(int j=0; j<text2.length(); j++){

                if(text1.charAt(i) == text2.charAt(j)){
                    dp[i+1][j+1] = dp[i][j] + 1;
                } else {
                    dp[i+1][j+1] = Math.max(dp[i][j+1],dp[i+1][j]);
                }
            }
        }

        return dp[text1.length()][text2.length()];
    }
}
