/*
https://leetcode.com/problems/n-queens/

The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
Queens attach horizontal, vertical and diagonal. 
Input: 4
Output:
// Solution 1.            // Solution 2
 [".Q..",                   ["..Q.",  
  "...Q",                    "Q...",
  "Q...",                    "...Q",
  "..Q."],                   ".Q.."]


Time Complexity: 
F(N) = N (queen placement) * F(N-1) 
O(N!)

Space : O(N) the call stack is max N times. 

*/
class Solution {
    public List<List<String>> solveNQueens(int n) {
        
        List<List<String>> finalRes = new ArrayList<List<String>>();
        if(n<=0)
            return finalRes;
        
        boolean[] col = new boolean[n];
        boolean[] diag = new boolean[n*2-1];
        boolean[] antiDiag = new boolean[n*2-1];
        
        nQueen(finalRes,new ArrayList<String>(), n, 0, col, diag, antiDiag);
        return finalRes;
    }
    
    void nQueen(List<List<String>> finalRes, List<String> oneResult,  int size, int row, boolean[] col, boolean[] diag, boolean[] antiDiag){
        if(size==row){
            List<String> subRes = new ArrayList<>();
            subRes.addAll(oneResult);
            finalRes.add(subRes);
            return;
        }
        
        StringBuilder str = new StringBuilder(new String(".").repeat(size)); // <- Nice!
        for(int c=0;c<size;c++){
            if(col[c] || diag[row + c] || antiDiag[row - c + size - 1]) // <- Remember this calculations.
                continue; 
            
            col[c] = true;
            diag[row + c] = true;
            antiDiag[row - c + size - 1] = true;
            str.setCharAt(c,'Q');
            oneResult.add(str.toString());
            
            nQueen(finalRes, oneResult, size, row+1, col, diag, antiDiag);
            
            str.setCharAt(c,'.');
            oneResult.remove(oneResult.size()-1);
            col[c] = false;
            diag[row + c] = false;
            antiDiag[row - c + size - 1] = false;
        }
    }
}
