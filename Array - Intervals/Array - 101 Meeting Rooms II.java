/**

Medium lotd of lots of companies VIMP -amz 50 fb30 ..
https://leetcode.com/problems/meeting-rooms-ii/

Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], return the minimum number of conference rooms required.


Input: [[0, 30],[5, 10],[15, 20]]
Output: 2

Input: [[4,10],[4,20],[10,12]]
Output: 2


Sort by start time
add meetings in priority queue ... minHeap for ending time
We keep concurrent meeting in priority queue
    Sort -> N*logN and Heap - > N*logN)
Time -> O(N*logN))
Space -> O(N)

*/

class Solution {
    public int minMeetingRooms(int[][] intervals) {
        if(intervals==null || intervals.length==0)
            return 0;

        // sort by start time
        Arrays.sort(intervals, (a,b) -> a[0]-b[0]);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1]-b[1]); // minHeap for ending time
        // minimum end time should be at the top of HEAP

        pq.add(intervals[0]);
        for(int i=1; i<intervals.length; i++){

            // if start time is greater than or eqal to peeks ending time
            // the meeting is over poll that meeting
            if(intervals[i][0] >= pq.peek()[1]){
                pq.poll();
            }

            pq.add(intervals[i]);
        }


        return pq.size();
    }
}

/* Solution 2 : have two arrays start and end timings sort them and then
We are checking how many meetings begin before the earliest-ended meeting ends.
For eg:
Starts 1,5,6,9,10
Ends 8,11,12,13,14
so meeting 1,5,6 start before first meeting ends at 8 so we need 3 rooms.
For 9 and 8 we just move i++ and j++ ( think of as it took the spot of the meeting ended at 8.)
then for 10 and 11.. all previous rooms are occupied and one of them ends after 10... so we need a room for a meeting starting at 10
so total 4 rooms
N*LogN
*/
public class Solution {
    public int minMeetingRooms(Interval[] intervals) {
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];
        for(int i=0; i<intervals.length; i++) {
            starts[i] = intervals[i].start;
            ends[i] = intervals[i].end;
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int rooms = 0;
        int j = 0;
        for(int i=0; i<starts.length; i++) {
            if(starts[i]<ends[j])
                rooms++;
            else
                j++;
        }
        return rooms;
    }
}
