/*
https://leetcode.com/problems/sqrtx/

Implement int sqrt(int x).
Compute and return the square root of x, where x is guaranteed to be a non-negative integer.

8 => 2
4 => 2
9 => 3
*/
class Solution {
    // think of 81  = 9
    // think of 101 = 10 
    // think of 110 = 10
    public int mySqrt(int x) {
        if (x == 0)
            return 0;
        
        int left = 1, right = x;
        int ans =0;
        
        while (left<=right) {
            int mid = left + (right - left)/2;
            
            int div =  x/mid;
            
            if(mid == div){
                return mid;
            }
            
            if (div < mid) { // 81/10 = 8 which is less than mid 
                right = mid - 1;
            } else {           // 81/8 = 10 which is greater than mid  so it could be ans
                ans = mid;
                left = mid + 1;
            }
        }
        
        return ans;
    }
}
