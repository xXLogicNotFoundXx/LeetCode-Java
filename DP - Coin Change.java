/*
https://leetcode.com/problems/coin-change/submissions/
You are given coins of different denominations and a total amount of money amount.
Write a function to compute the fewest number of coins that you need to make up that amount.
If that amount of money cannot be made up by any combination of the coins, return -1.
Input: coins = [1, 2, 5], amount = 11
Output: 3 
Explanation: 11 = 5 + 5 + 1
*/
class Solution {
    public int coinChange(int[] coins, int amount) {
        if(coins==null || coins.length==0)
            return -1;
        int []dp = new int[amount+1];
        Arrays.fill(dp,-1);
        dp[0]=0;
        for(int coin : coins){
            for(int i=coin;i<=amount;i++){
                if(dp[i-coin]!=-1){
                    int coinNum = dp[i-coin]+1;
                    dp[i] = dp[i]==-1 ? coinNum : Math.min(dp[i],coinNum);
                } 
            }
        }
        return dp[amount];
    }
}




