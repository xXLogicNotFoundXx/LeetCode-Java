/*
Medium - not that many
https://leetcode.com/problems/perfect-squares/
Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
Example 1:
Input: n = 12
Output: 3
Explanation: 12 = 4 + 4 + 4.

Example 2:
Input: n = 13
Output: 2
Explanation: 13 = 4 + 9.
*/
class Solution {
    // This is exactly same as coin change problem. (We have to build data set (which will be treated as coins)
    public int numSquares(int n) {
        if(n<=0) return 0;

        // Build all perfect square numbers we need to consider
        int nums[] = new int[(int) Math.sqrt(n) +1];
        for(int i=1;i<=nums.length;i++) {
            nums[i-1] = i*i;
            if(nums[i-1] == n)
                return 1;
        }

        int []dp= new int[n+1];
        dp[0] = 0;
        for(int coin : nums){

            for(int i=coin; i<=n; i++){

                int totalCoins = dp[i-coin] + 1;
                if(dp[i]==0)
                    dp[i] = totalCoins;
                else
                    dp[i] = Math.min(dp[i], totalCoins);
            }
        }
        return dp[n];
    }
}
