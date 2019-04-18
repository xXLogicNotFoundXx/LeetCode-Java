/*
https://leetcode.com/problems/sort-colors/
Sort an array in 1 pass with values 0,1,2 only 
Input: [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
*/
class Solution {
    public void sortColors(int[] nums) {
        int left =0, right=nums.length-1;
        for(int i=0;i<=right;i++){
            if(nums[i]==0){
                nums[i] = nums[left];
                nums[left] = 0;
                left++;
            }
            if(nums[i]==2){
                nums[i] = nums[right];
                nums[right] = 2;
                right--;
                i--;            // This is one important bcz switchin 2 with right may give 0 on ith index
            }
        }
    }
}
