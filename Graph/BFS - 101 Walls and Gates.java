/*
Medium - IMP
All 3-4 times.

https://leetcode.com/problems/walls-and-gates/

You are given a m x n 2D grid initialized with these three possible values.
-1 -> A wall or an obstacle.
0  -> A gate.
2147483647 -> Infinity means an empty room.
We use the value 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.


Given the 2D grid:

INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF

After running your function, the 2D grid should be:

  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4


 The idea is put all indices of '0' value in a queue.
 Start from there and then add neighbouring indices while updating thier distance. (dont process blocks)
 Eventually queue will be empty and we have the ans.
 Simple.

 First i thought i would need Priority Queue but it is not needed.
 Code could be better here.

*/
public class Solution {
    public void wallsAndGates(int[][] rooms) {
        if (rooms.length == 0 || rooms[0].length == 0)
            return;

        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) {
                   queue.add(new int[]{i, j});
                }
            }
        }
                                      // left , right, up and down 
        int[][] directions = new int[][]{{0,-1},{0,1},{1,0},{-1,0}};

        while (!queue.isEmpty()) {

            int[] cell = queue.poll();
            int val = rooms[cell[0]][cell[1]];

            for(int []dir : directions){
                int row = cell[0] + dir[0];
                int col = cell[1] + dir[1];


                if(row<0 || col <0 || row == rooms.length || col == rooms[0].length)
                    continue;

                if(rooms[row][col] == Integer.MAX_VALUE){
                    rooms[row][col] = val+1;
                    queue.add(new int[]{row, col});
                }
            }
        }
    }
}
