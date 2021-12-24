/*
https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/
On a 2D plane, we place stones at some integer coordinate points.  Each coordinate point may have at most one stone.
Now, a move consists of removing a stone that shares a column or row with another stone on the grid.
What is the largest possible number of moves we can make?
(in other words how many stones we have to remove)

If stone a and stone b are in the same column/row, we connect them as a component
One component can be removed to one stone left at least.
The largest possible number of moves we can make = Total stones count - count of stones left = Total stones count - count of components.
*/
class Solution {
    int[] unions = new int[1000];
    public int removeStones(int[][] stones) {
        int len = stones.length;
        for (int i = 0; i < len; i++){
            unions[i] = i;
        }
        
        for (int i = 0; i < len; i++){
            for (int j = i + 1; j < len; j++){ // IMP i + 1
                if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]){
                    union(i, j);
                }
            }
        }
        
        int count = 0;
        for (int i = 0; i < len; i++){
            if (unions[i] == i) count++;
        }
        
        return len - count;
    }
    
    private void union(int x, int y){
        x = find(x);
        y = find(y);
        if (x == y) return;
        unions[y] = x;
    }
    
    private int find(int x){
        if (unions[x] == x) 
            return x;
        unions[x] = find(unions[x]); // path compression amortize
        return unions[x];
    }
}
