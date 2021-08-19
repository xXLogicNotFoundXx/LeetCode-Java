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
