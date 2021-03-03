/*
https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
*/
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int []ans = new int[2];
            
        if(nums.length==0){
            return new int[]{-1,-1};
        }
        
        ans[0] = findLeftIndex(nums,target);
        ans[1] = findRightIndex(nums,target);
        
        return ans;
    }
    
    int findLeftIndex(int[] nums, int target){
        int left = 0, right = nums.length-1;
        int res = -1;
        while(left<=right)
        {
            int mid = left + (right-left)/2;
            
            if(nums[mid]==target){
                res = mid;
                right = mid-1;
            }
            else if(nums[mid]>target){
                right = mid-1;
            }
            else{
                left = mid+1;
            }
        }
        return res;
    }
    
    int findRightIndex(int[] nums, int target){
        int left = 0, right = nums.length-1;
        int res = -1;
        while(left<=right)
        {
            int mid = left + (right-left)/2;
            
            if(nums[mid]==target){
                res = mid;
                left = left+1;
            }
            else if(nums[mid]>target){
                right = mid-1;
            }
            else{
                left = mid+1;
            }
        }
        return res;
    }
}

/* O(n)
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int []ans = new int[2];
        ans[0] = ans[1] = -1;
        for(int i=0;i<nums.length;i++){
            if(nums[i]==target){
                ans[0] = ans[0]==-1 ? i :  ans[0];
                ans[1] = i;
            }
        }
        return ans;
    }
}*/
