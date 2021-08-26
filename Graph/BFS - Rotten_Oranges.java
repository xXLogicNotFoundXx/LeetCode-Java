/* 
Medium 
All companies 3-4 times. Amzn-26 

https://leetcode.com/problems/rotting-oranges/

In a given grid, each cell can have one of three values:
the value 0 representing an empty cell;
the value 1 representing a fresh orange;
the value 2 representing a rotten orange.

Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.
Return the minimum number of minutes that must elapse until no cell has a fresh orange.  
If this is impossible, return -1 instead.

 //Put the coordinates of all rotten oranges in queue and count the number of fresh oranges
 // while queue isempty keep rotting the oranges and keep track of minutes from queue.size()
 */

class Solution {
    public int orangesRotting(int[][] grid) {
        
        Queue<int[]> queue = new LinkedList<>();
        int freshCount = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        
        //Put the coordinates of all rotten oranges in queue
        //count the number of fresh oranges
        for(int i=0;i<rows;i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) freshCount++;
                if (grid[i][j] == 2) {
                    queue.add(new int[]{i, j});
                }
            }
        }
        
        if(freshCount == 0) 
            return 0;
        
        int[][] directions = new int[][]{{-1,0}, {1,0}, {0,-1}, {0,1}};
        
        
        int minutes = 0;
        
        while(!queue.isEmpty()){
            
            int size = queue.size();
            
            for(int i=0; i<size; i++){
                int []rotOrg = queue.poll();
                
                for(int[] dir : directions){
                    int x = dir[0] + rotOrg[0];
                    int y = dir[1] + rotOrg[1];

                    if(x<0 || y<0 || x>=rows || y>=cols || grid[x][y] !=1) 
                        continue;

                    grid[x][y] = 2;
                    freshCount--;
                    queue.add(new int[]{x, y});
                }
            }
            minutes++;
        }
        
        return freshCount ==0 ? minutes-1 : -1;
    }
}
