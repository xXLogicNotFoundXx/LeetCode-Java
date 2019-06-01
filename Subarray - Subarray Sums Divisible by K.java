/*
https://leetcode.com/problems/subarray-sums-divisible-by-k/
Given an array A of integers, return the number of (contiguous, non-empty) subarrays that have a sum divisible by K.
Input: A = [4,5,0,-2,-3,1], K = 5
Output: 7
Explanation: There are 7 subarrays with a sum divisible by K = 5:
[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]

We can all agree that for an array int[] A, where N = len(A), that there are N prefix sums.
prefix[0] = A[0], prefix[1] = A[0] + A[1], ... prefix[i] = A[0] + ... + A[i].

Then to calculate how many subarrays are divisible by K is logically equivalent to saying, how many ways can we pair up all prefix sum pairs (i,j) where i < j
such that (prefix[j] - prefix[i]) % K == 0.

Just from that information alone we easily get a O(n^2) solution.
Compute all prefix sums, then check all pair to see if k divides the difference between them.

However, if we just exploit some information w.r.t to the remainder of each prefix sum we can manipulate this into a linear algorithm. Here's how.

Number Theory Part
I noted above that we need to find all prefix sum pairs (i,j) such tha (p[j] - p[i]) % K == 0.
But this is only true, if and only if p[j] % K == p[i] % K
Why is this?

According the the division algorithm we can express p[j] and p[i] in the following way.
p[j] = bK + r0 where 0 <= r0 < K
p[i] = aK + r1 where 0<= r1 < K

Then p[j] - p[i] = (b*K + r0) - (a*K + r1)
= b*K - a*K + r0 - r1 = K*(b-a) + r0 - r1
Again: p[j] - p[i] = K*(b-a) + (r0-r1), in other words
K only divides p[j] - p[i] iff r0-r1 = 0 <-> r0 = r1 QED
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
