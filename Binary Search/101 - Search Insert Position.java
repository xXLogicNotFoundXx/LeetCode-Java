/*

Easy
apl,gool,amz,fb - 3 times in last 6 months

35. Search Insert Position
https://leetcode.com/problems/search-insert-position/
Given a sorted array and a target value, return the index if the target is found.
If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.
Input: [1,3,5,6], 5
Output: 2

*/
// Think of [1,3,5,6] 7 OR  [2,3,4] 1
class Solution {
    public int searchInsert(int[] nums, int target) {

        int left=0, right=nums.length-1;

        // nice!
        if(target<nums[left])
            return 0;
        // nice!
        if(target>nums[right])
            return right+1;

        while(left<right){
            int mid = left + (right-left)/2;

            if(nums[mid]<target)
                left=mid+1;         // we want the Ceiling value/index or equal
            else
                right=mid;
        }
        return left;
    }
}
