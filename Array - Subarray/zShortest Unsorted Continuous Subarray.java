/*
Medium - imp

https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
Facebook-4 microsoft3 Oracle3 Amazon2

Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order,
then the whole array will be sorted in ascending order, too.

You need to find the shortest such subarray and output its length.

Input: [2, 6, 4, 8, 10, 9, 15]
Output: 5
Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
*/
class Solution {

    // 2 PASS solution
    // Apparently we need to find min and max of when we find  nums[i-1] > nums[i]
    // but finding max and min is not enough
    // i/p= [1,3,5,2,4]  will find 2 and 5 but we need the consideration of that 3 & 4 too
    // we traverese nums and find min and max on condition nums[i-1] > nums[i]
    // we traverse and find
    // first occurrence of nums[i]>min ( i is gonna be start)
    // find last occurrence of nums[i]<max ( i is gonna be end index)
    public int findUnsortedSubarray1(int[] nums) {

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i=1; i<nums.length; i++) {
            if (nums[i-1] > nums[i]){
                min = Math.min(min, nums[i]);
                max = Math.max(max, nums[i-1]);
            }
        }

        // corner case when array is sorted
        if(min==Integer.MAX_VALUE && max == Integer.MIN_VALUE)
            return 0;

        int left  = nums.length-1;
        int right = 0;

        for (int i=0; i<nums.length-1; i++){
            if(nums[i]>min)
                left=Math.min(left,i);
            if(nums[i]<max)
                right=Math.max(right,i);;
        }

        return right-left+1;
    }

    // This was my first solution (It works now but I had to change code couple times to get here)
    // Man I missed so many cases. I was trying to do it in one pass. without while loops in that
    // Cases = [2,3,3,2,4] , [1,3,2,3,3] & [1,3,5,2,4]
    // This is in a way 2 Pass solution too.
    public int findUnsortedSubarray(int[] nums) {

        int left=Integer.MAX_VALUE;
        int right=0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int i=0;i<nums.length-1;i++){

            if(nums[i]>nums[i+1]){

                max = Math.max(max,nums[i]);
                min = Math.min(min,nums[i+1]);

                left = Math.min(left,i);
                right = Math.max(right,i+1);

                while(left>=1 && nums[left-1]>min) // this I added later for [2,3,3,2,4] and min var for [1,3,5,2,4]
                    left--;

                while(right<nums.length-1 && nums[right+1]<max) // this i added later  [1,3,2,3,3] and max var for [1,3,5,2,4]
                    right++;
            }

        }

        return left==Integer.MAX_VALUE ? 0 : right-left+1;
    }
}
