//Problem 1 : without duplicates in an array
/*
Medium VIMP
Microsoft25 Amazon18 LinkedIn18 Facebook17 and so many companies!


https://leetcode.com/problems/search-in-rotated-sorted-array/
[4,5,6,7,0,1,2]).
You are given a target value to search. If found in the array return its index, otherwise return -1.
// 5 1 3
// do binary search ..
// The trick is you find the sorted half .. there aways gonna be one half of the array which is sortted.  <- This
// Find the sorted half and move left and right according to the target.
// the conditions you have to check make sure you have <= or >= for the indexes that are not checked for target.
*/
class Solution {
    public int search(int[] nums, int target) {

        int left=0,right=nums.length-1;
        int mid=0;

        while(left<=right){

            mid = left + (right-left)/2;

            if(nums[mid]==target)
                return mid;

            // left and mid could be same so avoid comparing them .. comparing them wont tell us anything
            // if second half is sorted
            if(nums[mid]<nums[right] ){

                 // if target is beetween this range & for right we never checked equality for target hence <=
                if(nums[mid]<target && target<=nums[right])
                    left=mid+1;
                else
                    right=mid-1;

            }else{ // other half is sorted

               // if target is beetween this range & for left we never checked equality for target hence <=
                if(nums[left]<=target && target<nums[mid])
                    right = mid-1;
                else
                    left = mid+1;
            }
        }
        return -1;
    }
}

//Problem 2 : With duplicates in an array
/*
Medium - IMP
lkd4, amz4, msft4
https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
With duplicates in the an input array it will be slightly different
But that slight variation can put this algo to O(n).

I tried following same as Rotated array logic without duplication but it fails for condtiong where i can not decide which partition to go
if element at low , mid and high are same where do you go? so we need extra condition for that
if that is true we can do low++ and high--
Input : 1 1 1 1 1 1 7 1 1


by having duplicate elements in the array, we often miss the opportunity to apply binary search in certain search spaces.
So we get Time - O(n) worst case.
*/
class Solution {
    public boolean search(int[] nums, int target) {

        int left=0,right=nums.length-1;
        int mid=0;

        while(left<=right){

            mid = left + (right-left)/2;

            if(nums[mid]==target)
                return true; // return index of the target.

            if( nums[left] == nums[mid] && nums[mid] == nums[right] ) {
                left++; right--;
                continue;
            }

            // left and mid could be same so avoid comparing them .. comparing them wont tell us anything
            // if second half is sorted
            if(nums[mid]<=nums[right] ){    // '<=' because of duplicates 

                 // if target is beetween this range & for right we never checked equality for target hence <=
                if(nums[mid]<target && target<=nums[right])
                    left=mid+1;
                else
                    right=mid-1;

            }else{ // other half is sorted

               // if target is beetween this range & for left we never checked equality for target hence <=
                if(nums[left]<=target && target<nums[mid])
                    right = mid-1;
                else
                    left = mid+1;
            }
        }
        return false;
    }
}
