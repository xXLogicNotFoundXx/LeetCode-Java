/*
https://leetcode.com/problems/3sum-with-multiplicity/
Given an integer array A, and an integer target, return the number of tuples i, j, k  
such that i < j < k and A[i] + A[j] + A[k] == target.

As the answer can be very large, return it modulo 10^9 + 7.

NOTE: 
You would think sorting will mess up the order of i,j,k indexes 
This is USELESS detail bcz i,j,k can be reorder to any combination and it doesnt matter.
A[i] + A[j] + A[k] can be A[k] + A[i] + A[j]
Moreover they are not even asking to return tuples of i,j,k

Time : O(n^2)
Space : O(n^2)    
*/

class Solution {
    // This one is N^2 takes  599 ms 
    public int threeSumMulti(int[] A, int target) {
        HashMap<Integer, Integer> count = new HashMap<>();
        int n = A.length;
        long result = 0;
        
        for(int i = 0; i + 1 < n; i++){
            for(int j = i + 1; j < n; j++){
                if(count.containsKey(target - A[i] - A[j])){
                    result += ( 1L * count.get(target - A[i] - A[j]) ); 
                    //System.out.println(result);
                }
            }
            count.put(A[i], count.getOrDefault(A[i], 0) + 1);
        }
        
        // System.out.println(result);
        return (int) (result%(1000000000 + 7));
    }
}
