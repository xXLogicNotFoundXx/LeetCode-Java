/*

Medium - lkd-5 amz-2 msft-2 .. not that many companies - let it be ... too unique and too much math.

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
            //This ^ sliding window approach is only valid because numbers are positive.
        }
        return ans;
    }
/*
I though i would do (n*(n-1))/2 to calculate number of subarrays. But that doesnt work as we are moving one step at a time.

If [5,2,6] is a subarray with product less than K, then all subarrays of [5,2,6] have product less than K.
The number of subarrays can be calculated with an arithmetic series, in this case 1 + 2 + 3 = 6

So for the subarray [5,2,6] (from [10, 5, 2, 6]), your loop would look like:

left = 1, right = 1, right - left + 1 = 1
left = 1, right = 2, right - left + 1 = 2
left = 1, right = 3, right - left + 1 = 3

For a total of 6 subarrays
*/
}
