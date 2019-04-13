https://leetcode.com/problems/word-search/ 
board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]
Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.

class Solution {
// N*4^maxWordLength
    public boolean exist(char[][] board, String word) {
         for(int i = 0;i<board.length;i++){
            for(int j = 0;j<board[0].length;j++){
                    if(search(i,j,board,word,0)) return true;
            }
        }
        return false;
    }
    
    private boolean search(int i,int j, char[][] board, String word, int idx){
        if(idx == word.length()) return true;
        if(i<0||j<0||i>=board.length||j>=board[0].length||board[i][j]!=word.charAt(idx)) return false;
        char c = board[i][j];           // this is good if you want to preserve an array get that char --
                                        // or you want to create nodes again for anohter flow....
        board[i][j] = '1';              // Temporarily change the value ... this is gonna act as visited 
        if(search(i+1,j,board,word,idx+1)||
          search(i-1,j,board,word,idx+1)||
          search(i,j+1,board,word,idx+1)||
          search(i,j-1,board,word,idx+1)) return true;
        
        board[i][j] = c;                // and preserve that array for future flows
        return false;
    }
}
