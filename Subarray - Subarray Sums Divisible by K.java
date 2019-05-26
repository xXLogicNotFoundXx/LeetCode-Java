/*
https://leetcode.com/problems/subarray-sums-divisible-by-k/
Given an array A of integers, return the number of (contiguous, non-empty) subarrays that have a sum divisible by K.
Input: A = [4,5,0,-2,-3,1], K = 5
Output: 7
Explanation: There are 7 subarrays with a sum divisible by K = 5:
[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
*/
class Solution {
    // Prefix Sum is one technique we need to solve this problem, but it's not enough. 
    // we dont know the exact numbr we need to it becomes N^2TLE 
    // we need only mod remaining to convert that into O(n) problem. Note -4%5 = -4 
    public int subarraysDivByK1(int[] A, int K) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int count = 0, sum = 0;
        for(int a : A) {
            sum = (sum + a) % K;
            if(sum < 0) sum += K;  // Because -1 % 5 = -1, but we need the positive mod 4
            count += map.getOrDefault(sum, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
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
    
    // PREFIX SUM : TLE - Time limit exceeded. 
    // N^2 calculate sum 0-n i.e. prefix sum at every element 
    // and check if any (sum[i]-sum[j])%K==0 .. j<i  
    public int subarraysDivByK2(int[] A, int K) {
        if(A==null || A.length==0)
            return 0;
        int count = 0;
        if(A[0]%K==0)
            count++;
        for(int i=1;i<A.length;i++){
            A[i] = A[i-1] +  A[i];  
            if(A[i]%K==0)
                count++;
            for(int j=0;j<i;j++){
                if((A[i]-A[j])%K==0)
                  count++; 
            }
        }
        return count;
    }

}
