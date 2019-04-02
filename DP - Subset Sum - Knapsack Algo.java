https://leetcode.com/problems/partition-equal-subset-sum/
Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets
such that the sum of elements in both subsets is equal.

Input: [1, 5, 11, 5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].

Input: [1, 2, 3, 5]
Output: false

Input: [1, 2, 3, 5,1]
Output: true

class Solution {

    public boolean canPartition(int[] nums) {
        int totalSum =0;
        for(int i=0;i<nums.length;i++){
            totalSum += nums[i];
        }
        if(totalSum%2 != 0) return false; // should be even
        int sumToFind = totalSum/2;
        boolean []dp = new boolean[sumToFind+1];
        Arrays.fill(dp,false);
        dp[0] = true;
        for(int number : nums){
            for(int i=sumToFind;i>=number;i--){
                dp[i] = dp[i] || dp[i-number];
            }
        }
        // eventually we would find path from sumToFind to 0
        return dp[sumToFind];
    }
}
