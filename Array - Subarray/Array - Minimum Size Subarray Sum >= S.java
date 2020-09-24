/*
https://leetcode.com/problems/minimum-size-subarray-sum/
Given an array of n ""positive integers"" and a positive integer s, 
find the minimal length of a contiguous subarray of which the ""sum ≥ s"". If there isn't one, return 0 instead.
11
[1,2,3,4,5]
Ans : 3
*/
class Solution {
    // My Solution O(N)
    /*
    We could keep 2 pointer,one for the start and another 
    for the current subarray, 
    and make optimal moves so as to keep the sum greater than s 
    and maintain the lowest size possible.
    */
    public int minSubArrayLen1(int s, int[] nums) {
        
        if(nums==null||nums.length==0)
            return 0;
        
        int total = nums[0]; // what if num[0] >=s
        int minSubArray = total>=s ? 1 : Integer.MAX_VALUE; 
        int left=0;
        for(int i=1;i<nums.length;i++){
            
            total+=nums[i];
            
            if(total>=s && left<=i){
                minSubArray = Math.min(minSubArray,i-left+1); // +1 bcz we added nums[i] already
                
                total-=nums[left];
                left++;
                
                // we want left to keep moving forward & find min index diff 
                total-=nums[i]; 
                i--;
            }
        }
        
        return minSubArray == Integer.MAX_VALUE ? 0 : minSubArray;
    }
    
    // another O(N)
    public int minSubArrayLen(int s, int[] nums) {
        if(nums==null||nums.length==0)
            return 0;
        
        int total=0;
        int left=0;
        int minSubArray = Integer.MAX_VALUE; 
        
        for(int i=0;i<nums.length;i++){
            total+=nums[i];
            while(total>=s){
                minSubArray =  Math.min(minSubArray,i-left+1); // +1 bcz we added nums[i] already
                total-=nums[left];
                left++;
            }
        }
        return minSubArray == Integer.MAX_VALUE ? 0 : minSubArray;
    }
}
