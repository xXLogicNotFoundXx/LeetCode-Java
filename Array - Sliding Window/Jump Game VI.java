/*

https://leetcode.com/problems/jump-game-vi/

You are given a 0-indexed integer array nums and an integer k.
You are initially standing at index 0. In one move, you can jump at most k steps forward without going outside the boundaries of the array. 
That is, you can jump from index i to any index in the range [i + 1, min(n - 1, i + k)] inclusive.
You want to reach the last index of the array (index n - 1). 
Your score is the sum of all nums[j] for each index j you visited in the array.

Return the maximum score you can get.
Input: nums = [10,-5,-2,4,0,3], k = 3
Output: 17
Explanation: You can choose your jumps forming the subsequence [10,4,3] (underlined above). The sum is 17.

*/

class Solution {
    public int maxResult(int[] nums, int k) {
        
        /* well i wrote a code for either i+1 OR i+k jump ...
           but we have to do the range of that
            
        int[] dp = new int [nums.length];
        int n = nums.length-1;
        dp[0] = nums[0];
        
        for(int i=1; i<=n; i++){
            
            int sum1 = dp[i-1];
            int sumk = Integer.MIN_VALUE;
            
            if(i-k>=0)
                sumk = dp[i-k];
            
            dp[i] = nums[i] + Math.max(sum1,sumk);
        }
        return dp[n];
        */
        
        // now thinking about it first i thought priority queue would be good 
        // but then it would be n*log(K) 
        // like sliding window max/min 
        // can we use deque and have the max window polling from both side and maintaining min 
        // we can traverse both ways not a problem 
        // we do have to build DP for this 
        int[] dp = new int [nums.length];
        int n = nums.length-1;
        dp[0] = nums[0];
        
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(0);
        
        for(int i=1; i<=n;i++){
            
            // poll outer indexes
            while(!queue.isEmpty() && queue.peekFirst()<i-k){
                queue.pollFirst();
            }
            
            // calculate best path 
            dp[i] = nums[i] + (queue.isEmpty() ? 0 : dp[queue.peekFirst()]);
            
            // poll from last all lesser than DP[i]
            while(!queue.isEmpty() && dp[queue.peekLast()]<=dp[i]){
                queue.pollLast();
            }
            
            queue.offerLast(i);
        }
        
        return dp[n];
    }
}
