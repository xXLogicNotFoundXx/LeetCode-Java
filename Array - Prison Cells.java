/*
https://leetcode.com/problems/prison-cells-after-n-days/

There are 8 prison cells in a row, and each cell is either occupied or vacant.
Each day, whether the cell is occupied or vacant changes according to the following rules:
If a cell has two adjacent neighbors that are both occupied or both vacant, then the cell becomes occupied.
Otherwise, it becomes vacant.
(Note that because the prison is a row, the first and the last cells in the row can't have two adjacent neighbors.)
Given the initial state of the prison, return the state of the prison after N days (and N such changes described above.

 Here 0th and 8th position can be 1 initially but for next iterations those become 0's and stays 0's.
 Here's a generalizable way to approach this problem w/o having to have a ton of insight:
 there are 8 cells so there are 2 ^ 8 = 256 possible cell configurations.
 The pattern repeats latest within 2^k + 1 adjacent cells, while k is the length of the prison cells. 
 Here cells are 7, so after 2^7 64 transformations you are guaranteed to observe a cycle. 
 
 By using a HashMap storing the pattern as key and transformation state index as  value, 
 it is possible to find the repeated pattern too. 
 However, we can just register 1st iteration and and check for the cycle so no need for HashMap.
*/
class Solution {
     public int[] prisonAfterNDays(int[] cells, int N) {
        
        // get rid of those 1s in 0th and 8th position 
        int[] firstSimulation = new int[8];
        firstSimulation[0]=firstSimulation[7]=0;
        for (int i=1; i<7; i++) 
            firstSimulation[i] = (cells[i-1] == cells[i+1] ? 1 : 0);
        
        cells = firstSimulation.clone();
        N -= 1; // because we calculated one state already 
        
        // at this point we have our base case ready. 
        int cycle = 0;
        while (N > 0) {
            int[] nextSimulation = new int[8];
            for (int i=1; i<7; i++) 
                nextSimulation[i] = (cells[i-1] == cells[i+1] ? 1 : 0);
            
            cycle++;
            N--;
            if (Arrays.equals(firstSimulation, nextSimulation)) 
                N %= cycle;
            
            cells = nextSimulation.clone();
        }
        return cells;
    }
}
