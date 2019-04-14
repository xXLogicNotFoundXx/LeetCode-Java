https://leetcode.com/problems/house-robber/
You are a professional robber planning to rob houses along a street. 
Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security 
system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, 
determine the maximum amount of money you can rob tonight without alerting the police.

Example 1:
Input: [1,2,3,1]
Output: 4

// start from last and build max path at 0th position and return oth result 
// kind of like climbing stairs ... key point is you can take ith and ith+2 OR skip ith and take i+1
class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        int []buildMax = new int[n+3];  // +3 is very important to understand here 
        Arrays.fill(buildMax,0);
        for(int i=n-1;i>=0;i--){
            buildMax[i] = Math.max(nums[i]+buildMax[i+2],buildMax[i+1]);
        }
        return buildMax[0];
    }
}
