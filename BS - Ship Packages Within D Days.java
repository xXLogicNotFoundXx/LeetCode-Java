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
// O (totalWeight*D*N)
class Solution {
    public int shipWithinDays(int[] weights, int D) {
        int maxWeight =0;
        int avgWeight =0;
        for(int w : weights){
            maxWeight = Math.max(w,maxWeight);
            avgWeight += w;
        }
        avgWeight = avgWeight/D;
        maxWeight = Math.max(avgWeight,maxWeight);
        for(int min=maxWeight; min<avgWeight*D+1; min++){
            int index=0;
            for(int j=0;j<D;j++){
                for(int sum=0;index<weights.length;index++){
                    if(sum + weights[index] <= min)
                        sum += weights[index];
                    else
                        break;
                }
                if(index==weights.length)
                    return min;
            }
        }
        return 0;
    }
}

/*
// Better solution is with binary search O(log(totalWeight) * D * N)
class Solution {
    public int shipWithinDays(int[] weights, int D) {
        int left = 0, right = 0;
        
        for (int w: weights) {
            left = Math.max(left, w);
            right += w;
        }
        
        while (left < right) {
            int mid = (left + right) / 2;
            int dNeeded = 1, cur = 0;
            for (int w: weights) {
                if (cur + w > mid) {
                    dNeeded += 1;
                    cur = 0;
                }
                cur += w;
            }
            if (dNeeded > D) 
                left = mid + 1; 
            else 
                right = mid;
        }
        return left;
    }
}
*/
