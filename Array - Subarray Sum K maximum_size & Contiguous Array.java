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

/*
https://leetcode.com/problems/contiguous-array/
525. Contiguous Array
Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.
Input: [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
*/
// Test case [0,1,0,1,0,1] -> 6 ( this one is hard to calculate )
// Test case [0,0,0,0,0,1,1] -> 4
class Solution {
    
    // O(N^2) - TLE 
    public int findMaxLength1(int[] nums) {
        int max = 0;
        
        for(int i=0; i<nums.length; i++){
            int zeros=0, ones=0;
            
            for(int j=i; j<nums.length; j++){
                
                zeros += nums[j]==0 ? 1 : 0;
                ones  += nums[j]==1 ? 1 : 0;
                
                if(zeros==ones)
                  max = Math.max(max,j-i+1);  
            }
        }
        return max;
    }
    
    // Oh shit! If you think, this is like Maximum Subarray Sum equals to K.
    // if we replace 0 with -1 then we need Maximum Subarray Sum equals to 0.
    // So it can be solved by prefix sum and HashMap 
    // Time & Space O(N) & O(N)
    public int findMaxLength(int[] nums) {
        
        int max = 0;
        Map<Integer,Integer> map = new HashMap<>(); // prefixSum, Index
        int prefixSum=0;
        
        //map.put(0,-1); if prefixSum beacomes zero 
        for(int i=0; i<nums.length; i++){
            prefixSum += nums[i]==0 ? -1 : 1;
            
            if(prefixSum==0) // if we dont use-> map.put(0,-1);
                max = Math.max(max,i+1);
            
            if(map.containsKey(prefixSum)){
                max = Math.max(max,i-map.get(prefixSum));
            } else {
                map.put(prefixSum,i);
            }
        }
        
        return max;
    }
    
}
