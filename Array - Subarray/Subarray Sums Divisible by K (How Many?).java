/*
https://leetcode.com/problems/subarray-sums-divisible-by-k/
Given an array A of integers, return the number of (contiguous, non-empty) subarrays that have a sum divisible by K.
Input: A = [4,5,0,-2,-3,1], K = 5
Output: 7
Explanation: There are 7 subarrays with a sum divisible by K = 5:
[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
*/

class Solution {
    // PREFIX SUM : Time limit exceeded. 
    // O(N^2) O(1) calculate prefixSum staring from all indices
    // and check if the prefix sum is divisible by K
    public int subarraysDivByK1(int[] A, int K) {
        if(A==null || A.length==0)
            return 0;
        
        int count =  0;
        int sum =0;
        for(int i=0;i<A.length;i++){
            sum=0;
            for(int j=i;j<A.length;j++){
                sum+=A[j];
                if(sum%K == 0 )
                  count++; 
            }
        }
        return count;
    }
    
    // PrefixSum is one technique we need to solve this problem, but it's not enough. 
    // we need only mod remaining to convert that into O(n) problem. 
    // Note -4%5 = -4  (mod operator retains the sign of 1st operand. 4%-5 = 4 )
    public int subarraysDivByK(int[] A, int K) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int count = 0, prefixSum = 0;
        for(int a : A) {
            prefixSum = prefixSum + a;
            // you may think Math.abs(a) would work to get +ve mod
            // but here is the i/p = [1,-10,5]  K=9 and it doesnt work.
            // Morever, the Math.abs(a) would give wrong prefixSum and wrong mod. 
            
            int mod = prefixSum % K;
            if(mod < 0) {  // why?
                mod += K;  // Because -1 % 5 = -1, but we need the positive mod 4
                // thik [-1,5] prefixSum is gonna be 4. We should have 4 in the map when we process -1. 
            }
            
            if(map.containsKey(mod))
                count += map.get(mod);
            
            map.put(mod, map.getOrDefault(mod, 0) + 1);
        }
        return count;
    }
    /*
    Input: A = [4,5,0,-2,-3,1], K = 5
              Map  
    step 1 : {0:1}          a=4    sum =4  mod=4  result = 0+0 =0
    step 2 : {0:1,4:1}      a=5    sum =9  mod=4  result = 0+1 =1
    step 3 : {0:1,4:2}      a=0    sum =9  mod=4  result = 1+2 =3
    step 4 : {0:1,4:3}      a=-2   sum =7  mod=2  result = 3+0 =3  
    step 6 : {0:1,4:3,2:1}  a=-3   sum =4  mod=4  result = 3+3 =6
    step 7 : {0:1,4:4,2:1}  a=1    sum =5  mod=0  result = 6+1 =7
    */
}
