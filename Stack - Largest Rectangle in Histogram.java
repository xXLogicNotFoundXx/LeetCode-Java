/*
https://leetcode.com/problems/largest-rectangle-in-histogram/
84. Largest Rectangle in Histogram
Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.
Example:

Input: [2,1,5,6,2,3]
Output: 10

[5,6] can form rectangle of 5,5 
*/
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
class Solution {
    public int largestRectangleArea(int[] heights) {
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
