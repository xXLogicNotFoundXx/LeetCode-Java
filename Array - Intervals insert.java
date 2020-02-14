/*
https://leetcode.com/problems/insert-interval/

Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
*/
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> ans = new ArrayList<>();
        int[] toAdd = newInterval;
        
        for (int i = 0; i < intervals.length; i ++) {
			/*1. No overlap and toAdd appears before current interval, add toAdd to result.*/
            if (intervals[i][0] > toAdd[1]) {
                ans.add(toAdd);
                toAdd = intervals[i];
            }
            /*2. Has overlap, update the toAdd to the merged interval.*/
			else if (intervals[i][1] >= toAdd[0]){  
                toAdd = new int[] {Math.min(intervals[i][0], toAdd[0]),
                                   Math.max(intervals[i][1], toAdd[1]) };
            }
            /*3. No overlap and toAdd appears after current interval, add current interval to result.*/
            else{
                ans.add(intervals[i]);
            }  
        }
        ans.add(toAdd);
		return ans.toArray(new int[ans.size()][2]);
    }
}
