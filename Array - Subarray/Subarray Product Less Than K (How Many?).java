/*
713. Subarray Product Less Than K
https://leetcode.com/problems/subarray-product-less-than-k/
Your are given an array of positive integers nums.
Count and print the number of (contiguous) subarrays where the product of all the elements in the subarray is less than k.
Input: nums = [10, 5, 2, 6], k = 100
Output: 8
Explanation: The 8 subarrays that have product less than 100 are: [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
*/
class Solution {
    
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if(nums==null || nums.length==0 || k<=1)
            return 0;
        
        int prod = 1;
        int ans = 0;
        int left = 0;
        
        for (int right = 0; right < nums.length; right++) {
            prod *= nums[right];
            
            while (prod >= k) {
                prod /= nums[left];
                left++;
            }
            
            ans += right - left + 1; // all the fucking logic is here 
        }
        return ans;
    }
}
