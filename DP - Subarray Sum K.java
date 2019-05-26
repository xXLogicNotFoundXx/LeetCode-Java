/* 
Continuous - Subarray Sum Equals K
https://leetcode.com/problems/subarray-sum-equals-k/
Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:
Input:nums = [1,1,1], k = 2
Output: 2
*/
class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap();
        int total=0;
        int count=0;
        map.put(0,1);
        for(int i=0;i<nums.length;i++){
            total += nums[i];
            if(map.containsKey(total-k)){
                count += map.get(total-k);
            }
            map.put(total,map.getOrDefault(total,0)+1);
        }
        return count; 
    }
}
