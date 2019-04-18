/* Problem 1 - https://leetcode.com/problems/jump-game/
Given an array of non-negative integers, you are initially positioned at the first index of the array.
Each element in the array represents your maximum jump length at that position.
Determine if you are able to reach the last index.

Input: [2,3,1,1,4]
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
/* My solution I consider the breaking point will be at zero and we have to make sure we pass that zero. 
class Solution {
    public boolean canJump(int[] nums) {
        if(nums==null || nums.length==0)
            return true;
        int max=0;
        for(int i=0;i<nums.length-1;i++){ // last element we dont want to consider 
            if(nums[i]==0){
               if(max <= i) // we need to reach i+1
                   return false; 
            } else {
               nums[i] = i+nums[i]; 
            }
            max = Math.max(max,nums[i]); 
        }
        return true;
    }
}*/

// Problem 2 -  https://leetcode.com/problems/jump-game-ii/
// Your goal is to reach the last index in the minimum number of jumps.
class Solution {
    public int jump(int[] nums) {
        int max1 = 0, max2 =0;
        int step=0;
        for(int i=0;i<nums.length;i++){
            int dist = nums[i] + i;
            if(max1<i){
                step++;
                max1=max2;
            }
            max2 = Math.max(max2,dist);
        }
        return step;
    }
}

