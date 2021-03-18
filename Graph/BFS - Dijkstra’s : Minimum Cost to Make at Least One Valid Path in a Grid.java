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
    public int minCost(int[][] grid) {
        
        if(grid==null || grid.length==0)
            return 0;
        
        int m=grid.length-1;
        int n=grid[0].length-1;
        
        // no need to use priority queue we can use Deque ... same cost goes to first and other goes last .. 
        // even after doing that it is still not a lot of improovement
        PriorityQueue<Cell> pq = new PriorityQueue<Cell>((a,b)->a.cost-b.cost);
        Set<Cell> visited = new HashSet<Cell>();
        
        pq.add(new Cell(0,0,0));
        visited.add(new Cell(0,0,0));
        
        int[][] dir = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
        
        while(!pq.isEmpty()){
            Cell cell = pq.poll();
            
            if(cell.x == m && cell.y == n)
                return cell.cost;
            
            int freeCell = grid[cell.x][cell.y];
            
            for(int i=0; i<dir.length; i++){
                
                int[] d = dir[i];
            
                int x1 = cell.x+d[0];
                int y1 = cell.y+d[1];
                
                if(x1<0 || x1>m || y1<0 || y1>n)
                    continue;
                
                Cell newCell;
                if(i == freeCell-1){
                    newCell = new Cell(x1, y1, cell.cost);
                } else {
                    newCell = new Cell(x1, y1, cell.cost+1);
                }
                
                if(!visited.contains(newCell)){
                    // System.out.println(cell  + "->" + newCell ); lessong lernt always override equals and hashCode
                    pq.add(newCell);
                    visited.add(newCell);
                }
            }
        }
    
        return -1;
    }
    
    /*
        You must override hashCode() in every class that overrides equals(). Failure to do so will result in a violation of the general contract for Object.hashCode(),
        which will prevent your class from functioning properly in conjunction with all hash-based collections, including HashMap, HashSet, and Hashtable. 
    */
    class Cell {
        int x,y,cost;
        Cell(int x, int y, int cost ){
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
        
        @Override 
        public int hashCode(){
             return x*91 * y*91 + cost;
            // Same test case 
            // return x*31 * y*31 + cost;   // this one is in 77 miliseconds     Submitted 
            // x*31 + y + cost; // this one resulted into 289 miliseconds    TLE  
            // x*31 + y*31 + cost*31; resulted into 2656 milisecond          TLE 
            // why? x*31 + y*31 + cost*31 so many cells would result into same bucket ....
        }
        
        @Override 
        public boolean equals(Object another){      // <---- to override .. see passing as an Object not Cell ... compiler error 
            Cell obj = (Cell) another;
            return x==obj.x && y==obj.y && cost==obj.cost;
        }
        
        @Override 
        public String toString(){
            return x + " "+ y +" "+ cost;
        }
    }
}
