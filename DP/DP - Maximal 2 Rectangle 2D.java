/*
Hard
All Companies 2-3 times

https://leetcode.com/problems/maximal-rectangle/
*/
class Solution {
    /*
        If we had a histogram for each row it would become a probme of "Largest Rectangle in Histogram" for each row.
        https://leetcode.com/problems/largest-rectangle-in-histogram/

        So we build a histogram for each from previous rows and find maximum rectangle for each row and notedown the global max.
        O(N* ( M[buld histogram] + M[histogram max rectangle])) -> (N*M)
        Space: O(M)

        1 0 1 0 1
        1 0 1 1 1      2 0 2 1 2 => calculate histogram and note the max
        1 1 1 1 1   => 3 1 3 2 3 => calculate histogram and note the max
        1 0 0 1 0   => 4 0 0 3 0 => calculate histogram and note the max

        O( N*(M+M) )  M to build heights and another M to get histogram
        => O(N*M)
    */

    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) return 0;

        int maxarea = 0;
        int[] heights = new int[matrix[0].length];

        // for each ROW
        for(int i = 0; i < matrix.length; i++) {

            // Build out DP histogram and calulcate the the MAX rectangle
            for(int j = 0; j < matrix[0].length; j++) {
                // add the numbers and build an input to calculate histogram (heights)
                heights[j] = matrix[i][j] == '1' ? heights[j] + 1 : 0;
            }

            // update maxarea with the maximum area from this row's histogram
            maxarea = Math.max(maxarea, largestRectangleArea3(heights));
        }

        return maxarea;
    }

    public int largestRectangleArea3(int[] heights) {
        // check the solution of largest rectangle in histogram
    }
}
