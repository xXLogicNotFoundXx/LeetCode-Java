/*
https://leetcode.com/problems/shortest-unsorted-continuous-subarray/

Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order,
then the whole array will be sorted in ascending order, too.

You need to find the shortest such subarray and output its length.

Input: [2, 6, 4, 8, 10, 9, 15]
Output: 5
Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
*/
class Solution {
    
    // 2 PASS solution
    // Apparently we need to find min and max of when we find  nums[i-1] > nums[i]
    // but finding max and min is not enough 
    // i/p= [1,3,5,2,4]  will find 2 and 5 but we need the consideration of that 3 & 4 too 
    // we traverese nums and find min and max on condition nums[i-1] > nums[i]
    // we traverse and find
    // first occurrence of nums[i]>min ( i is gonna be start)
    // find last occurrence of nums[i]<max ( i is gonna be end index)
    public int findUnsortedSubarray1(int[] nums) {
        
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        for (int i=1; i<nums.length; i++) {
            if (nums[i-1] > nums[i]){
                min = Math.min(min, nums[i]);
                max = Math.max(max, nums[i-1]);
            }
        }
        
        // corner case when array is sorted 
        if(min==Integer.MAX_VALUE && max == Integer.MIN_VALUE)
            return 0;
        
        int left  = nums.length-1;
        int right = 0;
        
        for (int i=0; i<nums.length-1; i++){
            if(nums[i]>min)
                left=Math.min(left,i);
            if(nums[i]<max)
                right=Math.max(right,i);;
        }
        
        return right-left+1;
    }
    
    // This was my first solution (It works now but I had to change code couple times to get here)
    // Man I missed so many cases. I was trying to do it in one pass. without while loops in that 
    // Cases = [2,3,3,2,4] , [1,3,2,3,3] & [1,3,5,2,4]
    // This is in a way 2 Pass solution too. 
    public int findUnsortedSubarray(int[] nums) {
        
        int left=Integer.MAX_VALUE;
        int right=0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        for(int i=0;i<nums.length-1;i++){
            
            if(nums[i]>nums[i+1]){
                
                max = Math.max(max,nums[i]);
                min = Math.min(min,nums[i+1]);
                
                left = Math.min(left,i);
                right = Math.max(right,i+1);
                
                while(left>=1 && nums[left-1]>min) // this I added later for [2,3,3,2,4] and min var for [1,3,5,2,4]
                    left--;
                
                while(right<nums.length-1 && nums[right+1]<max) // this i added later  [1,3,2,3,3] and max var for [1,3,5,2,4]
                    right++;
            }
            
        }
        
        return left==Integer.MAX_VALUE ? 0 : right-left+1;
    }
}

/* 
1574. Shortest Subarray to be Removed to Make Array Sorted
https://leetcode.com/problems/shortest-subarray-to-be-removed-to-make-array-sorted/

Given an integer array arr, remove a subarray (can be empty) from arr such that the remaining elements in arr are non-decreasing.
A subarray is a contiguous subsequence of the array.
Return the length of the shortest subarray to remove.

Input: arr = [1,2,3,10,4,2,3,5]
Output: 3
Explanation: The shortest subarray we can remove is [10,4,2] of length 3. The remaining elements after that will be [1,2,3,3,5] which are sorted.
Another correct solution is to remove the subarray [3,10,4].

idea is 
  go left->right and find ith position when it is non-incremental 
  go right->left and find jth position where it is non-decremental 
  0----i<This is the range needs to be removed>j---(N-1)
  [1,2,3,10,4,2,3,5]
   ------>i   j<---
  by this logic we need to remove only 4 so far but we need to consider 10 and 2 as well 
  But it is possible if we may need to consider more numbers on left & right 
  which will form bigger non-decreasing subarray in this case 10 & 2
  
*/


class Solution {
    public int findLengthOfShortestSubarray(int[] nums) {
        int i=0, j=nums.length-1;
        
        while(i<nums.length-1 && nums[i]<=nums[i+1]){
            i++;
        }
        
        if(i==nums.length-1) return 0;
        
        while(j>0 && nums[j-1]<=nums[j]){
            j--;
        }
        
        // int ans = nums.length-1; // max_possible 
        // [2,2,2,1,1,1] in this case its good to remove either left part or right part completely 
        // int ans = Math.min(i+1,nums.length-j); [5,4,3,2,1] o/p 1 expected 4 
        int ans = Math.min(nums.length - i - 1, j);
        int left=0;
        int right=j;
        
        while(left<=i && right<=nums.length-1){
            
            if(nums[left]<=nums[right]){
                
                ans=Math.min(ans, right-left-1);
                left++; 
            
            }else{
                right++;
            }
        }
        
        return ans;
    }
}
