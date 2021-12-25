/*
Hard - IMP lot of companies

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

Without the constraints , we can put everything in the set and iterate from 1 to size;
Time O(N) and space O(N)
*/
class Solution {

  // Solution 1 : Cyclic swap - very elegant solution
  public int firstMissingPositive(int[] nums) {
        if(nums==null)
            return 0;

        // cyclic sort .. put all numbers in their respecyive index
        for(int i=0; i<nums.length; i++){

            while( nums[i]>0 && nums[i] <= nums.length && nums[i] != i+1 && nums[i] != nums[nums[i]-1]){
                                                                         // ^ This condition to avoid infinte loop [1,1] gives TLE
                // in loops like this alwasy think of avoiding infinite loop.
                swap(nums, i, nums[i]-1);
            }
        }

        for(int i=0; i<nums.length; i++){
            if(nums[i]!=i+1)
                return i+1;
        }

        return nums.length+1;
    }

    private void swap(int nums[], int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


  // Solution 2
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



}
