/*
https://leetcode.com/problems/3sum-smaller/
Given an integer array A, and an integer target, return the number of tuples i, j, k  
such that i < j < k and A[i] + A[j] + A[k] < target.
Complexity analysis
Time complexity : O(n^2). 
The twoSumSmaller function takes O(n) time because both left and right traverse at most n steps. 
Therefore, the overall time complexity is O(n^2).
Space complexity : O(1).

nums = [1, 2, 3, 5, 8], and target = 7.

[1,  2,   3, 5,  8]
 i   left       right 
 target= 7-a[i] = 6 

*/
class Solution{
    public int threeSumSmaller(int[] nums, int target) { 
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            
            int left = i+1, right = nums.length - 1;  
            while (left < right){ 

                if (arr[i] + arr[left] + arr[right] < target){ 
                    sum += (right - left); 
                    left++; 
                } else{
                    right--; 
                }
            } 
        }
        return sum; 
    }
}
