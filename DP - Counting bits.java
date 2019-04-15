
Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate 
the number of 1's in their binary representation and return them as an array.

Input: 2
Output: [0,1,1]
Example 2:

Input: 5
Output: [0,1,1,2,1,2]
// pattern is same after 2, 4, 8, 16 have 1 
// a[n] = 1 
// or 
// a[n] = a[n/2] + a[n%2]; 
class Solution {
    public int[] countBits(int num) {
        if(num<=0) 
            return new int[]{0}; // num <=0 should return [0] 
        
        int [] dp = new int[num+1];
        dp[0]=0;
        dp[1]=1;
        int sqr = 2;
        for(int i=2;i<=num;i++){  // here i though i%2==0 then dp[i]=1 which is wrong because 6%2 is 0.
            if(i==sqr){
                dp[i] =1;
                sqr *=2;
            } else{
                dp[i] = dp[i/2] + dp[i%2]; 
            }
        }
        return dp;
    }
}
