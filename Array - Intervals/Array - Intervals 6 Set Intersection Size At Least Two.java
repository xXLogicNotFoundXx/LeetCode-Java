/*

Hard - not that oftenly asked. only uber2 in last year

https://leetcode.com/problems/set-intersection-size-at-least-two/

An integer interval [a, b] (for integers a < b) is a set of all consecutive integers from a to b, including a and b.
Find the minimum size of a set S such that for every interval A in intervals, the intersection of S with A has a size of at least two.

For each inteval A , S would have 2 numbers that are in the interval A. 

Input: intervals = [[1,3],[1,4],[2,5],[3,5]]
Output: 3
Explanation: Consider the set S = {2, 3, 4}.

For each interval A, there are 2 elements from S in the interval.

Also, there isn't a smaller size set that fulfills the above condition.
Thus, we output the size of this set, which is 3.


We can sort by end points. since every end point is e >=s, it is better to choose
endPoint and endPoint-1 as a set value. which has higher chances to be in "next inteval".
By using TreeSet we can get floor and ceiling value we can try to find two numbers that are in the interval
ceiling(start) and floor(end)
OR
you can sort by start timing and iterate from last interval and add start & start+1

    Sort -> n*LogN
    For each interval we find ceiling and floor N*logN
Time : O (N*LogN)

*/
class Solution {
    public int intersectionSizeTwo(int[][] intervals) {
        Arrays.sort(intervals, (a,b) -> a[1]==b[1] ? a[0]-b[0] : a[1]-b[1]);

        TreeSet<Integer> set = new TreeSet<>();
        set.add(intervals[0][1]); // end
        set.add(intervals[0][1]-1); // end -1

        for(int i=0;i<intervals.length;i++){
            int start = intervals[i][0];
            int end = intervals[i][1];

            Integer floor = set.floor(end);
            if(floor<start){
                set.add(end);
                set.add(end-1);
                continue;
            }

            // At this point.
            // we found one number in the interval

            // check ceiling of a start is in interval and should be different that floor
            // if ceiling==floor we need different number
            Integer ceiling = set.ceiling(start);
            if(ceiling==floor){

                if(floor==end)
                    set.add(end-1);
                else
                    set.add(end);
                continue;
            }
        }

        return set.size();
    }
}

// all we really need largest and second largest number in set
// if they are both in the interval we move forward
// else we add end and/or end-1 and update largest and second largest number
