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
    public int[][] merge(int[][] intervals) {
        
        int n=intervals.length;
        
        if(n==0)
            return new int[0][];
        
        int[] start = new int[n];
        int[] end = new int[n];
        for(int i=0;i<n;i++){
            start[i]=intervals[i][0];
            end[i]=intervals[i][1];
        }
        Arrays.sort(start);
        Arrays.sort(end);
        
        List<int[]> merged= new ArrayList<>();
        int e=0;
        int init=start[0];
        for(int s=0;s<n;s++){
            if(start[s]<=end[e]){
                e=s; 
            }else{
                int []interval = new int[2];
                interval[0]=init;
                interval[1]=end[e];
                merged.add(interval);
                
                init=start[s];
                e++;
            }
        }
        
        if(e<n){
            int []interval = new int[2];
            interval[0]=init;
            interval[1]=end[e];
            merged.add(interval);
        }
        
        return merged.toArray(new int[merged.size()][]); // smart way to do it
    }
}

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
