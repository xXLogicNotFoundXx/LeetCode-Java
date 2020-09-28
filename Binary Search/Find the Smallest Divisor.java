/*
1283. Find the Smallest Divisor Given a Threshold
https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold/
Medium

Given an array of integers nums and an integer threshold,
we will choose a positive integer divisor and divide all the array by it and sum the result of the division. 
Find the smallest divisor such that the result mentioned above is less than or equal to threshold.

Each result of division is rounded to the nearest integer greater than or equal to that element. 
(For example: 7/3 = 3 and 10/2 = 5).

It is guaranteed that there will be an answer.

Input: nums = [1,2,5,9], threshold = 6
Output: 5
Explanation: We can get a sum to 17 (1+2+5+9) if the divisor is 1. 
If the divisor is 4 we can get a sum to 7 (1+1+2+3) and if the divisor is 5 the sum will be 5 (1+1+1+2). 
*/
class Solution {
    public int smallestDivisor(int[] nums, int threshold) {
        int left = 1;
        int right = 0;
        for( int num : nums){
            right = Math.max(right,num);
        }
        
        while(left < right){
            int mid = left + (right - left)/2;
            
            int sum = sumOfDivision(nums,mid);
            /*
             There weill be some div that will return sum <= threshold
             but we should keep moving left untill left = right 
             and thats gonna be the smallest div we are looking for. 
            */
            if(sum <= threshold){
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        
        return left;
    }
    
    int sumOfDivision(int []nums, int div){
        int sum=0;
        for(int num : nums){
            sum += num/div;
            sum += num%div !=0 ? 1 : 0;
        }
        return sum;
    }
}
