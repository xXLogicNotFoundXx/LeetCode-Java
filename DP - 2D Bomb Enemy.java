/*
361. Bomb Enemy
https://leetcode.com/problems/bomb-enemy/
Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), 
return the maximum enemies you can kill using one bomb.
The bomb kills all the enemies in the same row and column 
from the planted point until it hits the wall since the wall is too strong to be destroyed.
Note: You can only put the bomb at an empty cell


Idea is to count how many Enemies we can kill for empty cells of the matrix and return max. 
1 We could not do it inplace bcz 
    if we do column calucation then we wont have orginal values for row calulcations and vice versa. 
2 So definitely we need another matrix. 

So, we can traverse the matrix (top left to bottom right) 
1. count the enmies killed on each sell by adding left-row count and top-column count.
   From this you obviouly need two counts for each cell, rowKilled and columnKilled count
   So obviously this matrix is gonna have some object with two integers.
2. But as you can imagine we dont have the bottom and right count on each cell yet ( as we traversing down)
3. So, we have to traverse the matrix bottm up too and modify the count. 
After those two traversals we can get max count from the cell where '0' in original matrix.

Also, try to understand why we needed 4 integers in a class{int left,right,bottom,top;}
If we have sigle int, it wont even work for top-down traversal, bcz top cell would have row+col kill.
With two int rightRowKill & TopColKill would work for top-down traversal.
But coming bottom-up traversal, 
    if we add the count on rightRowKill in each cell it will mess up the calculations. 
    that rightRowKill has already considered all left enemies.
Hence we need class{int left,right,bottom,top;}

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
