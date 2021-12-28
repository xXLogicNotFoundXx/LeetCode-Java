/*
VVIMP - So many companies
Amazon-29 Microsoft|14 Twitter|12 Facebook\11 and so many

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

N*4^maxWordLength
*/
class Solution {

    public boolean exist(char[][] board, String word) {
         for(int i = 0;i<board.length;i++){
            for(int j = 0;j<board[0].length;j++){
                    if(search(i,j,board,word,0)) return true;
            }
        }
        return false;
    }

    private boolean search(int i, int j, char[][] board, String word, int idx){
        if(idx == word.length())
          return true;

        if( i<0 || j<0 ||
            i>=board.length || j>=board[0].length ||
            board[i][j]!=word.charAt(idx))
            return false;

        char c = board[i][j];           // this is good if you want to preserve an array get that char --
                                        // or you want to create nodes again for anohter flow....
        board[i][j] = '1';              // Temporarily change the value ... this is gonna act as visited

        if(search(i+1,j,board,word,idx+1)||
           search(i-1,j,board,word,idx+1)||
           search(i,j+1,board,word,idx+1)||
           search(i,j-1,board,word,idx+1)) {
          return true;
        }

        board[i][j] = c;                // and preserve that array for future flows
        return false;
    }
}

/*
Hard - VVIMP
Amazon30 Uber30 Cisco15 Microsoft12 Apple4 and so many
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

Time complexity: O( M*N + Max(Words*4^L, Words*M*N) )
// M*N to build a map
// either we are gonna search whole matrix for a word
// OR we would make 4 calls for each letter in word for length L.
*/
class Solution {
    public List<String> findWords(char[][] board, String[] words) {

        HashMap<Character,List<int[]>> map = new HashMap<>();
        for (int i = 0 ; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                map.putIfAbsent(board[i][j], new ArrayList<int[]>());
                List<int[]> list = map.get(board[i][j]);
                list.add(new int[]{i,j});
            }
        }

        Set<String> set = new HashSet<>();  // set is used to avoid duplicate
        for (String word: words){
            if (!set.contains(word) && findWord(board, map, word)) {
                set.add(word);
            }
        }

        List<String> ans = new ArrayList<String>();
        ans.addAll(set);

        return ans;
    }

    private boolean findWord(char[][] board, HashMap<Character,List<int[]>> map, String word) {
        if(!map.containsKey(word.charAt(0)))
            return false;
        List<int[]> positions = map.get(word.charAt(0));

        for(int[] pos : positions){
            if(dfs(board, word, 0, pos[0], pos[1]))
                return true;
        }

        return false;
    }

    private boolean dfs(char[][] board, String word, int cur, int i, int j) {
        if (cur == word.length())
            return true;

        if (i < 0 ||  i == board.length ||  j < 0 ||  j == board[0].length)
            return false;

        if (word.charAt(cur) != board[i][j])
            return false;

        board[i][j] = '#';
        boolean res =   dfs(board, word, cur+1, i-1, j) ||
                        dfs(board, word, cur+1, i+1, j) ||
                        dfs(board, word, cur+1, i, j-1) ||
                        dfs(board, word, cur+1, i, j+1);
        board[i][j] = word.charAt(cur);
        return res;
    }
}
