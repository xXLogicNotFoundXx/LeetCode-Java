/*
Medium - No companies at all ... still good one 

1493. Longest Subarray of 1's After Deleting One Element
https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/
Given a binary array nums, you should delete one element from it.

Return the size of the longest non-empty subarray containing only 1's in the resulting array.
Return 0 if there is no such subarray.

Input: nums = [1,1,0,1]
Output: 3

Input: nums = [0,1,1,1,0,1,1,0,1]
Output: 5

Time Space : O(N) O(1)
*/
class Solution {
    // I wrote a code but missed these cornercases
    // 1 if all are 1's in the array [1,1,1] ans should be 2, NOT 3. Bcz we deleted one number.
    // 2 if all are zeros. [0,0,0] ans should be 0.
    // 3 where 0 are consecutive  [1,1,0,0,1,1,1,0,1]
    public int longestSubarray(int[] nums) {

        if(nums==null || nums.length==0)
            return 0;

        int ones = 0;
        int max = 0;
        int prevOnes = 0;

        for(int i=0;i<nums.length;i++){
            if(nums[i]==1){
                ones++;
                max = Math.max(max,ones+prevOnes);
            } else{
                prevOnes = ones;
                ones=0;
            }
        }

        return max==nums.length ? max-1 : max; // -1 bcz we deleted one element
    }
}
