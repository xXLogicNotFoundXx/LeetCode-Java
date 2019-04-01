https://leetcode.com/problems/merge-intervals/
Example 1:
Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]

Example 2:
Input: [[1,4],[4,5]]
Output: [[1,5]]

intevals are not sorted.

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
    class sortByStart implements Comparator<Interval>{
        public int compare(Interval a, Interval b){
            return a.start - b.start;
        }
    }
    
    public List<Interval> merge(List<Interval> intervals) {
        if(intervals ==null || intervals.size()==0)
            return intervals;
        
        Collections.sort(intervals, new sortByStart());
        List<Interval> ans = new ArrayList();
        Interval temp = new Interval(intervals.get(0).start,intervals.get(0).end);
        for(int i=1;i<intervals.size();i++){
            Interval intv = intervals.get(i);
            if(temp.end >= intv.start){
                temp.end = Math.max(temp.end,intv.end);
            } else {
                ans.add(temp);
                temp = intv;
            }
        }
        
        ans.add(temp);
        return ans;
    }
}
