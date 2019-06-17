/*
https://leetcode.com/problems/the-maze-ii/
505. The Maze II
The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. 
You may assume that the borders of the maze are all walls. 
The start and destination coordinates are represented by row and column indexes.
The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall.
find the shortest distance for the ball to stop at the destination.

Two variations of this problem is :
https://leetcode.com/problems/the-maze/       (return true/false if we can hit the hole)
https://leetcode.com/problems/the-maze-ii/    (return minimum distance to hit the hole or -1)
https://leetcode.com/problems/the-maze-iii/   (return minimum distance but if there are
                                               multiple paths with same min distance return lexicographical ordered) 
                                               "lul" vs "ul" => 'l' < 'u'. So the output is "lul".
                                               
 Time complexity  : O(mn*log(mn)) -  Complete traversal of maze will be done in the worst case giving a factor of mn.  heapifying(O(log(mn)))   
 Space complexity : O(mn).  visited array of size m*n is used and queue size can grow upto m∗n in worst case.
*/
class Solution {
    
    // BFS with Djikstra so we will hit the hole at the minimum distance. 
    class PointPath{
        int x,y,dist;
        String path;
        PointPath(int x, int y, int dist){
            this.x=x;
            this.y=y;
            this.dist=dist;
        }
    }
    
    public int shortestDistance(int[][] maze, int[] ball, int[] hole) {
        boolean [][]visited = new boolean[maze.length][maze[0].length];
        
        // PriorityQueue ensures that we hit Hole with minimum distance first.   
        PriorityQueue<PointPath> pq = new PriorityQueue<PointPath>(new Comparator<PointPath>(){
            public int compare(PointPath a, PointPath b){
                return a.dist - b.dist;
            }
        });
        
        int[][] dirs = {{1,0},{0,-1},{0,1},{-1,0}};
        pq.offer(new PointPath(ball[0],ball[1],0)); 
        
        while(!pq.isEmpty()){
            
            PointPath cur = pq.poll();
            if(visited[cur.x][cur.y] == true) 
                continue;
            
            visited[cur.x][cur.y] = true;
            if(cur.x==hole[0] && cur.y==hole[1]){
                return cur.dist;
            }
            
            for (int i = 0; i < dirs.length; i++) {
                int nextX = cur.x;
                int nextY = cur.y;
                int steps=0;
                
                // continue in this direction 
                while(nextX + dirs[i][0] >=0 && 
                      nextX + dirs[i][0] < maze.length &&
                      nextY + dirs[i][1] >=0 && 
                      nextY + dirs[i][1] < maze[0].length &&
                      maze[nextX + dirs[i][0]] [nextY + dirs[i][1]]==0){
                    
                    nextX = nextX + dirs[i][0];
                    nextY = nextY + dirs[i][1];
                    steps++;
                    
                    /*
                    if problem allows to have hole in the middle (not at the wall) 
                    But this problem doesnt allow Hole to be in the middle
                    if(nextX==hole[0] && nextY==hole[1]){ // if we hit the hole add it to PQ
                        break; 
                    }*/
                }
                
                // we hit the wall/Hole
                if(visited[nextX][nextY] != true){
                    pq.offer(new PointPath(nextX,nextY,cur.dist+steps));
                }
            }
        }
        return -1;                                
    }
}
