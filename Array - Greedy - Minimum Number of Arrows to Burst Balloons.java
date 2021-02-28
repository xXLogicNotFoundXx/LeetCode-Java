/*Idea:
https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/

Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], 
return the minimum number of conference rooms required.

Input: intervals = [[0,30],[5,10],[15,20]]
Output: 2

2 arrows are needed 
at x = 6 (bursting the balloons [2,8] and [1,6]) 
and another arrow at x = 11 (bursting the other two balloons).


We know that eventually we have to shoot down every balloon, 
Given that, we can sort the array of balloons by their ending position. 
We should shoot as to the right as possible, because since balloons are sorted, 
this gives you the best chance to take down more balloons with single arrow. 

Sort array by end position. 
Start shooting ballon at right position and see if that arrow burts next ballon and keep going.

SIMILAR - https://leetcode.com/problems/merge-intervals
SIMILAR - https://leetcode.com/problems/non-overlapping-intervals/
SIMILAR - https://leetcode.com/problems/meeting-rooms-ii/
*/

class Solution {
    public int findMinArrowShots(int[][] points) {
        if(points==null || points.length==0)
            return 0; 
        
        // Arrays.sort(points, (a,b) -> a[1]-b[1]);
        // Lawada bc bcz of a[1]-b[1] it mess up with integer range 
        // [[-2147483646,-2147483645],[2147483646,2147483647]]  => this gives ans as 1; but it should be 2 
        Arrays.sort(points, (a,b) -> Integer.compare(a[1], b[1]));
        int arrowPos = points[0][1];
        int arrows=1;
        
        for(int i=1; i<points.length; i++){
            if(arrowPos >= points[i][0]){   // same arrow is bursting this balloon
                continue;
            }
            
            arrows++;
            arrowPos = points[i][1];
        }
        
        return arrows;
    }
}

/*
if they ask give one Arrow position that can be shot whith maximum baloon burst?

start [1,2,7,10]
end   [6,8,12,16]

then pick a number from end 
binary search in start and find floor value -- the index +1 is that many balloons we can burst 
binary search in start and fins ceiling value ---  length - index is that many ballon we can burts 
but this can count double ... we will count same bubble twice. 
*/
