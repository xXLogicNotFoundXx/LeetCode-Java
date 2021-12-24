/*
https://leetcode.com/problems/3sum-smaller/
Given an integer array A, and an integer target, return the number of tuples i, j, k  
such that i < j < k and A[i] + A[j] + A[k] < target.

NOTE: 
You would think sorting will mess up the order of i,j,k indexes 
This is USELESS detail bcz i,j,k can be reorder to any combination and it doesnt matter.
A[i] + A[j] + A[k] can be A[k] + A[i] + A[j]
Moreover there are not even asking to return tuples of i,j,k  

Complexity analysis
Time complexity : O(n^2). 
Space complexity : O(1).

nums = [1, 2, 3, 5, 8], and target = 7.
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
