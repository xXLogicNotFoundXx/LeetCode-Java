/*
Medium VIMP
Facebook73 Google12 Uber6 Amazon6 Apple6 Yelp4 Microsoft4 ...

https://leetcode.com/problems/random-pick-with-weight/
528. Random Pick with Weight
Medium
Given an array w of positive integers, where w[i] describes the weight of index i,
write a function pickIndex which randomly picks an index in proportion to its weight.

Trick question ...
To put it simple, the task is to do sampling with weight.
[1, 9], when we pick up a number out of it,
the chance is that 9 times out of 10 we should pick the number 9 as the answer.
*/

// pickIndex - O(n)
class Solution {

    int []wsum;
    int totalSum = 0;
    Random rand = new Random();

    // O(n)
    public Solution(int[] w) {
        wsum = new int[w.length];
        for (int i=0;i<w.length;i++) {
            totalSum += w[i];
            wsum[i] = totalSum;
        }
    }

    // O(n)
    public int pickIndex() {
        int target = rand.nextInt(totalSum); // 0-totalSum inclusive

        for( int i=0; i<wsum.length; i++){
            if(target<wsum[i])                 // 0-totalSum inclusive hence only < and not <=
                return i;
        }

        return wsum.length;
    }
}


// pickIndex - O(logn)
class Solution {
    int []wsum;
    int tot = 0;
    Random rand = new Random();

    public Solution(int[] w) {
        wsum = new int[w.length];
        for (int i=0;i<w.length;i++) {
            tot += w[i];
            wsum[i] = tot;
        }
    }

    public int pickIndex() {
        int targ = rand.nextInt(tot);  // <- this
        int lo = 0;
        int hi = wsum.length - 1;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (targ >= wsum[mid])
                lo = mid + 1;  // we want ceiling value
            else
                hi = mid;
        }
        return lo;
    }
}
