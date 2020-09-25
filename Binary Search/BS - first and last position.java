/*
https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
*/
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int []ans = new int[2];
        int left = 0, right = nums.length-1;
        ans[0] = ans[1] = -1;
        while(left<=right){
            int mid = left + (right-left)/2;
            if(nums[mid] < target){
                left=mid+1;
            }else if(nums[mid] > target){
                right=mid-1;
            }else if(nums[mid]==target){
                if(nums[left] == nums[mid] && nums[mid] == nums[right]){
                    ans[0] = left;
                    ans[1] = right;
                    break;
                } 
                if(nums[left] < nums[mid])
                    left++;
                if(nums[right] > nums[mid])
                    right--;
            }
        }
        return ans;
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
