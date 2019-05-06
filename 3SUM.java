/*
https://leetcode.com/problems/3sum/
Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? 
Find all unique triplets in the array which gives the sum of zero.
The solution set must not contain duplicate triplets.
*/
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        if(nums==null) return ans;
        Arrays.sort(nums);
        for(int i=0;i<nums.length-2;i++){
            while(i!=0 && i<nums.length-2 && nums[i] == nums[i-1])
                i++;
            
            int left=i+1,right=nums.length-1;
            while(left<right){
                int sum = nums[i] + nums[left] + nums[right];
                if(sum==0){
                    List<Integer> subAns =  new ArrayList<>();
                    subAns.add(nums[i]);
                    subAns.add(nums[left]);
                    subAns.add(nums[right]);
                    ans.add(subAns);
                    left++;
                    while(left<right && nums[left] == nums[left-1]) 
                        left++;
                    
                }else if(sum<0)
                    left++;
                else 
                    right--;
            }
            
        }
        return ans;
    }
}
