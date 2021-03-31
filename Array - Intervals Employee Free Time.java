/*
https://leetcode.com/problems/employee-free-time/
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
        
        // sort by start and then end time 
        Collections.sort(list, (a,b) -> a.start==b.start ? a.end-b.end : a.start-b.start);
        int start = list.get(0).start;
        int end = list.get(0).end;
        
        // expand interval .. if cant expand the gap is free time 
        for(int i=1; i<list.size(); i++){
            Interval intr = list.get(i);
            
            if(intr.start <= end) {
                // start is beetwing prev interval
                end = Math.max(intr.end, end);
            } else {
                Interval freeTime = new Interval(end,intr.start);
                ans.add(freeTime);
                
                start = intr.start;
                end =   intr.end;
            }
        }
        
        return ans;
    }
}
