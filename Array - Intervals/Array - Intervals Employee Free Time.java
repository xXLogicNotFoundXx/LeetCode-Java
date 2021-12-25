/*
Hard - IMP

https://leetcode.com/problems/employee-free-time/

We are given a list schedule of employees, which represents the working time for each employee.
Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.
Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.
(Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays. 
For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined).  
Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.

example 1:

Input: schedule = [ [[1,2],[5,6]], [[1,3]], [[4,10]]]
Output: [[3,4]]
Explanation: There are a total of three employees, and all common
free time intervals would be [-inf, 1], [3, 4], [10, inf].
We discard any intervals that contain inf as they aren't finite.

Example 2:
Input: schedule = [ [[1,3],[6,7]], [[2,4]], [[2,5],[9,12]]]
Output: [[5,6],[7,9]]

*/

/*
Put all intervals in one list and sort all intervals. 

Idea is -  we keep expanding working interval  until two interval are overlapping 
         - and when we find two interval are not overlapping at all 
         ... the gap in beetween them is a free time for all employees.
        
1 Sort all events in start time then according to end time. 
2 Initiate start and end with first interval 
    
3 if end is greter than nextStart ( we have to expand the interval of working employee )
        end becomes = Math.max(end, nextEnd); 

4 if end is less than nextStart ( we have found the free time )
        freeTime = [end, nextStart]]]
*/

class Solution {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> list = new ArrayList<Interval>();
        List<Interval> ans = new ArrayList<Interval>();
        
        if(schedule==null)
            return ans;
        
        for(List<Interval> iList : schedule){
            for(Interval i : iList){
                list.add(i);
            }
        }
        
        // Sort all events in start time then according to end time. 
        Collections.sort(list, (a,b) -> a.start==b.start ? a.end-b.end : a.start-b.start);
        // OR
        Collections.sort(list, new Comparator<Interval>(){
            public int compare(Interval a, Interval b){
                if(a.start==b.start)
                    return a.end - b.end;
                
                return a.start - b.start;
            }
        });
        
        int start = list.get(0).start;
        int end = list.get(0).end;
        
        // we keep track of working interval and when we find 
        // two interval are not overlapping at all ... the gap in beetween them is a free time for all employees 
        for(int i=1; i<list.size(); i++){
            Interval next = list.get(i);
            
            // if end is greter than nextStart 
            // we have to expand the interval of working employee
            if(next.start <= end) {
                // we have to epand current working interval 
                end = Math.max(end, next.end);
            } else { 
                // if end is less than nextStart 
                // we have found the free time
                Interval freeTime = new Interval(end, next.start);
                ans.add(freeTime);
                
                start = next.start;
                end =   next.end;
            }
        }
        
        return ans;
    }
}
