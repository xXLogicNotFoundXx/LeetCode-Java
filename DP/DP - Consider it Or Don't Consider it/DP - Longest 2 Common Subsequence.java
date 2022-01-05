/*
Medium
Amazon6 Google4 Adobe3 Karat3 ... some more in last 2 years

Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.
Input: text1 = "abcde", text2 = "ace"
Output: 3
Explanation: The longest common subsequence is "ace" and its length is 3.


This is like Longest common SUBSTRING .. but it is not a Substring ... it is subsequence
Characters are not lined up next to each other.

For SUBSTRING:
  Initial State                Final State

     a b c d f                  a b c d f
   0 0 0 0 0 0                0 0 0 0 0 0
 b 0                =>      b 0 0 1 0 0 0
 c 0                        c 0 0 0 2 0 0
 d 0                =>      d 0 0 0 0 3 0
 a 0                        a 0 1 0 0 0 0

How can we modify Longest common SUBSTRING DP to have longest common subsequece?
We can see that somehow we have to carry forward left counter to the right ...
we dont want to lose previously matched characters.

Initial State                Final State

   a c e                       a c e
  0 0 0 0 0 0                0 0 0 0
a 0                =>      a 0 1 1 1
b 0                        b 0 1 1 1
c 0                =>      c 0 0 2 1
d 0                        d 0 0 2 2
e 0                        e 0 0 0 3

so the equation becomes
        if(matching)
          dp[i][j] = dp[i-1]dp[j-1] + 1;            // same as finding SUBSTRING
        else                 // TOP        // left
          dp[i][j] = Math.max(dp[i-1]dp[j], dp[i]dp[j-1]);      // Continue the previously found matching characters
                                                                // Dont discard the numbers

Time  - O(n^2)
Space - O(n^2)

*/
class Solution {
    public int longestCommonSubsequence1(String text1, String text2) {

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
    //  we only ever looked at the current row and the previous row.
    //  we can optimize the space to O(n).
}
