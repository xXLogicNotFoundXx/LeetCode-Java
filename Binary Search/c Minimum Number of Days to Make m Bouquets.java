/*
only google in last two years
1482. Minimum Number of Days to Make m Bouquets
https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/

Given an integer array bloomDay, an integer m and an integer k.
We need to make m bouquets. To make a bouquet, you need to use k adjacent flowers from the garden.
The garden consists of n flowers, the ith flower will bloom in the bloomDay[i] and then can be used in exactly one bouquet.
Return the minimum number of days you need to wait to be able to make m bouquets from the garden.
If it is impossible to make m bouquets return -1.

Input: bloomDay = [1,10,3,10,2], m = 3, k = 1
Output: 3
We need 3 bouquets each should contain 1 flower.
After day 1: [x, _, _, _, _]   // we can only make one bouquet.
After day 2: [x, _, _, _, x]   // we can only make two bouquets.
After day 3: [x, _, x, _, x]   // we can make 3 bouquets. The answer is 3.

O(Nlog(maxA))
*/
class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        int left=0, right=0;
        for( int n : bloomDay){
            right = Math.max(right,n);
        }

        // Nice!
        if(m*k > bloomDay.length)
            return -1;

        while(left < right){
            int mid = left + (right-left)/2;

            int bq = makeBouquets(bloomDay, k, mid);

            //System.out.println("left="+left+" right="+right);
            //System.out.println("Mid="+mid+" bq="+bq);
            if(bq>=m){
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return  left;
    }

    int makeBouquets(int[] b, int k, int day){
        int prefixSum = 0;
        int bouquets = 0;
        for(int i=0;i < b.length;i++){

            if(b[i]<=day){
                prefixSum++;
            } else {
                prefixSum = 0;
            }

            if(prefixSum==k){
                bouquets++;
                prefixSum=0;
            }
            //System.out.println("i="+i+" bq="+bouquets);
        }

        return bouquets;
    }
}
