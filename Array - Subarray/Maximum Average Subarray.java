/*
643. Maximum Average Subarray I
https://leetcode.com/problems/maximum-average-subarray-i/
Given an array consisting of n integers, find the contiguous subarray of given length k that has the maximum average value. 
And you need to output the maximum average value.
Input: [1,12,-5,-6,50,3], k = 4
Output: 12.75
Explanation: Maximum average is (12-5-6+50)/4 = 51/4 = 12.75
*/
class Solution {
    public double findMaxAverage(int[] nums, int k) {
        if(nums==null || k<=0 || k>nums.length)
            return 0;
        
        double ans=-Double.MAX_VALUE, sum=0; // Double.MIN_VALUE is not negative
        int left=0;
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
            if(i-left==k-1){
                ans = Math.max(ans,sum/k);
                sum-=nums[left];
                left++;
            } 
        }
        return ans;
    }
}

/*
Note : 
Short: Short data type is a 16-bit signed two's complement integer.
int: Int data type is a 32-bit signed two's complement integer.
long: Long data type is a 64-bit signed two's complement integer.
float: Float data type is a single-precision 32-bit IEEE 754 floating point.
double: double data type is a double-precision 64-bit IEEE 754 floating point.

The IEEE 754 format has one bit reserved for the sign and the remaining bits representing the magnitude. 
This means that it is "symmetrical" around origo (as opposed to the Integer values, which have one more negative value). 
Thus the minimum value is simply the same as the maximum value, with the sign-bit changed, 
so -Double.MAX_VALUE is the smallest possible actual number you can represent with a double.
*/
