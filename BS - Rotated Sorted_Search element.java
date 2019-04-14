Problem 1 : without duplicates in an array 
/*
https://leetcode.com/problems/search-in-rotated-sorted-array/
[4,5,6,7,0,1,2]).
You are given a target value to search. If found in the array return its index, otherwise return -1.
// 5 1 3
// do binary search .. 
// the trick is you find the sorted half and if the target is in that sorted half search in that 
*/
class Solution {
    public int search(int[] nums, int target) {
        int low = 0, high = nums.length-1, mid =(low+high)/2;
        while(low<=high){
            mid =(low+high)/2;
            if(nums[mid]==target)
                return mid;
            
            if(nums[low] <= nums[mid]){ // first half is sorted
                if(nums[low] <= target && target<nums[mid]) // if its in beetween them 
                    high = mid-1;
                else
                    low = mid+1;
            } else { // other half is sorted 
                if(nums[mid] < target && target<=nums[high]) // if its in beetween them 
                    low = mid+1;
                else 
                    high = mid-1;
            }
            
        }
        return -1;
    }
}

