/*
https://leetcode.com/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid/
*/
class Solution {
    // We can use BFS in this but instead of regular queue 
    // we can use priority queue so that .. we conside all cells that we can reach with zero cost first
    // from every cell we keep adding zero cost cell and other three with +1 cost in PQ. 
    // but while processinig wil get all zero cost then cost=1 cells then cost=2
    // during this processing we sould reach cell(m,n) and that would be our min cost.
    // for visited .. we can just take x and y bcz we may add x,y,some cost but later we can reach x,y with zero cost.
    // so for visited we have to consider x,y and cost combinations.
    
    // 93 ms, faster than 7.17% of Java online submissions  with Priority Queue 
    class Cell { 
        int x, y; 
        int cost = 0;
        Cell(int x, int y, int cost) {
            this.x=x;
            this.y=y;
            this.cost = cost; 
        }
    }
    
    public int minCost(int[][] grid) {
        
        PriorityQueue<Cell> pq = new PriorityQueue<Cell>( (a,b) -> a.cost-b.cost );
        
        Set<String> visited = new HashSet<String>();
        pq.add(new Cell(0,0,0));
        
        
        while(!pq.isEmpty()) {
            Cell cell = pq.poll();
            
            if(cell.x == grid.length-1  &&  cell.y == grid[0].length-1){
                return cell.cost;
            }
                
            if(visited.contains(getHash(cell.x, cell.y)))
                continue;
            
            visited.add(getHash(cell.x, cell.y));
            
            addNeighbours(grid, cell, pq, visited);
        }
        
        return Integer.MAX_VALUE;
    }
    
    void addNeighbours(int[][] grid, Cell cell, PriorityQueue<Cell> pq, Set<String> visited){
                                         // R     L       D      U
        int[][] dir = new int[][]{{0,0}, {0,1}, {0,-1}, {1,0}, {-1,0} };
    
        int freeCell = grid[cell.x][cell.y];
        
        for(int i=1; i<dir.length; i++){
            
            int x = cell.x + dir[i][0];
            int y = cell.y + dir[i][1];
            
            if(x<0 || x>= grid.length || y<0 || y>=grid[0].length)
                continue; 
            
            if(visited.contains(getHash(x,y)))
                continue; 
            
            int cost = i==freeCell ? 0 : 1; 
            
            pq.add(new Cell(x,y, cell.cost + cost));
            
        }
    }
    
    String getHash(int x, int y){
        return x+"-"+y;
    }
    
// if you think we are always gonna visit cost 0, 1, 2, 3 ... 
// if we use Deque instead of priority queue 
// if it is zero cost add it from first or add it from the back 
// 76 ms, faster than 8.04% of Java online submissions.  with Deque 
    
// if you do visited as int[][] visited = new int[m][n]; that will reduce runtime complexity. 
// as we wont be creating string out of x,y and calculating hash code for it.
    
}
