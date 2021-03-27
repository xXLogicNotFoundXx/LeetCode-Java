/*
HARD
https://leetcode.com/submissions/detail/472933466/

Given a string s. In one step you can insert any character at any index of the string.
Return the minimum number of steps to make s palindrome.

Input: s = "zzazz"
Output: 0

Input: s = "mbadm"
Output: 2
Explanation: String can be "mbdadbm" or "mdbabdm".

Input: s = "leetcode"
Output: 5
Explanation: Inserting 5 characters the string becomes "leetcodocteel".

*/
class Solution {
    /*
    It is really fucked up that the ask is for insertion. 
    If you dont know how to tackle the insersion then you are toast. Here we are asked for count 
    So we dont have to give the palindromic string in the end.
    
    Subtle detail that you can conver the problem to longest palindromic subsequence varient. 
    
    This is like longest common subsequece in two strings.  
    Very close to finding longest palindromic subsequece. start from 0 and n-1 
        When missmatch happend we count that insertion and eithe i++ or j-- and we take the min.
        
    Another way to look at it :
    Subtle detail is that if you can find the longest palindromic subsequence then: 
        If we know the longest palindromic sub-sequence is x and the length of the string is n then, what is the answer to this problem? 
        It is n - x as we need n - x insertions to make the remaining characters also palindrome.
    */
    // Time: N^2  Space :N^2
    public int minInsertions1(String s) {
        
        if(s==null || s.isEmpty())
            return 0;
        
        Integer[][] memo = new Integer[s.length()][s.length()];
        
        return minInsertionsHelper(s, 0, s.length()-1, memo);
    }
    
    Integer minInsertionsHelper(String s, int i, int j, Integer[][] memo){
        if(i>j || i==j)
            return 0;
        
        if(memo[i][j]!=null)
            return memo[i][j];
        
        if(s.charAt(i)==s.charAt(j)){
            memo[i][j] = minInsertionsHelper(s, i+1, j-1, memo);
        } else {
            memo[i][j] = Math.min( minInsertionsHelper(s, i+1, j, memo),        /* imagine insert at j of charAt(i) */
                                   minInsertionsHelper(s, i, j-1, memo)) + 1 ;  /* imagine insert at i of charAt(j) */
        }
        
        return memo[i][j];
    }
    
    
    /*
        Another way to find longest palindromic subsequence and take the diff with N 
        Time: N^2  Space :N^2
    */
    public int minInsertions(String s) {
        
        if(s==null || s.isEmpty())
            return 0;
        
        Integer[][] memo = new Integer[s.length()][s.length()];
        
        return s.length() - longestPalindromicSubsequence(s, 0, s.length()-1, memo);
    }
    
    int longestPalindromicSubsequence(String s, int i, int j, Integer[][] memo){
        if(i>j)
            return 0;
        
        if(i==j)
            return 1;
        
        if(memo[i][j]!=null)
            return memo[i][j];
        
        if(s.charAt(i)==s.charAt(j)){
            memo[i][j] = longestPalindromicSubsequence(s, i+1, j-1, memo) + 2;
        } else {
            memo[i][j] = Math.max( longestPalindromicSubsequence(s, i+1, j, memo),
                                   longestPalindromicSubsequence(s, i, j-1, memo));  
        }
        
        return memo[i][j];
    }
}
