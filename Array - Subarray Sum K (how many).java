/* 
Continuous - How many Subarray Sum Equals K 
https://leetcode.com/problems/subarray-sum-equals-k/
Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Prefix Sum strategy  
Example 1:
Input:nums = [1,1,1], k = 2
Output: 2
*/
class Solution {
    
    // Time & space = O(n) & O(n) 
    public int subarraySum(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap();
        int total=0;
        int count=0;
        map.put(0,1); // start of the array (for the case total==k)
        for(int i=0;i<nums.length;i++){
            total += nums[i];
            if(map.containsKey(total-k)){
                count += map.get(total-k);
            }
            map.put(total,map.getOrDefault(total,0)+1);
        }
        return count; 
    }
    
    // Time & space = O(N^2) O(1)
    public int subarraySum1(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; start++) {
            int sum=0;
            for (int end = start; end < nums.length; end++) {
                sum+=nums[end];
                if (sum == k)
                    count++;
            }
        }
        return count;
    }
}
