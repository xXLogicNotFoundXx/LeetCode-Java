/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
Input: [[0, 30],[5, 10],[15, 20]]
Output: 2

Input: [[4,10],[4,20],[10,12]]
Output: 2
/*
Solution 1:
Algorithm
    Sort the given meetings by their start time.
    Initialize a new min-heap and add the first meeting's ending time to the heap. We simply need to keep track of the ending times as that tells us when a meeting room will get free.
    For every meeting room check if the minimum element of the heap i.e. the room at the top of the heap is free or not.
    If the room is free, then we extract the topmost element and add it back with the ending time of the current meeting we are processing.
    If not, then we allocate a new room and add it to the heap.
    After processing all the meetings, the size of the heap will tell us the number of rooms allocated. This will be the minimum number of rooms needed to accommodate all the meetings.
 */


public class Solution {
    class sortByStart implements Comparator<Interval>{
        public int compare(Interval a, Interval b){
            return a.start - b.start;
        }
    }
    
    public int minMeetingRooms(Interval[] intervals) {
        if(intervals ==null || intervals.length==0) return 0;
        PriorityQueue<Integer> endTimes = new PriorityQueue<Integer>(); // it is automatically min_heap for mox heap you need comparator
        // Max heap would be PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>(Collections.reverseOrder());
        Arrays.sort(intervals,new sortByStart());
        endTimes.add(intervals[0].end);
        for(int i=1;i<intervals.length;i++){
            if(intervals[i].start >= endTimes.peek()){
                endTimes.poll();
            } 
            endTimes.add(intervals[i].end);
        }
        return endTimes.size();
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
}*/

