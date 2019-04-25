/*
https://leetcode.com/problems/minimum-size-subarray-sum/
Given an array of n ""positive integers"" and a positive integer s, 
find the minimal length of a contiguous subarray of which the ""sum ≥ s"". If there isn't one, return 0 instead.
11
[1,2,3,4,5]
Ans : 3
*/
class Solution {
    public int minSubArrayLen(int sum, int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        
        int total=0, min=nums.length+1;
        int left=0, right=0;
        while(right<nums.length){
            total += nums[right++]; 
            
            while(left<right && total>=sum){
                min = Math.min(min,right-left);
                total -= nums[left++];
            }
        }
        return min > nums.length ? 0 : min;
    }
}
