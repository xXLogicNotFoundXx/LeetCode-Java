/*
https://leetcode.com/problems/split-array-largest-sum/

Given an array which consists of non-negative integers and an integer m,
you can split the array into m non-empty continuous subarrays. 
Write an algorithm to minimize the largest sum among these m subarrays.
*/
// This is almost like the problem where we try to find number of days for shipping all packages 
// l = max number in an array;  (this is the minimum largest_sum you gonna get [m>=nums.length]) 
// r = sum of all array numbers; ( this is the maximum largest_sum you gonna get [when m=1]) 
// mid = (l + r) / 2; see if we can divide it into m subarrays 
// if we can do  r = mid-1;    // we need to find minimum 
// if not do     l = mid+1; 
// Time complexity : O(n∗log(sumofarray)).
class Solution {
    public int splitArray(int[] nums, int m) {
        
        long l=0,r=0;
        for(int num : nums){
            l = Math.max(l,num);
            r +=num;
        }
        
        if(m==1)
            return (int) r;
        if(m>=nums.length)
            return (int) l;
        
        while(l<=r){
            long mid = l+ (r-l)/2;
            
            if(valid(nums,mid,m))
                r=mid-1; // target could be too big
            else
                l=mid+1;
              
        }
        return (int) l;
    }
    
    boolean valid(int []nums, long target, int m){
        int sum=0,count=0;
        for(int num : nums){
            sum+=num;
            if(sum > target){
                sum=num;
                count++;
            } 
        }
        count++;
        return count<=m; // there could be less than m partition if target is too big 
    }
}
