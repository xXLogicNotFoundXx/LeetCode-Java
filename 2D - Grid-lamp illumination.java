/*
https://leetcode.com/problems/grid-illumination/
lamps - each (x,y) lamp lights up every square on its x-axis, y-axis, and both diagonals (similar to a Queen in chess).
queries - for each (x,y) ans is 0 if that square is not illuminated OR 1 if it is illuminated.
After each query (x, y) [in the order given by queries], we turn off any lamps that are at cell query(x, y) or are adjacent 8-directionally .
Return an array of integers ans where each value ans[i] should be equal to the answer of the ith query queries[i].
*/
class Solution1 {
    // Have an array of Row, Col, Diagonal and AntiDiagonal 
    // Keep a count of lamps in each row[i] ,col[j] ,diag[i+j], antidiag[j-i+N] (remember this calculation to get same index for antiDiagonal elements)
    // if any of these arrays count is more than 0 for given i,j then that cell is lit
    // then traverse the block and update the count of these arrays
    // memory limit exceed bcz we creat a grid of NxN where N=10000 and all these 4 arrays with N 
    public int[] gridIllumination(int N, int[][] lamps, int[][] queries) {
        int[][] lampsGrid = new int[N][N];
        int rN = N, cN = N;
        
        int[] row = new int[rN];
        int[] col = new int[cN];
        int[] diag = new int[rN+cN];
        int[] backSlash = new int[rN+cN];
        
        int[] output = new int[queries.length];
        for(int []lamp : lamps){
            lampsGrid[lamp[0]][lamp[1]]=1;
        }
        
        // 9 blocks can have lamp for each query(x,y) itself and 8 adjacent blocks
        int[][] dirs = {{0,0}, {0,1}, {0,-1}, {1,0}, {-1,0}, {1,1}, {1,-1}, {-1,1}, {-1,-1}};
        for(int i=0;i<rN;i++){
            for(int j=0;j<cN;j++){
                if(lampsGrid[i][j]==1){
                    row[i]++;
                    col[j]++;
                    diag[i+j]++;
                    backSlash[j-i+cN]++;
                }
            }
        }
        
        for(int i=0;i<queries.length;i++){
            int[] query = queries[i];
            output[i] = isLit(query[0],query[1],cN,row,col,diag,backSlash)==true? 1 : 0 ;
            
            //turnOffTheGrid
            for(int dir[] : dirs){
                int x1 = query[0]+dir[0];
                int y1 = query[1]+dir[1];
                if(x1<0 || x1>=rN || y1<0 || y1>= cN)
                    continue;
                if(lampsGrid[x1][y1]==1){
                    lampsGrid[x1][y1]=0;
                    row[x1]--;
                    col[y1]--;
                    diag[x1+y1]--;
                    backSlash[y1-x1+cN]--;
                }
            }
        }  
        return output; 
    }
    
    boolean isLit(int x, int y, int cN, int[] row,int[] col,int[] diag,int[]backSlash){
        return row[x]>0 || col[y]>0 || 
               diag[x+y]>0 || backSlash[y-x+cN]>0;
    }
}

// This one is nice solution. Using minimal memory. 
// The logic is same but using HashMaps and Set
class Solution {
    
    public int[] gridIllumination(int N, int[][] lamps, int[][] queries) {
        Map<Integer, Integer> row = new HashMap();       // row number to count of lamps
        Map<Integer, Integer> col = new HashMap();       // col number to count of lamps
        Map<Integer, Integer> antiDiag = new HashMap();  // AntiDiagonal(Backslash) y-x+N to count of lamps
        Map<Integer, Integer> diag = new HashMap();      // diagonal x+y to count of lamps
        Set<String> litLamps = new HashSet<>();          //  lamps that are ON
        
        
        // increment counters while adding lamps
        for(int i=0; i<lamps.length; i++){
            int x = lamps[i][0];
            int y = lamps[i][1];
            row.put(x, row.getOrDefault(x, 0) + 1);
            col.put(y, col.getOrDefault(y, 0) + 1);
            antiDiag.put(y-x+N, antiDiag.getOrDefault(y-x+N, 0) + 1); // AntiDiagonal(Backslash) y-x+N to count of lamps
            diag.put(x+y, diag.getOrDefault(x+y, 0) + 1); // diagonal x+y to count of lamps
            litLamps.add(x+"-"+y);
        }
        
        int[] ans = new int[queries.length];
        
        // 9 blocks can have lamp for each query(x,y) itself and 8 adjacent blocks
        int[][] dirs = {{0,0}, {0,1}, {0,-1}, {1,0}, {-1,0}, {1,1}, {1,-1}, {-1,1}, {-1,-1}};
        
        // address queries
        for(int i=0; i<queries.length; i++){
            int x = queries[i][0];
            int y = queries[i][1];
            
            ans[i] = (row.getOrDefault(x, 0) > 0 ||
                      col.getOrDefault(y, 0) > 0 || 
                      antiDiag.getOrDefault(y-x+N, 0) > 0 || 
                      diag.getOrDefault(x+y, 0) > 0) ? 1 : 0;            
            
            // switch off the lamps in 9 blocks if any 
            for(int d=0; d<dirs.length; d++){
                int x1 = x + dirs[d][0];
                int y1 = y + dirs[d][1];
				if(litLamps.contains(x1+"-"+y1)){
                    // the lamp is on, turn it off, so decrement the count of the lamps
                    row.put(x1, row.get(x1) - 1);
                    col.put(y1, col.get(y1) - 1);
                    antiDiag.put(y1-x1+N, antiDiag.get(y1-x1+N) - 1);
                    diag.put(x1+y1, diag.get(x1+y1) - 1);
                    litLamps.remove(x1+"-"+y1);
                }
            }
        }
        return ans;
    }
}
