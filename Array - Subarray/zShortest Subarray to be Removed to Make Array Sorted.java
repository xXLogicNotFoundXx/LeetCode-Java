/*
Medium - no one asked this question in last 2 years. 

Shortest Subarray to be Removed to Make Array Sorted
https://leetcode.com/problems/shortest-subarray-to-be-removed-to-make-array-sorted/

Given an integer array arr, remove a subarray (can be empty) from arr such that the remaining elements in arr are non-decreasing.
A subarray is a contiguous subsequence of the array.
Return the length of the shortest subarray to remove.

Input: arr = [1,2,3,10,4,2,3,5]
Output: 3
Explanation: The shortest subarray we can remove is [10,4,2] of length 3. The remaining elements after that will be [1,2,3,3,5] which are sorted.
Another correct solution is to remove the subarray [3,10,4].

idea is
  go left->right and find ith position when it is non-incremental
  go right->left and find jth position where it is non-decremental
  0----i<This is the range needs to be removed>j---(N-1)
  [1,2,3,10,4,2,3,5]
   ------>i   j<---
  by this logic we need to remove only 4 so far but we need to consider 10 and 2 as well
  But it is possible if we may need to consider more numbers on left & right
  which will form bigger non-decreasing subarray in this case 10 & 2

*/


class Solution {
    public int findLengthOfShortestSubarray(int[] nums) {
        int i=0, j=nums.length-1;

        while(i<nums.length-1 && nums[i]<=nums[i+1]){
            i++;
        }

        if(i==nums.length-1) return 0;

        while(j>0 && nums[j-1]<=nums[j]){
            j--;
        }

        // int ans = nums.length-1; // max_possible
        // [2,2,2,1,1,1] in this case its good to remove either left part or right part completely
        // int ans = Math.min(i+1,nums.length-j); [5,4,3,2,1] o/p 1 expected 4
        int ans = Math.min(nums.length - i - 1, j);
        int left=0;
        int right=j;

        while(left<=i && right<=nums.length-1){

            if(nums[left]<=nums[right]){

                ans=Math.min(ans, right-left-1);
                left++;

            }else{
                right++;
            }
        }

        return ans;
    }
}
