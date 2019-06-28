/*
https://leetcode.com/problems/grid-illumination/
lamps - each (x,y) lamp lights up every square on its x-axis, y-axis, and both diagonals (similar to a Queen in chess).
queries - for each (x,y) ans is 0 if that square is not illuminated OR 1 if it is illuminated.
After each query (x, y) [in the order given by queries], we turn off any lamps that are at cell query(x, y) or are adjacent 8-directionally .
*/
class Solution {
    
    public int[] gridIllumination(int N, int[][] lamps, int[][] queries) {
        Map<Integer, Integer> m1 = new HashMap();       // row number to count of lamps
        Map<Integer, Integer> m2 = new HashMap();       // col number to count of lamps
        Map<Integer, Integer> m3 = new HashMap();       // diagonal x-y to count of lamps
        Map<Integer, Integer> m4 = new HashMap();       // diagonal x+y to count of lamps
        Map<Integer, Boolean> m5 = new HashMap();          // whether lamp is  ON|OFF
        
        
        // increment counters while adding lamps
        for(int i=0; i<lamps.length; i++){
            int x = lamps[i][0];
            int y = lamps[i][1];
            m1.put(x, m1.getOrDefault(x, 0) + 1);
            m2.put(y, m2.getOrDefault(y, 0) + 1);
            m3.put(x-y, m3.getOrDefault(x-y, 0) + 1);
            m4.put(x+y, m4.getOrDefault(x+y, 0) + 1);
            m5.put(N*x+y, true);
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
				if(m5.containsKey(N*x1 + y1) && m5.get(N*x1 + y1)){
                    // the lamp is on, turn it off, so decrement the count of the lamps
                    m1.put(x1, m1.getOrDefault(x1, 1) - 1);
                    m2.put(y1, m2.getOrDefault(y1, 1) - 1);
                    m3.put(x1 - y1, m3.getOrDefault(x1 - y1, 1) - 1);
                    m4.put(x1 + y1, m4.getOrDefault(x1 + y1, 1) - 1);
                    m5.put(N*x1+y1, false);
                }
            }
        }
        
        return ans;
    }
}
