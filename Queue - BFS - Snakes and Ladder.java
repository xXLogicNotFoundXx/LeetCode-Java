/*
https://leetcode.com/problems/snakes-and-ladders/
909. Snakes and Ladders 
Input: 
[-1,-1,-1,-1,-1,-1]
[-1,-1,-1,-1,-1,-1]
[-1,-1,-1,-1,-1,-1]
[-1,35,-1,-1,13,-1]
[-1,-1,-1,-1,-1,-1]
[-1,15,-1,-1,-1,-1]
Output: 4
Explanation: 
At the beginning, you start at square 1 [at row 5, column 0].
You decide to move to square 2, and must take the ladder to square 15.
You then decide to move to square 17 (row 3, column 5), and must take the snake to square 13.
You then decide to move to square 14, and must take the ladder to square 35.
You then decide to move to square 36, ending the game.
It can be shown that you need at least 4 moves to reach the N*N-th square, so the answer is 4.
*/

class Solution {
    // O(m^2)
    public int snakesAndLadders(int[][] board) {
        int m = board[0].length;
        if(m==0) return 0;
        int layer=0, ind =0;
        int []pos = new int [m*m];
        for(int i=m-1; i>=0;i--,layer++){
            for(int j=0;j<m;j++){
                pos[ind++] = board[i][j];
            }
            if(layer%2==1)
                reverse(pos,layer, m);
        }
        boolean []visited = new boolean[m*m];
        visited[0] = true; 
        Queue<Integer> q = new LinkedList<>();
        int steps = 0; 
        // Start could be direct ladder 
        int start = pos[0] > -1 ? pos[0] - 1 : 0;
        visited[start] = true; 
        q.add(start);
        
        while(!q.isEmpty()){
            int size = q.size();
            while(size-- > 0){
                int p = q.poll();
                if(p==m*m-1)
                    return steps;
                
                for(int i=p+1; i<=Math.min(p+6,m*m-1); i++){
                    int dest = pos[i] > -1 ? pos[i] - 1 : i;
                    if (!visited[dest]) {
                        visited[dest] = true;
                        q.offer(dest);
                    }
                }
            }
            steps++;
        }
        return -1;
    }
    
    void reverse(int []pos, int layer,int m){
        int first = layer*m;
        int last = first+m-1;
        for(;first<last;first++,last--){
            int temp = pos[first];
            pos[first] = pos[last];
            pos[last] = temp;
        }
    }
}
