
/* 
HARD - VIMP 

https://leetcode.com/problems/trapping-rain-water/
Given n non-negative integers representing an elevation compute how much water it is able to trap after raining.
 Input: [0, 1,  0, 2,  1,  0,  1, 3, 2,  1, 2, 1]
        [0, 0, +1, 0, +1, +2, +1, 0, 0, +1, 0, 0]    = 6     
 Output: 6
 
 Keep track of the maximum height from both forward directions backward directions, call them leftmax and rightmax.
 leftmax = at oth and right max at nth height
 if rightmax is lower than leftmax start filling up from right to left 
        fill in the block = Math.max(rightMax,height[j]) - height[j] 
 else start filling up from left to right
        fill = Math.max(leftMax,height[i]) - height[i]
*/
class Solution {
    // Time O(n) & Space O(n) this is nice! 
    public int trap(int[] height) {
        if(height==null || height.length==0)
            return 0;
        
        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];
        
        int max = height[0];
        for(int i=0; i<height.length; i++){
            max = max < height[i] ? height[i] : max;
            leftMax[i] = max;
        }
        
        max = height[height.length-1];
        for(int i=height.length-1; i>=0; i--){
            max = max < height[i] ? height[i] : max;
            rightMax[i] = max;
        }
        
        int sum=0;
        for(int i=0; i<height.length; i++){
            sum += Math.min(leftMax[i],rightMax[i]) - height[i];
        }
        
        return sum;
    }
    
 
 // O(N) time and O(1) space 
    public int trap1(int[] height) {
        if(height == null || height.length <= 2) 
            return 0;
        int leftMax = height[0], rightMax = height[height.length-1];
        int i=1, j = height.length-2;
        int fill = 0;
        while(i<=j){
            if(leftMax<rightMax){
                leftMax = Math.max(leftMax,height[i]); // doing this will guarantee non negative number in next step 
                fill+= leftMax-height[i];                 
                i++;
            }else{
                rightMax = Math.max(rightMax,height[j]); // doing this will guarantee non negative number in next step 
                fill+= rightMax-height[j];                
                j--;
            }
        }
        return fill;
    }
}
