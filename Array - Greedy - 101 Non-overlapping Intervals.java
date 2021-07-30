/*


https://leetcode.com/problems/non-overlapping-intervals/

Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

Input: [[1,2],[2,3],[3,4],[1,3]]
Output: 1
Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.

1. Sort by end (Why not sort by start?)
2. Check if current interval is overlaping with previous if so drop current and count++

Lesson : Always think of sorting from start and then end too. 
*/
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals==null || intervals.length==0)
            return 0;
        
        /* why are we doing it Sorting end ?  after sorting with end we have following 3 cases 
          case 1 :   ---- 
                          ------   ( 1st and 2nd(current) interval do not over lap )
                          
          case 2 :   -----          ( 1st and 
                         ------             2nd(current) interval overlap ) ->  good to drop current inteval 
                         
          Case 3:    -----          ( 1st and 
                   ---------------          2nd(current) interval overlap ) ->  good to drop current inteval 
                   
                   
        So in any case it always good to drop current intersection. 
                   
        if you sort with start then it becomes more complicated .. 
        for ex : 
                    --------------                                  ----------------
                       -----                                OR              -----------------
                             -------------                                       ----------
                we are at the 2nd interval 
                we have to check previus and next interval to figure out which to delete and move forward.
                case 1 we should delete first interval 
                case 2 we should delete current interval
        */
        
        Arrays.sort(intervals, (a,b) -> a[1] - b[1]);
        

        int count=0;
        int end = intervals[0][1];
        
        for(int i=1; i< intervals.length; i++){
            if(end > intervals[i][0]){   
                // we need to drop current interval one 
                count++;
                continue;
            }
            end = intervals[i][1];
        }
    
        return count;
    }
}
