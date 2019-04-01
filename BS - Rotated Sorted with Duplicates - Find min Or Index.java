https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/
// Array has duplicates too 
class Solution {
    public int findMin(int[] nums) {
        int left =0, right = nums.length-1;
        while(left<right){
            int mid = (left + right)/2;
            
            if(nums[left] == nums[mid] && nums[mid] == nums[right]){ // i/p =[ 1,1,1,1,1,1,1,1,0,1,1]
                left++; right--;
                continue;                       // important i always miss that man 
            }
                
            if(nums[mid] > nums[right])
                left = mid+1;
            else 
                right = mid;
        }
        return nums[left];   // even right is gonna be the same so nums[right works too]
    }
}
