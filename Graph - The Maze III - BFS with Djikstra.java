/*
https://leetcode.com/problems/the-maze-iii/
499. The Maze III
Find out how the ball could drop into the hole by moving the shortest distance. 
Output the moving directions by using 'u', 'd', 'l' and 'r'. Since there could be several different shortest ways, 
you should output the lexicographically smallest way. If the ball cannot reach the hole, output "impossible".

Variations of this problem is :
https://leetcode.com/problems/the-maze-ii/    (return true/false if we can hit the hole)
https://leetcode.com/problems/the-maze-ii/    (return minimum distance to hit the hole or -1)
https://leetcode.com/problems/the-maze-iii/   (return minimum distance but if there are
                                               multiple paths with same distsnace return lexicographical order) 
                                               "lul" vs "ul" => 'l' < 'u'. So the output is "lul".
                                               
 Time complexity  : O(mn*log(mn)) -  Complete traversal of maze will be done in the worst case giving a factor of mn.  
                    heapifying(O(log(mn))). You can consider the Path comparison O(mn* (log(mn) + K)) K is string comparisons in PQ. 
 Space complexity : O(mn).  visited array of size m*n is used and queue size can grow upto m∗n in worst case.
*/
class Solution {
    
    // BFS with Djikstra so we will hit the hole at the minimum distance. 
    // We also explore path with lexicographically ( d,l,r,u) order if the distance is same. 
    // PriorityQueue polls out the Coordinate with the minimum distance, if there are two with same distance, 
    // we compare their lexicographical order, by this way, we can ensure that we get the lexicographically smallest way in the end.
    class PointPath{
        int x,y,dist;
        String path;
        PointPath(int x, int y, int dist, String path){
            this.x=x;
            this.y=y;
            this.dist=dist;
            this.path=path;
        }
    }
    
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        
        boolean [][]visited = new boolean[maze.length][maze[0].length];
        PriorityQueue<PointPath> pq = new PriorityQueue<PointPath>(new Comparator<PointPath>(){
            public int compare(PointPath a, PointPath b){
                return a.dist == b.dist ? a.path.compareTo(b.path) : a.dist - b.dist;
            }
        });
        
        String[] dirStr =  {"d","l","r","u"};
        int[][] dirs = {{1,0},{0,-1},{0,1},{-1,0}};
        
        pq.offer(new PointPath(ball[0],ball[1],0,"")); 
        while(!pq.isEmpty()){
            
            PointPath cur = pq.poll();
            
            if(visited[cur.x][cur.y] == true) 
                continue;
            visited[cur.x][cur.y] = true;
            
            // it is very important to not mark 
            if(cur.x==hole[0] && cur.y==hole[1]){
                return cur.path;
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
                    
                    // if we hit the HOLE we need to put this in normal Queue 
                    if(nextX==hole[0] && nextY==hole[1]){
                        break; 
                    }
                }
                
                // we hit the wall/Hole
                if(visited[nextX][nextY] != true){
                    pq.offer(new PointPath(nextX,nextY,cur.dist+steps,cur.path+dirStr[i]));
                }
            }
        }
        return "impossible";
    }
}
