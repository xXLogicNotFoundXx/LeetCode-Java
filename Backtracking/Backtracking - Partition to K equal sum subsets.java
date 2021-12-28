/*
Medium - lkd9 FB3 amz3 msft3 google2
https://leetcode.com/problems/partition-to-k-equal-sum-subsets/

Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into
k non-empty subsets whose sums are all equal.
Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
Output: True

How do you decide what total we are looking at?
Total_Sum / K => Should be the SUM.
if(Total_Sum % K !=0) then there is no ans.
We can not divide an array into K subsets having equal sum.


Then, let's recursively search, where at each call to our function, we choose which of k subsets the next value will join.

time complexity: O(k*2^n)
*/
class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if(nums==null || nums.length<k || k<=0 )
          return false;

        int total =0;
        for(int n:nums)
          total+=n;

        if(total%k>0)
          return false;

        boolean[] used = new boolean[nums.length];
        return backtrack(nums,used,k,total/k,0,0);
    }

    boolean backtrack(int[]nums,boolean[] used, int k, int target, int start, int sum){
        // End condition
        if(k==0) // we did all the partitions sucessfully
            return true;

        if(sum>target)
            return false;

        if(sum==target)
           return backtrack(nums,used,k-1,target,0,0);

        for(int i=start; i<nums.length; i++){

            if(used[i]!= true){
                used[i] = true;
                if(backtrack(nums,used,k,target,i+1,sum+nums[i]))
                    return true;
                used[i] = false;
            }
        }

        return false;
    }
}
