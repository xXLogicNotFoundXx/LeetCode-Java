/*
https://leetcode.com/problems/maximum-area-of-a-piece-of-cake-after-horizontal-and-vertical-cuts/
Given a rectangular cake with height h and width w, and two arrays of integers horizontalCuts and verticalCuts 
where horizontalCuts[i] is the distance from the top of the rectangular cake to the ith horizontal cut and similarly, 
verticalCuts[j] is the distance from the left of the rectangular cake to the jth vertical cut.

Return the maximum area of a piece of cake after you cut at each horizontal and vertical position provided in the arrays horizontalCuts and verticalCuts. 
Since the answer can be a huge number,return modulo 10^9+7.

*/

/*
Man this is kida trick question. 
If you think divide and conquer and think about only horizontal lines what is the ans?
    Max consecutive difference will be the ans. 
Same for Vertical lines, if you find out max consecutive difference of the cut you have vertical cut ans. 
Final ans is maxHorizontal * maxVertical cut.

*/
class Solution {
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        
        long maxHorizontalCut = Math.max(0, horizontalCuts[0]); 
        long maxVerticalCut = Math.max(0, verticalCuts[0]);
        
        for(int i=0; i<horizontalCuts.length-1; i++){
             maxHorizontalCut = Math.max( maxHorizontalCut,  horizontalCuts[i+1] -  horizontalCuts[i]);
        }
        maxHorizontalCut = Math.max( maxHorizontalCut, h - horizontalCuts[horizontalCuts.length-1]);
        
        
        for(int i=0; i<verticalCuts.length-1; i++){
            maxVerticalCut = Math.max( maxVerticalCut,  verticalCuts[i+1] -  verticalCuts[i]);
        }
        maxVerticalCut = Math.max( maxVerticalCut,  w - verticalCuts[verticalCuts.length-1] );
        
        return (int) ( (maxHorizontalCut * maxVerticalCut) % 1000000007);
    }
}
