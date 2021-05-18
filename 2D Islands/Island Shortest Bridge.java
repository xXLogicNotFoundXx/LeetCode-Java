/*
https://leetcode.com/problems/shortest-bridge/

In a given 2D binary array A, there are two islands. 
(An island is a 4-directionally connected group of 1s not connected to any other 1s.)
Now, we may change 0s to 1s so as to connect the two islands together to form 1 island.
Return the smallest number of 0s that must be flipped.  (It is guaranteed that the answer is at least 1.)

1. Find one Island and mark that whole Island as -1 
2. Find second Island and Add ALL positions of the  island in the queue to process for BFS. 
    These all positions are our level one nodes and visited. 
3. Level by level mark positions as visited (Very IMP) 
4. return level 

Time O(M*N) - DFS could traverse all ... BFS could traverse all 
Space O(M*N) - Queue could get almost all M*N positions 
*/
class Solution {
    public int shortestBridge(int[][] A) {
        int m = A.length, n = A[0].length;
        
        int[][] dir = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        Queue<int[]> queue = new LinkedList<>();
        
        boolean foundOneIsland = false;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                
                if(foundOneIsland && A[i][j] == 1) {
                    // Found second island add this position for the queue 
                    queue.add(new int[]{i,j});
                    // break;
                    // NOTE we need all the island position to start with 
                    // with break, you consider only one position and that could be farthest in second island 
                    // so we need to consider all the position of second Island
                }
                
                // Marks this island as -1
                if(A[i][j] == 1 && !foundOneIsland) {
                    dfsMarkIslandMinusOne(A, i, j);
                    foundOneIsland = true;
                }
                
            }
        }

        // now level by level look for -1 
        int level = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                
                int[] pos = queue.poll();
                
                for(int d[] : dir){
                    int x = pos[0] + d[0];
                    int y = pos[1] + d[1];
                    
                    if(x < 0 || x >= m || y < 0 || y >= n) 
                        continue;
                    
                    // found the island 
                    if(A[x][y] == -1) 
                        return level;
                    
                    // visited
                    if(A[x][y] == 1) 
                        continue;
                    
                    if(A[x][y] == 0) {
                        A[x][y] = 1;    // visited 
                        queue.add(new int[]{x, y});
                    }
                }
            }
            level++;
        }
        
        return -1;
    }
    
    public void dfsMarkIslandMinusOne(int[][] A, int i, int j) {
        if(i<0 || i == A.length ||  j<0 || j == A[0].length ){
            return;
        }
        
        if(A[i][j]==1){
            A[i][j] = -1;   
            dfsMarkIslandMinusOne(A, i+1, j);
            dfsMarkIslandMinusOne(A, i-1, j);
            dfsMarkIslandMinusOne(A, i, j+1);
            dfsMarkIslandMinusOne(A, i, j-1);
        }
    }
}
