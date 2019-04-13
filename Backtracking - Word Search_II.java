https://leetcode.com/problems/word-search-ii/ 
Input:  
words = ["oath","pea","eat","rain"] and board =
[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
Output: ["eat","oath"]
// run time  N*4^maxWordLength
// beacse we creare 4 branches each step and depth is wordLength * N words 
class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        int rows = board.length;
        int cols = board[0].length;
        List<String> ans = new ArrayList();
        Set<String> set = new HashSet<String>();
        for(String word : words){
            if(set.contains(word)) // we dont want to process same words again and again 
                continue;
            for(int i=0;i<rows;i++){
                for(int j=0;j<cols;j++){
                    if(!set.contains(word) && find(board, word, 0, i, j)){ 
                        // we dont want to process same words again and again  only once and be done with it 
                        ans.add(word);
                        set.add(word);
                    }
                }
            }
        }
        return ans;
    }
    
    boolean find(char[][] board, String word, int wi, int i,int j){
        if(wi == word.length())
            return true; 
        if( i<0 || j<0 || i==board.length || j==board[0].length)
            return false;
        
        if(board[i][j] == word.charAt(wi)){
            int [][]dir = new int[][] {{0,1},{0,-1},{-1,0},{1,0}};
            board[i][j] = '#';
            for(int []p : dir){
                if(find(board, word, wi+1, i+p[0], j+p[1]) ){
                    board[i][j] = word.charAt(wi);
                    return true;
                }
            }
            board[i][j] = word.charAt(wi);
        }
        
        return false;
    }
}
