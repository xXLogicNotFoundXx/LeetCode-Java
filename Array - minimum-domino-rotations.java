/*
https://leetcode.com/problems/minimum-domino-rotations-for-equal-row/
1007. Minimum Domino Rotations For Equal Row
Given A and B you can swap(rotate) A[i] & B[i], your goal is to make A OR B having all same values.
How many minimum swaps are needed?
*/
class Solution {
    public int minDominoRotations(int[] A, int[] B) {
        if(A==null || B==null || A.length!=B.length)
           return -1;
        int ans = minDominoRotations(A[0],A,B);
        if(ans==-1){
            ans = minDominoRotations(B[0],A,B);
        }
        return ans;
    }
    
    int minDominoRotations(int val, int[] A, int[] B){
        int rot_A=0, rot_B=0;
        for(int i=0;i<A.length;i++){
            if(A[i]!=val && B[i]!=val)
                return -1;
            if(A[i]==val && B[i]!=val)
                rot_A++;
            if(A[i]!=val && B[i]==val)
                rot_B++;
        }
        return Math.min(rot_A,rot_B);
    }
}
