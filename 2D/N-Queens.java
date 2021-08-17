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
F(N) = Nth (queen placement) * F(N-1) 
O(N!)

Space : O(N) the call stack is max N times. 

The MAIN problem to solve is.. how do you decide that placing one queen at particulat position is ok?
and no queens are attacking each other.

Once you answer that, then other part you can do by using recursion. If this poisiton is ok then go to next row.
While building one row answer at a time. 

How do you decide the position is safe very fast? 
 We can have column[N[ boolean array and once we put a queen in one position. That column is takes and next rows cant take that column for queens.
 Similarly we can take diagonal[(N+N)-1] boolean array, do the calculation to find unique index for all diagonal elements.
   For any x,y we can generate unique index by doing x+y.
 Similarly we can take AntiDiagonal[(N+N)-1] boolean array 
   For any xy, we can genrate unique index by x-y+N ... here N has to be y's max value ( +N so that -> index value never goes below 0 ( N and y canceles out)
                                           OR y-x+M ... here M has to be x's max value ( +M so that -> index value never goes below 0 ( M and x canceles out)
*/
class Solution {
    public List<List<String>> solveNQueens(int n) {
        
        List<List<String>> finalRes = new ArrayList<List<String>>();
        if(n<=0)
            return finalRes;
        
        boolean[] col = new boolean[n];
        boolean[] diag = new boolean[(n+n)-1];
        boolean[] antiDiag = new boolean[(n+n)-1];
        
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
            if(col[c] || diag[row + c] || antiDiag[row - c + size - 1]) // <- this calculations for the anti-diagonal ...
                continue; 
            
            col[c] = true;
            diag[row + c] = true;
            antiDiag[row - c + size - 1] = true; // <- this calculations for the anti-diagonal....
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
