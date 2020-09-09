/*
 https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 Array has distinct Element
 the trick here is ... find the one which is not sorted half 
 so you compaire if x at  mid > right then weh go that half and we do 
 left = mid + 1 as we know mid is already greater than right  
 and elest right = mid
*/
class Solution {
    public int findMin(int[] nums) {
        int left = 0,  right = nums.length - 1;
        // if array is not rotated at all 
        if(nums[left] < nums[right]) 
            return nums[left];
        
        while(left < right) {
                
            int mid = (left + right)/2;
            
            if(nums[mid] > nums[right])
                left = mid + 1;
            else
                right = mid;
        }

        return nums[right]; // right or left both works [1], [3,1], [1,2]
    }
}

// https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/
// Array has duplicates too 
class Solution {
    public int findMin(int[] nums) {
        int left =0, right = nums.length-1;
        while(left<right){
            int mid = (left + right)/2;
            
            if(nums[left] == nums[mid] && nums[mid] == nums[right]){ // i/p =[ 1,1,1,1,1,1,1,1,0,1,1]
                left++; right--;
                continue;
            }
                
            if(nums[mid] > nums[right])
                left = mid+1;
            else 
                right = mid;
        }
        return nums[left];   // even right index will work
    }
}
