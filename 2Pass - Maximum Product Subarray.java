/*
https://leetcode.com/problems/maximum-product-subarray/
Given an integer array nums, find the contiguous subarray within an array 
(containing at least one number) which has the largest product.
Input: [2,3,-2,4]
Output: 6
Input: [-2,0,-1]
Output: 0
*/
class Solution {
     public int maxProduct(int[] nums) {
        int prod = 1;
        int result = Integer.MIN_VALUE;
        for(int i = 0; i < nums.length; i++) {
            prod = prod * nums[i];
            result = Math.max(prod, result);
            if(prod == 0) 
                prod = 1;
        }
        prod = 1;
        // we need second pass from right to left for input like [3,-1,4]
        for(int i = nums.length - 1; i >= 0; i--) { 
            prod = prod * nums[i];
            result = Math.max(prod, result);
            if(prod == 0) 
                prod = 1;       
        }
        return result;
    }
}
