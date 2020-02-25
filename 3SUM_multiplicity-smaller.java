/*
https://leetcode.com/problems/3sum-with-multiplicity/
Given an integer array A, and an integer target, return the number of tuples i, j, k  
such that i < j < k and A[i] + A[j] + A[k] == target.
As the answer can be very large, return it modulo 10^9 + 7.
Time : O(n^2)
Space : O(n^2)    
*/

class Solution {
    // This one is N^2 takes  599 ms 
    public int threeSumMulti(int[] A, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        
        int res = 0;
        for (int i = 0; i < A.length; i++) {
            // A[i] must be considered for result before we calculate all combination sum and we dont 
            // want to consider that sum bcz that includesA[i]
            res = (res + map.getOrDefault(target - A[i], 0)) % 1000000007;
            for (int j = 0; j < i; j++) {
                int temp = A[i] + A[j];
                map.put(temp, map.getOrDefault(temp, 0) + 1);
            }
        }
        return res;
    }
}

/*
https://leetcode.com/problems/3sum-smaller/
Given an integer array A, and an integer target, return the number of tuples i, j, k  
such that i < j < k and A[i] + A[j] + A[k] < target.
*/
