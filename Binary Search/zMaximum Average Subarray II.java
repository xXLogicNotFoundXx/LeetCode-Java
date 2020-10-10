/*
https://leetcode.com/problems/maximum-average-subarray-ii/
Given an array consisting of n integers, find the contiguous subarray whose length is 
greater than or equal to k that has the maximum average value. 
And you need to output the maximum average value.

1 <= k <= n <= 10,000.
Elements of the given array will be in range [-10,000, 10,000].
The answer with the calculation error less than 10^-5 will be accepted.

Input: [1,12,-5,-6,50,3], k = 4
Output: 12.75
Explanation:
when length is 5, maximum average value is 10.8,
when length is 6, maximum average value is 9.16667.
Thus return 12.75.

*/
class Solution {
    
    // O(N^2)
    public double findMaxAverage1(int[] nums, int k) {
        
        double ans = Double.MAX_VALUE*-1;
        
        for(int i=0; i<=nums.length-k; i++){
            double sum = 0;
            
            for(int j=i; j<nums.length; j++){
                sum += nums[j];
                if(j-i+1 >= k){
                    ans = Math.max(ans, sum/(j-i+1));
                }
            }
        }
        return ans;
    }
    
    
    // O(N log.M)
    public double findMaxAverage(int[] nums, int k) {
        // what is the min avg and what is the max avg possible?
        // min avg would be minNums and max avg would be maxNums
        // use binary search on it. 
        double left=0,right=0;
        for(int num : nums){
            left  = Math.min(left,num);
            right = Math.max(right,num);
        }
        
        while( (left + 0.00001) < right){ 
            // The answer with the calculation error less than 10^-5 will be accepted.
            
            double mid = left + (right-left)/2;
            
            if (check(nums, k, mid)) 
                left=mid; 
            else 
                right=mid; 
        }
        
        return left;
    }
    
    // Find if there is subarray can form avg 
    boolean check(int[] nums, int k, double avg){
        
        int n=nums.length;
        double[] a= new double[n];
        
        // Transfer to a[i], 
        // a[] stores a1-mid, a2-mid, a3-mid ... aj-mid
        for (int i= 0; i<n; i++) 
            a[i] = nums[i] - avg; 
        
        int prefixSum=0;
        for (int i=0; i<k; i++) 
            prefixSum += a[i];
        
        if (prefixSum >= 0) 
            return true;
        
        int last = 0;
        for (int i=k;i<n;i++){
            
            prefixSum += a[i];
            last += a[i-k];
            
            if (last < 0) {
                prefixSum -= last;
                last = 0;
            }
            
            if (prefixSum >= 0) 
                return true;
        }
        
        return false;
    }
    
}
