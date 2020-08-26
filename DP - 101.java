/* https://leetcode.com/problems/climbing-stairs/solution/
You are climbing a stair case. It takes n steps to reach to the top.
Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 */
class Solution {
    // left -> right 
    public int climbStairs(int n) {
        if(n==0) 
            return 0;
        int[] dp = new int[n+1];
        dp[0]=dp[1]=1;
        for(int i=2;i<n+1;i++){
            dp[i] = dp[i-1]+ dp[i-2];
        }
        return dp[n];
    }
  
   // right -> left 
    public int climbStairs(int n) {
        if(n==0)
            return 0;
        int []a = new int[n+1];
        Arrays.fill(a,0);
        a[n]=1;
        a[n-1]=1;
        for(int i=n-2;i>=0;i--){
            a[i] = a[i+1] + a[i+2]; // different ways of climbing ith step
        }
        return a[0]; 
    }   
}

/*
https://leetcode.com/problems/min-cost-climbing-stairs/
Your goal is to reach endofthearray+1 with min cost. 
1st and 2nd cost is not pais until you jump from that position. 
Input: cost = [10, 15, 20]                  
Output: 15
Explanation: Cheapest is start on cost[1], pay that cost and go to the top.
*/
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        if(cost==null)
            return 0;
        int c =0;
        for(int i=2;i<=cost.length;i++){
            c = Math.min(cost[i-1],cost[i-2]);
            if(i!=cost.length)
                cost[i] += c;
        }
        return c;
    }
}

  
/*
https://leetcode.com/problems/coin-change
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


/*
https://leetcode.com/problems/minimum-cost-for-tickets/
The days of the year that you will travel is given as an array days.  Each day is an integer from 1 to 365.
Train tickets are sold in 3 different ways:
 1-day pass is sold for costs[0] dollars;
 7-day pass is sold for costs[1] dollars;
 30-day pass is sold for costs[2] dollars.
The passes allow that many days of consecutive travel.  
For example, if we get a 7-day pass on day 2, then we can travel for 7 days: day 2, 3, 4, 5, 6, 7, and 8.
Return the minimum number of dollars you need to travel every day in the given list of days.

Input: days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
Output: 17
Explanation: 
For example, here is one way to buy passes that lets you travel your travel plan:
On day 1, you bought a 30-day pass for costs[2] = $15 which covered days 1, 2, ..., 30.
On day 31, you bought a 1-day pass for costs[0] = $2 which covered day 31.
*/
class Solution {
    public int mincostTickets(int[] days, int[] costs) {
        int maxDay = days[days.length-1];
        int []dp = new int[maxDay+1];
        
        Set<Integer> set = new HashSet<>();
        for(int i : days)
            set.add(i);
        
        for(int i=1;i<=maxDay;i++){
            if(set.contains(i)){
                dp[i] = dp[Math.max(i-1,0)] + costs[0]; // -1 day cost  + day pass 
                dp[i] = Math.min(dp[Math.max(i-7,0)] + costs[1], dp[i]); // -7 day cost + 7 day pass
                dp[i] = Math.min(dp[Math.max(i-30,0)] + costs[2], dp[i]); // -30 day cost + 30 pass 
            } else{
                dp[i] = dp[i-1]; // no travel no cost
            }
        }
        return dp[maxDay];
    }
}
