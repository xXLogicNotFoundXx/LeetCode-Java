/*
https://leetcode.com/problems/grid-illumination/
lamps - each (x,y) lamp lights up every square on its x-axis, y-axis, and both diagonals (similar to a Queen in chess).
queries - for each (x,y) ans is 0 if that square is not illuminated OR 1 if it is illuminated.
After each query (x, y) [in the order given by queries], we turn off any lamps that are at cell query(x, y) or are adjacent 8-directionally .
Return an array of integers ans where each value ans[i] should be equal to the answer of the ith query queries[i].
*/
class Solution1 {

    // Have an array of Row, Col, Diagonal and AntiDiagonal 
    // Keep a count of lamps in each row[i] ,col[j] ,diag[i+j], antidiag[j-i+N]
    // if any of these arrays count is more than 0 for given i,j then that cell is lit
    // then traverse the block and update the count of these arrays
    // memory limit exceed bcz we creat a grid of NxN where N=10000
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

class Solution {
    
    public int[] gridIllumination(int N, int[][] lamps, int[][] queries) {
        Map<Integer, Integer> m1 = new HashMap();       // row number to count of lamps
        Map<Integer, Integer> m2 = new HashMap();       // col number to count of lamps
        Map<Integer, Integer> m3 = new HashMap();       // AntiDiagonal(Backslash) x-y to count of lamps
        Map<Integer, Integer> m4 = new HashMap();       // diagonal x+y to count of lamps
        Set<String> m5 = new HashSet<>();                 //  lamp that are ON
        
        
        // increment counters while adding lamps
        for(int i=0; i<lamps.length; i++){
            int x = lamps[i][0];
            int y = lamps[i][1];
            m1.put(x,   m1.getOrDefault(x, 0) + 1);
            m2.put(y,   m2.getOrDefault(y, 0) + 1);
            m3.put(x-y, m3.getOrDefault(x-y, 0) + 1); // AntiDiagonal(Backslash) x-y to count of lamps
            m4.put(x+y, m4.getOrDefault(x+y, 0) + 1); // diagonal x+y to count of lamps
            m5.add(x+""+y);
        }
        
        int[] ans = new int[queries.length];
        
        // 9 blocks can have lamp for each query(x,y) itself and 8 adjacent blocks
        int[][] dirs = {{0,0}, {0,1}, {0,-1}, {1,0}, {-1,0}, {1,1}, {1,-1}, {-1,1}, {-1,-1}};
        
        // address queries
        for(int i=0; i<queries.length; i++){
            int x = queries[i][0];
            int y = queries[i][1];
            
            ans[i] = (m1.getOrDefault(x, 0) > 0 ||
                      m2.getOrDefault(y, 0) > 0 || 
                      m3.getOrDefault(x-y, 0) > 0 || 
                      m4.getOrDefault(x+y, 0) > 0) ? 1 : 0;            
            
            // switch off the lamps in 9 blocks if any 
            for(int d=0; d<dirs.length; d++){
                int x1 = x + dirs[d][0];
                int y1 = y + dirs[d][1];
				if(m5.contains(x1+""+y1)){
                    // the lamp is on, turn it off, so decrement the count of the lamps
                    m1.put(x1, m1.getOrDefault(x1, 1) - 1);
                    m2.put(y1, m2.getOrDefault(y1, 1) - 1);
                    m3.put(x1 - y1, m3.getOrDefault(x1 - y1, 1) - 1);
                    m4.put(x1 + y1, m4.getOrDefault(x1 + y1, 1) - 1);
                    m5.remove(x1+""+y1);
                }
            }
        }
        
        return ans;
    }
}
