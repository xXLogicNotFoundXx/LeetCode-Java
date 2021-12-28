/*
medium IMP
Facebook23 Amazon8 Flipkart3 google2 tiktok2
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
// BinarySearch: Time - O(nlogx), where x is the total weights, n is weights.length
class Solution {
    public int shipWithinDays(int[] weights, int D) {

        if(D<=0 || weights==null)
            return 0;

        int maxWeight=0;
        int totalWeight =0;

        for(int i=0;i<weights.length;i++){
            maxWeight = Math.max(maxWeight,weights[i]);
            totalWeight += weights[i]; // may be overflow
        }

        int left = maxWeight;
        int right = totalWeight;

        while(left<right){
            int mid = left + (right-left)/2;

            int days = daysToShip(weights,mid);
            /*
                DON'T Do this
            if(days==D)
                return mid;

            This will give you wrong answer:
            because there will be some x weight which will give the same days as D.
            However, this does not mean the x weigh is absolute minumum weight required to ship packages in D.
            so weep moving left=mid+1 keep the right=mid even if we find the weight for D days.
            In the end -  left and right will be equal.

            */

            if(days<=D) // mid could be the answer
                right=mid;  // we want floor value
            else
                left=mid+1;

        }

        return left; // right or left doesn't matter
    }

    int daysToShip(int[] weights, int cap){
        int count=0;
        int total=0;

        for(int i=0;i<weights.length;i++){
            total+=weights[i];
            if(total>cap){
                count++;
                total=weights[i];
            }
        }
        count++;

        return count;
    }
}
