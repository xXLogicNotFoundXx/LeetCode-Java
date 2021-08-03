/*
https://leetcode.com/problems/first-missing-positive/
41. First Missing Positive
Given an unsorted integer array, find the smallest missing positive integer.

Example 1:

Input: [1,2,0]
Output: 3
Example 2:

Input: [3,4,-1,1]
Output: 2
Example 3:

Input: [7,8,9,11,12]
Output: 1
Follow up:

Your algorithm should run in O(n) time and uses constant extra space.
*/
class Solution {
    public int firstMissingPositive1(int[] nums) {
        
        if(nums==null || nums.length==0)
            return 1;
        
        // mark all ouside range <1 && n< to -1 
        for(int i=0;i<nums.length;i++){
            if(nums[i] < 1 || nums[i] > nums.length){
                nums[i]=-1;
            }
        }
        
        // Whenever we get the +ve number
        // Using Cyclic Replacements put zeros in respective places 
        // Almost like 3rd approach in : https://leetcode.com/problems/rotate-array/solution/   
        for(int i=0;i<nums.length;i++){
            
            if(nums[i] != -1){
                int j = nums[i]-1;
                
                while(j>=0 && j<nums.length){
                    
                    if( nums[j] != -1){
                        int index1 = nums[j]-1;
                        nums[j] = 0; 
                        j = index1;
                    } else {
                        nums[j]=0;
                        break;
                    }
                }

            }
            
        }
        
        // the first index value that is not zero thats missing number.
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=0)
                return i+1;
        }
        
        // all 1-N are in the array 
        return nums.length+1;
        
    }
    
    
    // Solution 2 : Cyclic swap
    public int firstMissingPositive(int[] nums) {
       
        int n = nums.length;
        for (int i = 0; i < nums.length; i++){
            //Put each positive number less than or equal to n in its right place.
            while(nums[i] > 0 && nums[i] <= n && nums[i] != nums[nums[i] - 1]){
                swap(nums, i, nums[i] - 1); //swap nums[i] & nums[nums[i] - 1] elements
            }
        }
        
        for(int i = 0; i < n; i++) {
            if(nums[i] != i + 1)
                return i + 1;
        }
        
        return n + 1;
    }
    
    private void swap(int nums[], int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
