https://leetcode.com/problems/coin-change/submissions/
// [2,4,5] 12
// ans = 3 
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




