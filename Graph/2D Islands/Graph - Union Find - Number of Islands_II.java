/*
Hard - IMP
Google6 Uber3 Apple2
https://leetcode.com/problems/number-of-islands-ii/
A 2d grid map of m rows and n columns is initially filled with water.
We may perform an addLand operation which turns the water at position (row, col) into a land.
Given a list of positions to operate, count the number of islands after each addLand operation.
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
You may assume all four edges of the grid are all surrounded by water.
Example:
Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
Output: [1,1,2,3]
Explanation:
Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).
0 0 0
0 0 0
0 0 0
Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.
1 0 0
0 0 0   Number of islands = 1
0 0 0
Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.
1 1 0
0 0 0   Number of islands = 1
0 0 0
Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.
1 1 0
0 0 1   Number of islands = 2
0 0 0


*/

/*
We can solve using 101 technique.
Mark 1 in the grid, create visitot[m][n] array and calculate number of islands for each position.
Runtime = O(L*M*N) L are number of positions we have
Space O(m*n)
*/
class Solution {

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        int[] parent = new int[m*n];
        List<Integer> ans = new ArrayList<>();
        Arrays.fill(parent,-1);

        int islands = 0;
        for(int i=0; i<positions.length; i++){
            islands = makeAnIsland(islands, positions[i][0], positions[i][1], parent, m, n);
            ans.add(islands);
        }

        return ans;
    }

    int makeAnIsland(int islands, int x ,int y, int[] parent, int rows, int cols){


        if(parent[x*cols + y] != -1)  // duplicates entries in positions :/
            return islands;

        int[][] directions = new int[][]{{-1,0}, {1,0}, {0,-1}, {0,1}};
        Set<Integer> neighbouringIslands = new  HashSet<Integer>();

        int pos = x*cols + y;
        parent[pos] = pos;

        for(int [] dir : directions) {
            int x1 = x + dir[0];
            int y1 = y + dir[1];

            if(x1<0 || x1==rows || y1<0 || y1==cols)
                continue;

            int newPos = x1*cols + y1;
            if(parent[newPos] != -1){
                // HashSet avoids duplicates .. so neighbouring two island that are connected will be counted as one
                neighbouringIslands.add( find(parent,newPos) );
            }

        }
        
        // Note needed .. below code will handle it anyway
        if(neighbouringIslands.size()==0){   // new island
            return islands+1;
        }

        for(int island : neighbouringIslands){ // make all islands point to this one island
            union(parent, island, pos);
        }

        islands = islands - neighbouringIslands.size() + 1;
        return islands;
    }


    int find(int[] parent, int pos){

        if(parent[pos]==pos)
            return pos;

        parent[pos] = find(parent, parent[pos]);  // path compression
        return parent[pos];
    }

    void union(int[] parent, int pos1, int pos2){
        if(find(parent, pos1) == find(parent, pos2))
            return;

        parent[pos1] = find(parent, pos2);
    }

}
