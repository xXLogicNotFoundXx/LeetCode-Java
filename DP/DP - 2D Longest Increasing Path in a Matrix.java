/*
Medium - IMP
https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
Google10 Facebook10 Adobe4 Snapchat4 Microsoft3 ...

Given an m x n integers matrix, return the length of the longest increasing path in matrix.
From each cell, you can either move in four directions: left, right, up, or down.
You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).

*/
class Solution {
    // Time  O(MxN) Space  O(MxN)
    /*
        The idea we calculate from each position - how long we can form the path.
        Once we calculate we save that in DP. So once we calculate that we never calculate that again.
        We can use the trick where we create Integer array and if that is then we have to calculate that.
        If the array entry is not null we return that value.
    */
    public int longestIncreasingPath(int[][] matrix) {

        if(matrix==null || matrix[0].length==0)
            return 0;

        Integer dp[][] = new Integer[matrix.length][matrix[0].length];

        int maxPath =0;
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[0].length; j++){
                maxPath = Math.max(maxPath, getMaxPath(matrix,i, j, dp));
            }
        }

        return maxPath;
    }

    int getMaxPath(int[][] matrix, int i, int j, Integer[][] dp){

        if(dp[i][j]!=null)
            return dp[i][j];


        // calculate longest path
        int[][] directions = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};

        int max=0;
        for(int[] dir : directions){
            int newI = i + dir[0];
            int newJ = j + dir[1];

            if(newI<0 || newJ<0 || newI>=matrix.length || newJ>=matrix[0].length)
                continue;


            if(matrix[newI][newJ] > matrix[i][j])
                max = Math.max(max, getMaxPath(matrix, newI, newJ, dp) + 1);
        }

        dp[i][j] = Math.max(max,1);
        return dp[i][j];
    }

}
