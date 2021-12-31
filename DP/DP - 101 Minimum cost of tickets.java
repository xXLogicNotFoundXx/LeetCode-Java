/*
Medium - okiesh
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

FB12 Apple3
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
