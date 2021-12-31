/*
Medium - IMP
Facebook11 Google4 Amazon3 ans some more


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


The trick is ... how do you make use of only one number only once while doing a knapsack ...
Both previous coin change problem coud use infinite number of coint of each type.

*/
class Solution {

    public boolean canPartition(int[] nums) {
        int totalSum =0;
        for(int i=0;i<nums.length;i++){
            totalSum += nums[i];
        }

        if(totalSum%2 != 0)
            return false;

        int sumToFind = totalSum/2;

        // now it becomes almsot coin change kinda problem - BUT
        // ******* here we are supposed to use the number only one time *****
        // how do you do it?
        // Here you can start from the Sum to 0 and use the coin/number to mark the number that are
        // reachable from that number as true.
        // eventually sumToFind index becomes TRUE iff there is a path from the numbers we have marked true.

        boolean []dp = new boolean[sumToFind+1];
        Arrays.fill(dp,false);

        dp[0] = true;

        for(int number : nums){

            for(int i=sumToFind; i>0; i--){
                if( i-number >= 0)
                    dp[i] = dp[i] || dp[i-number];

            }
        }
        // eventually we would find path from sumToFind to 0
        return dp[sumToFind];
    }

    // Time  O(MxN) M is sumToFind and N is array size
    // Space O(M)
}
