https://leetcode.com/problems/product-of-array-except-self/

Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the 
product of all the elements of nums except nums[i].
Input:  [2,3,4,5]
Output: [60,40,30,24]

you can only use * operation 
// This is a great example of 2 pass algorithm..
// first pass make sure you get all 0 to i-1 multiplication at ans[i]
// second pass from back you make sure  i+1 to n multiplication at ans[i]

class Solution {
    
    // This is a great example of 2 pass algorithm..
    // first pass make sure you get all 0 to i-1 multiplication at ans[i]
    // second pass from back you make sure  i+1 to n multiplication at ans[i]
    public int[] productExceptSelf(int[] nums) {
        if(nums==null | nums.length == 0) return new int[0];
        int result[] = new int[nums.length];
        int temp = 1;
        for(int i=0;i<nums.length;i++){
            result[i] = temp;
            temp = temp * nums[i];
        }
        // nums =   [2,3,4,5]
        // result = [1,2,6,24]
        temp = 1;
        for(int i=nums.length-1 ;i>=0;i--){
            result[i] = result[i]*temp;
            temp = temp * nums[i];
        }
        // nums =   [ 2, 3, 4, 5]
        // tmp  =     60,20,5,1
        // result = [60,40,30,24]
        return result; 
    }
}
