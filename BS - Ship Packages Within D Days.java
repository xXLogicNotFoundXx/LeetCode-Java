/*
https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/
Capacity To Ship Packages Within D Days ( Order remains same in array)
Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within D days.
Input: weights = [1,2,3,4,5,6,7,8,9,10], D = 5
Output: 15
Explanation: 
A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
1st day: 1, 2, 3, 4, 5
2nd day: 6, 7
3rd day: 8
4th day: 9
5th day: 10
    we have to start with maxWieght on belt because that one has to go between 1-D days 
    if avg weight is greater thant maxWeight we have to start with avgWeight 
    Start filling the items if all items are filled in D date we have answer 
    else increment weight by 1 (or better by next item thats gonna go in day 1)
*/
class Solution {
    // BinarySearch: Time - O(nlogx), where x is the total weights, n is weights.length
    public int shipWithinDays(int[] weights, int D) {
        if (weights == null || weights.length == 0) {
            return 0;
        }
        
        int start = 0, end = 0;
        for (int w: weights) {
            end += w;
            start = Math.max(w, start);
        }
        while (start < end) {
            int mid = (start + end) / 2;
            int days = countDays(weights, mid);
            if (days > D) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }
    
    private int countDays(int[] weights, int capacity) {
        int count = 1, curWeight = 0;
        for (int w: weights) {
            curWeight += w;
            if (curWeight > capacity) {
                count++;
                curWeight = w;
            }
        }
        return count;
    }
}


