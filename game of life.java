class Solution {
    public void gameOfLife(int[][] board) {
        int[][] board1 = new int[board.length][board[0].length];
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                board1[i][j] = board[i][j];
            }
        }
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                int live = getNeighbors(board1,i,j);
                if(live<2)
                    board[i][j] = 0;
                else if(live>3)
                    board[i][j] = 0;
                else if(live==3)
                    board[i][j] = 1;
            }
        }
    }
    
    int getNeighbors(int[][] board, int i,int j){
        int liveN = 0;
        int [][] dir = {{-1,0},{-1,1},{-1,-1},{1,0},{1,1},{1,-1},{0,-1},{0,1}};
        for(int []d : dir){
            int k=i+d[0];
            int l=j+d[1];
            if(k<0 || l< 0 || k >= board.length || l >= board[0].length ) continue;
            if(board[k][l] == 1)
                liveN++;
        }
        return liveN;
    } 
}

// in place 
 public void gameOfLife(int[][] board) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                int num = getNumLiveNeighbours(board, i, j);
                if(board[i][j] == 1) {
                    if(num < 2 || num > 3) {
                        board[i][j] = 1;
                    } else {
                        board[i][j] = 3;
                    }
                } else {
                    if(num == 3) board[i][j] = 2;
                }
            }
        }
        
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                board[i][j] >>= 1;
            }
        }
    }
    
    private int getNumLiveNeighbours(int[][] board, int x, int y) {
        int numLiveOnes = 0;
        
        for(int i = Math.max(x - 1, 0); i <= Math.min(x + 1, board.length - 1); i++) {
            for(int j = Math.max(y - 1, 0); j <= Math.min(y + 1, board[i].length - 1); j++) {
                if(i == x && j == y) continue;
                if((board[i][j] & 1) == 1) numLiveOnes++;
            }
        }
        
        return numLiveOnes;
    }
