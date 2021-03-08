/*
https://leetcode.com/problems/largest-rectangle-in-histogram/

Also look : https://leetcode.com/problems/maximal-square/
*/
class Solution {
    
    // O(N^2) - 91 / 96 test cases passed.
    public int largestRectangleArea1(int[] heights) {
        int maxArea = 0;
        int length = heights.length;
        for (int i = 0; i < length; i++) {
            int minHeight = Integer.MAX_VALUE;
            for (int j = i; j < length; j++) {
                minHeight = Math.min(minHeight, heights[j]);
                maxArea = Math.max(maxArea, minHeight * (j - i + 1));
            }
        }
        return maxArea;
    }
    
    // This can be 
    /*
        Scan from left to right to compute left[], which represents the left most boundary of current height.
        Scan from right to left to compute right[], which represents the right most boundary of current height.
        Scan from left to right again to compute rectangle area based on the height of that each position.
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
        
        // build left
        left[0] = 0;
        for(int i = 1; i < n; i++) {
            int currentLeft = i-1;
            while(currentLeft >= 0 && heights[currentLeft] >= heights[i]) {
                currentLeft = left[currentLeft]-1;
            }
                
            left[i] = currentLeft+1;
        }
        
        // build right
        right[n-1] = n-1;
        for(int i = n-2; i >= 0; i--) {
            int currentRight = i+1;
            while(currentRight < n && heights[i] <= heights[currentRight]) {
                currentRight = right[currentRight]+1;
            }
                
            right[i] = currentRight-1;
        }
        
        // compare height
        for(int i = 0; i < n; i++) {
            result = Math.max(result, (right[i]-left[i]+1)*heights[i]);
        }
        
        // return
        return result;
    }
    
    /*
    this will help you to understand stack solution:
    https://www.youtube.com/watch?v=RVIh0snn4Qc

    Conside input : [1,2,3,4,5,3,3,2]

    Idea is :
    Keep putting indexes on the stack until height is in increasing. 
    Once you find the currentHeight less than height[peek] 
    then work on the stack one by one untill we find the same height or less height on the stack than currentHeight
        work on stack is calculating area for these indices on stack.
    */
    public int largestRectangleArea3(int[] heights) {
        int max = 0;
        int i = 0;
        Stack<Integer> stack = new Stack<>();

        while (i < heights.length) {

            if (stack.isEmpty() || heights[stack.peek()] <= heights[i]) {
                stack.push(i);
                i++;
            } else {
                int p = stack.pop();
                int h = heights[p];
                int w = stack.isEmpty() ? i /*Area 0-i'th index */ : i - stack.peek() - 1;
                max = Math.max(h * w, max);
            }
        }

        while (!stack.isEmpty()) {
            int p = stack.pop();
            int h = heights[p];
            int w = stack.isEmpty() ? i : i - stack.peek() - 1;
            max = Math.max(h * w, max);
        }

        return max;
    }
}
