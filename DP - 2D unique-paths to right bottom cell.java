/*
https://leetcode.com/problems/unique-paths/
Problem 1: 
A robot is located at the top-left corner of a m x n grid.
The robot can only move either down or right at any point in time. 
The robot is trying to reach the bottom-right corner of the grid.
How many possible unique paths are there?
Input: m = 3, n = 2
Output: 3
1. Right -> Right -> Down
2. Right -> Down -> Right
3. Down -> Right -> Right

1 1 1  1  1
1 2 3  4  5
1 3 6  10 15
1 4 10 20 35

*/
class Solution {
    public int uniquePaths(int m, int n) {
        if(m<=0 || n<=0)
            return 1;
        
        int[][] dp = new int[n][m];
        for(int i=0; i<m; i++)
            dp[0][i]=1;
        
        for(int i=0; i<n; i++)
            dp[i][0]=1;
        
        for(int i=1; i<n; i++){
            for(int j=1; j<m; j++){
                dp[i][j]= dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[n-1][m-1];
    }
}

/*
https://leetcode.com/problems/unique-paths-ii/
Problem 2: 
if some obstacles are added to the grids. How many unique paths would there be?
Input:
[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
Output: 2
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right
*/
 public class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        obstacleGrid[0][0] = (obstacleGrid[0][0]==1)? 0 : 1;

        for(int i = 1;i<m;i++)
            obstacleGrid[i][0] = (obstacleGrid[i][0]==1)? 0 : obstacleGrid[i-1][0];

        for(int j = 1;j<n;j++)
            obstacleGrid[0][j] = (obstacleGrid[0][j]==1)? 0 : obstacleGrid[0][j-1];

        for(int i = 1;i<m;i++){
            for(int j =1;j<n;j++){
                obstacleGrid[i][j] = (obstacleGrid[i][j]==1)? 0 : obstacleGrid[i-1][j]+obstacleGrid[i][j-1];
            }
        }
        return obstacleGrid[m-1][n-1];
    }
}
