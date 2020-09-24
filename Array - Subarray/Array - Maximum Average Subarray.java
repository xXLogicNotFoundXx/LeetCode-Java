/*
643. Maximum Average Subarray I
https://leetcode.com/problems/maximum-average-subarray-i/
Given an array consisting of n integers, find the contiguous subarray of given length k that has the maximum average value. 
And you need to output the maximum average value.
Input: [1,12,-5,-6,50,3], k = 4
Output: 12.75
Explanation: Maximum average is (12-5-6+50)/4 = 51/4 = 12.75
*/
class Solution {
    public double findMaxAverage(int[] nums, int k) {
        if(nums==null || k<=0 || k>nums.length)
            return 0;
        
        double ans=-Double.MAX_VALUE, sum=0; // Double.MIN_VALUE is not negative
        int left=0;
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
            if(i-left==k-1){
                ans = Math.max(ans,sum/k);
                sum-=nums[left];
                left++;
            } 
        }
        return ans;
    }
}
