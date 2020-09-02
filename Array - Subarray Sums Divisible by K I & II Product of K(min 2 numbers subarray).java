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
    // O(N^2) O(1) calculate prefixSum for all indices  
    // and check if any (sum[i]-sum[j])%K==0 .. j<i  
    public int subarraysDivByK2(int[] A, int K) {
        if(A==null || A.length==0)
            return 0;
        
        int count = A[0]%K==0 ? 1 : 0;
    
        for(int i=1;i<A.length;i++){
            A[i] = A[i-1] +  A[i];  
            
            if(A[i]%K==0)
                count++;
            
            for(int j=0;j<i;j++){
                if( (A[i]-A[j])%K == 0 )
                  count++; 
            }
        }
        return count;
    }
}

class Solution {
    // Prefix Sum is one technique we need to solve this problem, but it's not enough. 
    // we need only mod remaining to convert that into O(n) problem. Note -4%5 = -4 
    public int subarraysDivByK(int[] A, int K) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // IMP
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
            
            prefixSum = mod; // To avoid overflow. (Well you can just have prefixSum and remove the mod variable.)
            
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


/*        FUCKED UP CORNER CASES - but idea is same as to take mod into consideration 
https://leetcode.com/problems/continuous-subarray-sum/
Given a list of non-negative numbers and a target integer k, write a function to check
if the array has a continuous subarray of size at least 2 that sums up to a multiple of k, 
that is, sums up to n*k where n is also an integer.

Input: [23, 2, 4, 6, 7],  k=6
Output: True
Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
*/

/*
No doubt the edge cases were the trickiest part of this problem. 
I did not expect to see k being zero or negative or that a factor would be times zero. 
Anyhow, you definitely end up with a few more if statements!
    Some damn it! test cases:
    [0,0], 0 -> true
    [0], 0 -> false
    [0, 0], 100 -> true
    [1,5], -6 -> true
    [1,0], 2 -> false
    [0,1,0], -1 -> true
    [2,2], 4 -> true 
*/
class Solution {
    
    
    // Fucked up corner cases.
    public boolean checkSubarraySum(int[] nums, int k) {
        if(nums==null || nums.length<=1)
            return false;
        
        // [0,0], 0 -> true & [0, 0], 100 -> true
        // Two continuous 0 will form a subarray 
        // which has sum = 0. 0 * k == 0 will always be true.
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == 0 && nums[i + 1] == 0) 
                return true;
        }
        
        // k can't be "0" any longer.
        if (k == 0) 
            return false;
        
        // [1,5], -6 -> true
        // Let's make +ve k so the mod is not -ve 
        if (k < 0) 
            k = -k;
        
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,-1); // -1 bcz to get [2,2], 4 -> true  
        
        int prefixSum =0;
        for(int i=0;i<nums.length;i++){
            
            prefixSum +=nums[i];
            int mod = prefixSum%k;
            
            if(map.containsKey(mod)){
                if(map.get(mod) < i-1) 
                    return true;
            } else{
                map.put(mod,i);
            }
        }
        return false;
    }
    
}
