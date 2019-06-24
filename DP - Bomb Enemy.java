/*
361. Bomb Enemy
https://leetcode.com/problems/bomb-enemy/
Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), 
return the maximum enemies you can kill using one bomb.
The bomb kills all the enemies in the same row and column 
from the planted point until it hits the wall since the wall is too strong to be destroyed.
Note: You can only put the bomb at an empty cell

The problem here is you can calculate column addition but 
row addition becomes very tricky bcz the resulting matrix has already enemy count 
even if you create a new matrix for row calculation .. there is gonna be averlap when you try to add 
row and column addition ... 
let this sink in 
// bc testcase []  ["E"] 
(top, bottom, left, right) be max enemy to kill at each direction if bomb at (i, j)
Therefore we need to start build dp array from both top left and bottom right corner
Once have we have # of enemy at 4 direction, we can find max # of enemy
*/
class Solution {
    class Count{
        int left,right,bottom,top; 
    }
    
    public int maxKilledEnemies(char[][] grid) {
        if(grid.length==0)
            return 0;
        
        int m = grid.length;
        int n = grid[0].length;
        
        Count[][] countDP = new Count[m][n];
        // top left to bottom right 
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                
                countDP[i][j] = new Count();
                Count dp = countDP[i][j];
                
                if(grid[i][j]=='W')
                    continue;
                
                if(grid[i][j]=='E'){
                    dp.left++;
                    dp.top++;
                }
                
                dp.left += j==0 ? 0 : countDP[i][j-1].left;
                dp.top  += i==0 ? 0 : countDP[i-1][j].top;
            }
        }
        
        int ans = 0;
        // bottom right to top left 
        for(int i=m-1;i>=0;i--){
            for(int j=n-1;j>=0;j--){
                
                Count dp = countDP[i][j];
                if(grid[i][j]=='W')
                    continue;
                
                if(grid[i][j]=='E'){
                    dp.right++;
                    dp.bottom++;
                }
                
                dp.right  += j==n-1 ? 0 : countDP[i][j+1].right;
                dp.bottom += i==m-1 ? 0 : countDP[i+1][j].bottom;
                                                       
                if(grid[i][j]=='0'){
                    ans = Math.max(ans, dp.left + dp.right + dp.top + dp.bottom );
                }                                     
            }
        }
        return ans;
    }
}
