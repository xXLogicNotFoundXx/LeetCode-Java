
https://leetcode.com/problems/two-sum/
Given an array of integers, return indices of the two numbers such that they add up to a specific target.
Given nums = [2, 7, 11, 15], target = 9,
return [0, 1]
// We do target-nums[i] and put that in the map with index 
// if we find that number going through loop we found the two indices  

class Solution {
    public int[] twoSum(int[] nums, int target) {
        if(nums==null) return null;
        Map<Integer,Integer> sumMap = new HashMap<Integer,Integer>(); 
        for(int i=0;i<nums.length;i++){
            if(sumMap.containsKey(nums[i]))
                return new int[]{sumMap.get(nums[i]),i};
            sumMap.put(target-nums[i],i);
        }
        return null;
    }
}
