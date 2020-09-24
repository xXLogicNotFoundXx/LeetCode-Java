/*
209. Minimum Size Subarray Sum >= S
https://leetcode.com/problems/minimum-size-subarray-sum/
Given an array of n ""positive integers"" and a positive integer s, 
find the minimal length of a contiguous subarray of which the ""sum ≥ s"". If there isn't one, return 0 instead.
11
[1,2,3,4,5]
Ans : 3
Explaination : Subarray [3,4,5] forms 12 sum.

12
[13]
Ans: 1
*/
class Solution {
    // O(N)
    public int minSubArrayLen(int s, int[] nums) {
        if(nums==null||nums.length==0)
            return 0;
        
        int total=0;
        int left=0;
        int minSubArray = Integer.MAX_VALUE; 
        
        for(int i=0;i<nums.length;i++){
            total+=nums[i];
            while(total>=s){
                minSubArray =  Math.min(minSubArray,i-left+1); 
                total-=nums[left];
                left++;
            }
        }
        return minSubArray == Integer.MAX_VALUE ? 0 : minSubArray;
    }
}
