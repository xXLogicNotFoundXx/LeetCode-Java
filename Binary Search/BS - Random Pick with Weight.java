/*
https://leetcode.com/problems/random-pick-with-weight/
528. Random Pick with Weight
Medium
Given an array w of positive integers, where w[i] describes the weight of index i, 
write a function pickIndex which randomly picks an index in proportion to its weight.
*/
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
        int targ = rand.nextInt(tot);
        int lo = 0;
        int hi = wsum.length - 1;
        while (lo != hi) {
            int mid = (lo + hi) / 2;
            if (targ >= wsum[mid]) 
                lo = mid + 1;
            else
                hi = mid;
        }
        return lo;
    }
}
