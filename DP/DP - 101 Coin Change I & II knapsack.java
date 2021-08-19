/*
https://leetcode.com/problems/coin-change-2/
You are given coins of different denominations and a total amount of money.
Write a function to compute the number of combinations that make up that amount. 
You may assume that you have infinite number of each kind of coin.
Input: amount = 5, coins = [1, 2, 5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1
*/
class Solution {
    /*  It is like Combination Sum problem 
        but in combination sum problem you are asked to return all tuples forming target 
        so we have to use backtracking https://leetcode.com/problems/combination-sum/
        resulting into O(N^T/M)  The maximum depth of the tree could be T/M  ( T is total and M is minimum number)
        Space is O(T/M) thats the height of the tree 
    
    public int change(int amount, int[] coins) {
        // remember coint are not sorted 
        Arrays.sort(coins);
        return calculateSumNumber(coins, amount, 0);
    }
    
    int calculateSumNumber(int[] coins, int amount, int start){ 
        if(amount < 0) 
            return 0;
        
        if(amount == 0){
            return 1;    
        }
        
        int count = 0;
        for(int i=start;i<coins.length;i++){
            if(amount<coins[i])
                break;
            count +=  calculateSumNumber(coins, amount-coins[i], i);
        }
        
        return count;
    }
    */
    
    /* 
        Here we are asked how many combinations are there 
        is there any DP method we could use? 
        
        This is a classic knapsack problem.
        Time complexity:  O(N*amount) where N is a length of coins array.
        Space complexity: O(amount) to keep dp array.
    */
    
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] = dp[i] + dp[i-coin];
            }
        }
        return dp[amount];
    }
    
    
    /*  Another Knapsack version 
        Return the fewest number of coins that you need to make up that amount.
        Time complexity:  O(N*amount) where N is a length of coins array.
        Space complexity: O(amount) to keep dp array.
    */
    
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
