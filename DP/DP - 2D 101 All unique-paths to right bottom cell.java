/*

Medium 
All companies 5-6 times. 

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

Time : O(MxN)
Space : O(MxN)
*/
class Solution {
    public int uniquePaths(int m, int n) {
        if(m<=0 || n<=0)
            return 1;
        
        int[][] dp = new int[n][m];
        
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                
                if(i==0 || j==0){
                    dp[i][j] = 1;
                    continue;
                }
                
                dp[i][j]= dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[n-1][m-1];
    }
}

/*
Medium 
All big ones at least 2-3 times 

https://leetcode.com/problems/unique-paths-ii/

If some obstacles are added to the grids. How many unique paths would there be?
Input:
[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
Output: 2 (two paths around obstacle)
*/

/* We do need first loops. Incorporating logic in one i,j loop is not possible (lots of ifs and buts) */

public int uniquePathsWithObstacles(int[][] obstacleGrid) {
	int m = obstacleGrid.length, n = obstacleGrid[0].length;
	int[][] path = new int[m][n];

	for (int i = 0; i < m; i++) {
		if (obstacleGrid[i][0] == 1)  {
			path[i][0] = 0;
			//on the first column, if there is an obstacle, the rest are blocked. 
			//no need to continue.
			break;  
		} else
			path[i][0] = 1;
	}
	
	for (int j = 0; j < n; j++) {
		if (obstacleGrid[0][j] == 1)  {
			path[0][j] = 0;
			//First row, once obstacle found, the rest are blocked.
			break; 
		} else
			path[0][j] = 1;
	}
	
	for (int i = 1; i < m; i++) {
		for (int j = 1; j < n; j++) {
			if (obstacleGrid[i][j] == 1) 
				path[i][j] = 0;
			else
				path[i][j] = path[i-1][j] + path[i][j-1];
		}
	}
	return path[m-1][n-1];
}
