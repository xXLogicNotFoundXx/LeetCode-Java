/*
Hard 
All companies 2-3 times at least. 

https://leetcode.com/problems/largest-rectangle-in-histogram/
Given an array of integers heights representing the histogram's bar height where the width of each bar is 1,
return the area of the largest rectangle in the histogram.

Input: heights = [2,1,5,6,2,3]
Output: 10
*/
class Solution {
    
    // O(N^2) - 91 / 96 test cases passed.
    // for each i - expand j= [i to n] and find the maxArea  
    public int largestRectangleArea1(int[] heights) {
        int maxArea = 0;
        int length = heights.length;
        //  you also have to calculate the single max for eg : [9,0,1,0]
        for (int i = 0; i < length; i++) {
            int minHeight = Integer.MAX_VALUE;
            for (int j = i; j < length; j++) {
                minHeight = Math.min(minHeight, heights[j]);
                maxArea = Math.max(maxArea, minHeight * (j - i + 1));
            }
        }
        return maxArea;
    }
    
     /*  We can build -> for each given height[i] -> how long I can go the left and how long I can go to the right 
         Once both are built ... in last loop for each i ->  (right[i]-left[i]) * height[i] and note down the max.
         How to build a left in o(n)?
            We can do : 
                if height[i-1] is geater than height[i]
                ... we can just simple put left[i] = left[i-1].. keep hoping 
         We can use same logic to build right in o(n).
         
         Time : O(N)  3PASS : we reuse results of previous calculations and "jump" through indices in quick manner. Amortized O(1)
                            // Amortized complexity is the total expense per operation, evaluated over a sequence of operations.
                            // The idea is to guarantee the total expense of the entire sequence, 
                            // while permitting individual operations to be much more expensive than the amortized cost.
         Space : O(N) -> left and right array 
    */
    public int largestRectangleArea(int[] heights) {
        
        // validate input
        if(heights == null || heights.length == 0) {
            return 0;
        }
        
        // init
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        int result = 0;
        
        /*
         We can build for given height[i] -> how long I can go the left and how long I go to the right 
         Once both are built ... in last loop for each i ->  (right[i]-left[i]) * height[i] and note down the max.
         How to build a left in o(n)? 
         We can do : 
            if height[i-1] is geater than height[i]
            ... we can just simple put left[i] = left[i-1].. keep hoping 
        */
        
        left[0] = 0; // same index for the start 
        for(int i = 1; i < n; i++) {
            
            left[i] = i;
            int currentLeft = i-1;
            
            while( currentLeft >=0  && heights[currentLeft] >= heights[i]) {
                
                currentLeft = left[currentLeft];
                left[i] = currentLeft;
            
                currentLeft = currentLeft-1; // now check next left
            }
        }
        
        right[n-1] = n-1; // same index for the start - same logic for right 
        for(int i = n-2; i >= 0; i--) {
            
            right[i] = i;
            int currentRight = i+1;
            
            while( currentRight <= n-1 && heights[currentRight] >= heights[i]) {
                
                currentRight = right[currentRight];
                right[i] = currentRight;
            
                currentRight = currentRight+1; // now check next right
            }
        }
      
        // build max area
        for(int i = 0; i < n; i++) {
            result = Math.max(result, (right[i]-left[i] + 1) * heights[i]);
        }
        
        return result;
    }
    
    // There is a stack solution that i think is very hard to come up with. 
  
}
