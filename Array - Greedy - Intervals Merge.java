/*
https://leetcode.com/problems/merge-intervals/

Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals,
and return an array of the non-overlapping intervals that cover all the intervals in the input.

Example 1:
Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]

Example 2:
Input: [[1,4],[2,3]]
Output: [[1,4]]

intevals are not sorted.

 */
 class Solution {
    public int[][] merge(int[][] intervals) {
        if(intervals==null || intervals.length==0)
            return intervals;
        
        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);
        
        // see int[] is fine bcz it is an object not primitive type. 
        List<int[]> list = new LinkedList<>();
        int start = intervals[0][0];
        int end = intervals[0][1];
        
        for(int i=1; i< intervals.length; i++){
            if(end >= intervals[i][0]){   // keep exapnding intereval 
                // why math.max => for intervals like this [[1,4],[2,3]]
                end = Math.max(end,intervals[i][1]);                        // very important 
                continue; 
            }
            
            list.add(new int[]{start,end});
            start = intervals[i][0];
            end = intervals[i][1];
        }
        // Add remaning interval
        list.add(new int[]{start,end});
        
        int[][] ans = new int[list.size()][2];
        for( int i=0;i<list.size();i++){
            ans[i][0] = list.get(i)[0];
            ans[i][1] = list.get(i)[1];
        }
        
        return ans;
    }
}
// if the interval was an object 
class Solution {
    
    public List<Interval> merge(List<Interval> intervals) {
        if(intervals==null) return new ArrayList<Interval>();
        if(intervals.size()==1) return intervals;
        
        List<Interval> ans = new ArrayList<Interval>();
        int n = intervals.size();
        
        Collections.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval a, Interval b) { 
                return a.start - b.start; 
            } 
        }); 
     
        for(int i=0;i<n;i++){
            Interval oneInterval = new Interval(intervals.get(i).start,0);
            int end = intervals.get(i).end;
            for(int j=i+1;j<n;j++){
                if(intervals.get(j).start <= end){
                    end= Math.max(end,intervals.get(j).end);
                } else{
                    break;
                }
                i=j;
            }
            oneInterval.end = end;
            ans.add(oneInterval);
        }
        return ans;
    }
}
