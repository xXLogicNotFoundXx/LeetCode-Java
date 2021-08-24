/*
https://leetcode.com/problems/house-robber/
You are a professional robber planning to rob houses along a street. 
Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security 
system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, 
determine the maximum amount of money you can rob tonight without alerting the police.

Example 1:
Input: [1,2,3,1]
Output: 4
// key point is you can take ith and i-2+ curNumber OR take i-1
*/

class Solution {
    public int rob(int[] nums) {
        
        if(nums==null)
            return 0;
        
        int dp[] = new int[nums.length+2];
        // all dp[] is zero 
        for(int i=0;i<nums.length;i++){
            int dpIndex = i+2;
            int num = nums[i];
            
            dp[dpIndex] = Math.max(num + dp[dpIndex-2], dp[dpIndex-1]);
        }
        
        return dp[dp.length-1];
    }
}
