
Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate 
the number of 1's in their binary representation and return them as an array.

Input: 2
Output: [0,1,1]
Example 2:

Input: 8
Output: [0,1,1,2,1,2,2,3,1]
        
// 2, 4, 8, 16 have only one 1  
// a[n] = a[n/2] + n%2; 
class Solution {
    // 2, 4, 8, 16 have only one 1  
    // a[n] = a[n/2] + n%2; 
    public int[] countBits(int num) {
        if(num<=0) 
            return new int[]{0}; // num <=0 should return [0] 
        
        int [] dp = new int[num+1];
        dp[0]=0;
        dp[1]=1;
        for(int i=2;i<=num;i++){  
           dp[i] = dp[i/2] + i%2;  
        }
        return dp;
    }
}

