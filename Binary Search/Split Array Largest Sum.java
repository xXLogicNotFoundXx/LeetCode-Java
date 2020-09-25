/*
https://leetcode.com/problems/split-array-largest-sum/

Given an array which consists of non-negative integers and an integer m,
you can split the array into m non-empty continuous subarrays. 
Write an algorithm to minimize the largest sum among these m subarrays.
*/
// Time complexity : O(n∗log(sumofarray)).
// This is almost like the problem where we try to find weight capacity to ship all packages in D days 
// Instead of finding weight we gonna find mininum largets sum that can divide the array in m non-empty continuous parts. 
// left = maximum number in an array; 
// right = sum of all numbers in the array;

class Solution {
    public int splitArray(int[] nums, int m) {
        
        long left=0,right=0;
        for(int num : nums){
            left  =  Math.max(left,num);
            right += num;
        }
        
        if(m>=nums.length)
            return (int) left;
        
        if(m==1)
            return (int) right;
        
        while(left<right){
            long mid = left + (right-left)/2; // to avoid overflow
            
            int parts = countDivisions(nums,mid);
            
            /*
                    DON'T Do this
            if(parts==m)
                return mid;
            
            This will give you wrong answer:
            because there will be some x sum which will give the same partition as m.
            However, this does not mean the x sum is absolute minumum sum required to partition array in m subarrays.
            so weep moving left=mid+1 keep the right=mid even if we find the x sum to partition array in m subarrays.
            In the end -  left and right will be equal.
            */
            if(parts<=m)
                right=mid;
            else
                left=mid+1;
              
        }
        
        return (int) left;
    }
    
    int countDivisions(int []nums, long target){
        long total=0;
        int count=0;
        
        for(int num : nums){
            total+=num;
            if(total > target){
                total=num;
                count++;
            } 
        }
        count++;
        return count;
    }
}
