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

        int left = 0,  right = nums.length - 1;

        // if array is not rotated at all
        if(nums[left] < nums[right])
            return nums[left];

        while(left < right) {

            // rule of thumb -> l +(r-l)/2 when left is moving else r - (r-l)/2
            int mid = left + (right-left)/2;

            if(nums[mid] < nums[right]) // this is sorted ...
                right = mid;            // right will alwasy be pointing to lowesr number
            else
                left = mid + 1;

           // ***** if you do ****
           // nums[left] > nums[mid] and change right=mid .. it doesnt work why ?
           // bcz left could be same as calculated mid and we should not compare these two
           // because comparing same value doesnt tell us anything.
           // hence the conndition should always be if(nums[mid] < nums[right])
        }

        return nums[right]; // right or left both works [1], [3,1], [1,2]
    }
}

// Medium IMP
// in last year Amazon4 Microsoft3 Google3 Facebook3
// https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/
// Array has duplicates too
class Solution {
    public int findMin(int[] nums) {

        int left = 0,  right = nums.length - 1;

        // if array is not rotated at all
        if(nums[left] < nums[right])
            return nums[left];

        while(left < right) {

            // rule of thumb -> l +(r-l)/2 when left is moving else r - (r-l)/2
            int mid = left + (right-left)/2;

            if(nums[left] == nums[mid] && nums[mid]==nums[right]){
                left++;right--;
                continue;
            }


            if(nums[mid] <= nums[right]) // this is sorted ... '<=' necause of duplicates
                right = mid;            // right will alwasy be pointing to lowest number
            else
                left = mid + 1;

           // ***** if you do ****
           // nums[left] >= nums[mid] and change right=mid .. it doesnt work why ?
           // bcz left could be same as calculated mid and we should not compare these two
           // because comparing same value doesnt tell us anything.
           // hence the conndition should always be if(nums[mid] < nums[right])
        }

        return nums[right]; // right or left both works [1], [3,1], [1,2]
    }
}
