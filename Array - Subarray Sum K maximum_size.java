/*
Premium:
325. Maximum Size Subarray Sum Equals k
https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/
Given an array nums and a target value k, find the maximum length of a subarray that sums to k. 
If there isn't one, return 0 instead.
Input: nums = [1, -1, 5, -2, 3], k = 3
Output: 4 
Explanation: The subarray [1, -1, 5, -2] sums to 3 and is the longest.
*/
class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return 0;
        
        Map<Integer,Integer> map = new HashMap();
        int total=0, max=0;
        map.put(0,-1); // if the total becomes K then we should have map entry. 
        for(int i=0;i<nums.length;i++){
            total += nums[i];
            
            if(total==k) // this not needed as we did map.put(0,-1). This another way to do it though.
                max = Math.max(max,i+1);
            
            if(map.containsKey(total-k)){
                max = Math.max(max,i-map.get(total-k));
            }
            
            if(!map.containsKey(total))
                map.put(total,i); // prevent override we want the left most item to form a max size
        }
        return max; 
    }
}
