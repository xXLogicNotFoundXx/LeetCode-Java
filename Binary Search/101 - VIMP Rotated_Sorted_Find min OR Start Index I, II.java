/*
Medium - IMP
 Fb-7 msft-6 amz-6 uber-2

https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 Array has distinct Element
 the trick here is ... find the one which is not sorted half
 so you compaire if x at  mid > right then weh go that half and we do
 left = mid + 1 as we know mid is already greater than right
 and elest right = mid
*/
class Solution {
    public int findMin(int[] nums) {
        if(nums==null || nums.length==0)
            return 0;

        int left=0, right = nums.length-1;

        while(left<right){
            int mid = left + (right-left)/2; // rule of thumb -> l +(r-l)/2 when left is moving 

            if(nums[left] <= nums[mid] && nums[mid] <= nums[right])
                return nums[left];

            if(nums[left] > nums[mid]) // this part is not sorted.
                right = mid;           // So mid could be the lowest or it is beetween left-mid
            else
                left = mid+1;
        }

        return nums[left];
    }
}

// Medium IMP
// in last year Amazon4 Microsoft3 Google3 Facebook3
// https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/
// Array has duplicates too
class Solution {
    public int findMin(int[] nums) {
        if(nums==null || nums.length==0)
            return 0;

        int left=0, right = nums.length-1;

        while(left<right){
            int mid = left + (right-left)/2; // rule of thumb -> l +(r-l)/2 when left is moving

            if(nums[left] == nums[mid] && nums[mid]==nums[right]){
                left++;right--;
                continue;
            }

            if(nums[left] <= nums[mid] && nums[mid] <= nums[right])
                return nums[left];

            if(nums[left] > nums[mid]) // this part is not sorted.
                right = mid;           // So mid could be the lowest or it is beetween left-mid
            else
                left = mid+1;
        }

        return nums[left];
    }

}
