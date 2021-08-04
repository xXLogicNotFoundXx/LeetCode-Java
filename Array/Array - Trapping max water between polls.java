/*
Medium VIMP 

https://leetcode.com/problems/container-with-most-water/
Input: [1,8,6,2,5,4,8,3,7]
Output: 49

It is almost simliar like rain drop but we are not calculating raindrops here ...
we are calculating where we want to fill up the tank with our tap 
why 49 because 1 8<------->7  we will be filling water at 7(min of height) * 7 slots (index of 8 - index of 7)
*/
class Solution {
    public int maxArea(int[] height) {
        if(height==null || height.length==0) return 0;
        int left = 0;
        int right = height.length-1;
        int max = 0;
        while(left<right){
            if(height[left]<height[right]){
                max = Math.max(max,(right-left)*height[left]);
                left++;
            }else{
                max = Math.max(max,(right-left)*height[right]);
                right--;
            }
        }
        return max;
    }
}
