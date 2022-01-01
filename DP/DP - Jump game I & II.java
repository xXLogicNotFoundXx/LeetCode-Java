/* Problem 1 -
Medium
Amazon16 Facebook6 Uber4

https://leetcode.com/problems/jump-game/
Given an array of non-negative integers, you are initially positioned at the first index of the array.
Each element in the array represents your maximum jump length at that position.
Determine if you are able to reach the last index.

Input: [2,3,1,1,4]
at 0th you can jump 1 step or 2 steps
at 1st you can jump 1,2,3 steps
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
*/

class Solution {
    public boolean canJump(int[] A) {
        int max = 0;
        for(int i=0;i<A.length;i++){
            if(max<i) {
                return false;
            }
            max = Math.max(A[i]+i,max);
        }
        return true;
    }
}

// Problem 2 -
// Medium - Amazon20 Adobe8 Microsoft3

// https://leetcode.com/problems/jump-game-ii/
// Your goal is to reach the last index in the minimum number of jumps.
// it is more like minimum number of fueling stops. However in this problem it is almost like ...
// The sall difference between this and gas stops problem is ... here you discard the fuel left in the tank. that is why youdo i+a[i]
// this small difference .. changes the whole approach and that is why we could come up with O(n) solution here and we didnt need PQ.

class Solution {
    public int jump(int[] A) {

        int jumps = 0;
        int weCanGoTill = 0;
        int farthestJumpSoFar = 0;

        for (int i = 0; i < A.length - 1; i++) {

            // calculate best jump so far.
            farthestJumpSoFar = Math.max(farthestJumpSoFar, i + A[i]);

            // if our current jump/end reached...
            // we have to select best jump from the previous jumps we discovered
            if (i == weCanGoTill) {
                jumps++; // we took one stop that will give best jump so far
                weCanGoTill = farthestJumpSoFar; // we adeed the best jump so far.
            }
        }
        return jumps;
    }
}
