Problem 2 : With duplicates in an array 
/*
https://leetcode.com/problems/search-in-rotated-sorted-array-ii/

With duplicates in the an input array it will be slightly different 
But that slight variation can put this algo to O(n).

I tried following same as Rotated array logic without duplication but it fails for condtiong where i can not decide which partition to go 
if element at low , mid and high are same where do you go? so we need extra condition for that 
if that is true we can do low++ and high-- 
Input : 1 1 1 1 1 1 7 1 1

*/
class Solution {
    public boolean search(int[] nums, int target) {
        int low =0, high = nums.length-1;
        while(low<=high){
            
            int mid = (low+high)/2;
            if(nums[mid]==target)
                return true;
            
            if( nums[low] == nums[mid] && nums[mid] == nums[high] ) {
                low++; high--;
                continue;               // Important
            }
            
            if(nums[low]<=nums[mid]){
                if( nums[low]<=target && target < nums[mid])   // <= is important 
                    high=mid-1;
                else
                    low=mid+1;
            }else{
                if(nums[mid]<target && target <=nums[high])     // <= is important
                    low=mid+1;
                else
                    high=mid-1;
            }
        }
        return false; 
    }
}
