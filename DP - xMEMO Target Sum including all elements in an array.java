https://leetcode.com/problems/target-sum/
// Below is the recursive which is 2^N as each number is considered as +ve and considered -ve

    public int findTargetSumWays1(int[] nums, int S) {
        return findTargetSumWays(nums,0,0,S);
    }
    int findTargetSumWays(int[] nums, int i,int target, int S){
        if(i==nums.length){
            retrun S==target ? 1 : 0;
        }
        return findTargetSumWays(nums,i+1,target-nums[i],S) + findTargetSumWays(nums,i+1,target+nums[i],S);
    }


// Below is the recursive with memoization 
Using memoization improves the time complexity actually, think it like this:
Without memoization, the time complexity is actually O(2^n), where n is the length of the array,
since we are trying all the possible combinations.
By using memoization, we calculate every sum at each index at most once,
that makes the time complexity O(n*sum), which is the same as those DP solutions

class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        if(nums==null) return 0;
        HashMap<String,Integer> map = new HashMap<>();
        return findTargetSumWays(nums, S, map, 0);
    }
    int findTargetSumWays(int []nums, int sum, HashMap<String,Integer> map , int i){
        if(i==nums.length){
            return sum==0 ?  1 : 0;
        }
        
        if(map.containsKey(i+"->"+sum))
            return map.get(i+"->"+sum);
        
        int positive = findTargetSumWays(nums,sum-nums[i], map, i+1);
        int negative = findTargetSumWays(nums,sum+nums[i], map, i+1);
        
        map.put(i+"->"+sum,positive+negative);
        return positive+negative;
    }
}


You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. 
Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.

Find out how many ways to assign symbols to make sum of integers equal to target S.
Example 1:
Input: nums is [1, 1, 1, 1, 1], S is 3. 
Output: 5
Explanation: 
-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3
There are 5 ways to assign symbols to make the sum of nums be target 3.

class Solution {
    // This problem is simlar like subset sum problem - 0/1 knapsack problem..
    // but as we know subset sum/knapsack wont work with negative numbers ... So how do we solve this problem
    // all the numbers are positive in an array so ...
    /*
    Let P be the positive subset and N be the negative subset
    For example:
    Given nums = [1, 2, 3, 4, 5] and target = 3 then one possible solution is +1-2+3-4+5 = 3
    Here positive subset is P = [1, 3, 5] and negative subset is N = [2, 4]

    Then let's see how this can be converted to a subset sum problem:

                      sum(P) - sum(N) = target
    sum(P) + sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N)
                           2 * sum(P) = target + sum(nums)
    So the original problem has been converted to a subset sum problem as follows:
    Find a subset P of nums such that sum(P) = (target + sum(nums)) / 2
    */
    
   public int findTargetSumWays(int[] nums, int S) {
        int totalSum =0; 
        for(int num : nums){
            totalSum+=num;
        }
        if((totalSum+S)%2 != 0) return 0;
        if(S>totalSum) return 0;  // FAILED for totalSum [1,2,7,9,981] 1000000000  Memory Limit Exceeded
        int sumToFind = (totalSum+S)/2;
        int []dp = new int[sumToFind+1];
        Arrays.fill(dp,0);
        dp[0] = 1;
        for(int number : nums){
            for(int i=sumToFind;i>=number;i--){
                dp[i] += dp[i-number];
            }
        }
        // eventually we would frind path from sumToFind to 0
        return dp[sumToFind];
    }




