// https://leetcode.com/problems/maximal-rectangle/
class Solution {
    /*
        If we had a histogram for each row it would become a probme of "Largest Rectangle in Histogram" for each row. 
        So we build a histogram for each from previous rows and find maximum rectangle for each row and notedown the global max. 
        
        O(N* ( M[buld histogram] + M[histogram max rectangle])) -> (N*M)
        Space O(M)
    */
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) return 0;
        
        int maxarea = 0;
        int[] dp = new int[matrix[0].length];

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {

                // update the state of this row's histogram using the last row's histogram
                // by keeping track of the number of consecutive ones

                dp[j] = matrix[i][j] == '1' ? dp[j] + 1 : 0;
            }
            // update maxarea with the maximum area from this row's histogram
            maxarea = Math.max(maxarea, largestRectangleArea3(dp));
        } 
        
        return maxarea;
    }
    
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
