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
/* 
 You can start with i=0 and j=n-1 index 
 if they match you do i++ and j-- 
 else you have two option 
 either you do i++ and make recursive call 
 OR you do j-- and make recusrive call
 and take the max of of those both options. 

without memoization this would have been 2^N recursive calls because 
each call makes max 2 recursive calls and MAX depth of the tree would be N.

With memoization we solve each substring problem which is N^2. 
*/

public class Solution {
    
    // why this works? but below doesnt work?
    /*
        I learned here be careful of passing the count down the recursive call.
        We have to add the count when we come back up from the recursive call. 
        We also use same technique for calculating height of the the tree..        
    */
    public int longestPalindromeSubseq1(String s) {
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
    
    
    /*
    ----> VIMP ... let this sink in. 
    
    This is what usually i would have done ...
    this doesnt work for "ababac" it returns 3 but should be 5. 
    
     WRONG way to do it. 
     Passing down the count in recursive call wont work. 
     
     why?
     the thing is, you can find a subanswer ... from different path and will record that as an aswer...
     in this example .... "ababac"
     we have a recursive call i++ first so it will iterate for all i values first, remember the count is still 0 
     then it will backtrack and eventually will land up on i=1 & j=3 bab and will find the palindrome ... so -> memo[1][3] =3
     now, on the very first call i=0 we will start making j-- 
     "ababac"
     i=0,j=4  so a is matched 
     will pass count as 2 and 
     then now  i=1 and j=3 -> this will return 3 ... bcz we previously calculated this and memo will return this ans.
     thus, our answer will be 3 and the count that we passed is lost. 
     
     Now, you may think that may be we should add count to the result .. 
     but remember when we calculated i=1 & j=3 the count was zero anyway. 
      
    */
    public int longestPalindromeSubseq(String s) {
        
        Integer[][] memo = new Integer[s.length()][s.length()];
        
        return longestPalindromeSubseqHelper(s, 0, s.length() - 1, memo, 0);
    }
    
    private Integer longestPalindromeSubseqHelper(String s, int i, int j, Integer[][] memo, int count) {
        
        if (i > j)    // this is for even number of chars   
            return count;
        
        if (i == j)  // this is for odd number of chars 
            return count+1;
        
        if (memo[i][j] != null)
            return memo[i][j];
              
        
        if (s.charAt(i) == s.charAt(j)) {
            memo[i][j] = longestPalindromeSubseqHelper(s, i+1, j-1, memo, count+2);
        } else {
            memo[i][j] = Math.max(longestPalindromeSubseqHelper(s, i+1, j,   memo, count), 
                                  longestPalindromeSubseqHelper(s, i,   j-1, memo, count));
        }
        
        return memo[i][j];
    }  
}
