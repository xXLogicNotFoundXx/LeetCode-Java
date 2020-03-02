//Problem 1 : without duplicates in an array 
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
            
            if(nums[low] <= nums[mid]){ // first half is sorted  low and mid could be the same ..i/p[3,1] target 1 
                if(target<nums[mid] && nums[low] <= target ) // if its in beetween them 
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

//Problem 2 : With duplicates in an array 
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
            
            if(nums[low]<=nums[mid]){ // low and mid could be the same but not high i/p [3,1] targer 1 
                if( target < nums[mid] && nums[low]<=target)   // <= is important 
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
