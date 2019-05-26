/*
https://leetcode.com/problems/3sum-with-multiplicity/
Given an integer array A, and an integer target, return the number of tuples i, j, k  such that i < j < k and A[i] + A[j] + A[k] == target.
As the answer can be very large, return it modulo 10^9 + 7.
*/
class Solution {
    // This one is N^2 takes  599 ms 
    public int threeSumMulti1(int[] A, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        
        int res = 0;
        int mod = 1000000007;
        for (int i = 0; i < A.length; i++) {
            // A[i] must be considered for result before we calculate all combination sum and we dont 
            // want to consider that sum bcz that includesA[i]
            res = (res + map.getOrDefault(target - A[i], 0)) % mod;
            for (int j = 0; j < i; j++) {
                int temp = A[i] + A[j];
                map.put(temp, map.getOrDefault(temp, 0) + 1);
            }
        }
        return res;
    }
    
    /*
    To calculate combinations, we will use the formula C(n,r)   nCr = n! / r! * (n - r)!, Also noted as C(n,r).
        where n represents the total number of items, and r represents the number of items being chosen at a time.
    */ 
    public int threeSumMulti(int[] A, int target) {
        // This one is worst N^2  but takes  60ms 
        Arrays.sort(A);
        int m = 1000000007;
        int res = 0;
        for (int i = 0; i < A.length - 2; ++i) {
            int j = i + 1;
            int k = A.length - 1;
            while (j < k) {
                if (A[j] + A[k] < target - A[i]) { ++j; }
                else if (A[j] + A[k] > target - A[i]) { --k; }
                else {
                    if (A[j] == A[k]) {
                        // If A[j...k] are all equal, then there are combinations of C(k - j + 1, 2) 
                        // combinations that meet the requirement.
                        // let this sink in so we did n*n-1/2   ... n-2! is cancelled out look at the formula.
                        res = (res + (k - j + 1) * (k - j) / 2) % m; 
                        break;
                    }
                    int l = 1, r = 1;
                    // count the number that are same as A[j] and A[k]
                    while (j + l < k && A[j + l] == A[j]) 
                        l++;  // l: number of elements equal to A[j].
                    while (j < k - r && A[k - r] == A[k]) 
                        r++; // r: number of elements equal to A[k].
                    
                    res = (res + l * r) % m; // found l * r cases that meet the requirement.
                    j += l; // forward j by l steps.
                    k -= r; // backward k by r steps.
                }
            }
        }
        return res;
    }
}
