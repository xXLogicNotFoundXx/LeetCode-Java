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
// Medium -
// https://leetcode.com/problems/jump-game-ii/
// Your goal is to reach the last index in the minimum number of jumps.
class Solution {
    public int jump(int[] A) {
        int jumps = 0, curEnd = 0, curFarthest = 0;
        for (int i = 0; i < A.length - 1; i++) {
            curFarthest = Math.max(curFarthest, i + A[i]);
            if (i == curEnd) {
                jumps++;
                curEnd = curFarthest;
            }
        }
        return jumps;
    }
}
