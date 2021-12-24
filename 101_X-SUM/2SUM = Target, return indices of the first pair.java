
/*
https://leetcode.com/problems/two-sum/
Given an array of integers, return indices of the two numbers such that they add up to a specific target.
Given nums = [2,-7,11,-2,15], target = -5,
return [0, 1]
// We do target-nums[i] and put that in the map with index 
// if we find that number going through loop we found the two indices  
*/

class Solution {
    public int[] twoSum(int[] nums, int target) {
        if(nums==null) 
            return null;
        
        Map<Integer,Integer> sumMap = new HashMap<>(); 
        
        for(int i=0;i<nums.length;i++){
            
            if(sumMap.containsKey(target-nums[i])){
                int[] ans = new int[2];
                ans[0]=sumMap.get(target-nums[i]);
                ans[1]=i;
                return ans;
            }
            
            sumMap.put(nums[i],i);
        }
        return null;
    }
}

