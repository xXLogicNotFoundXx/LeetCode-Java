/*
easy
lkd7,amz6,apl3,msft2
https://leetcode.com/problems/sqrtx/

Implement int sqrt(int x).
Compute and return the square root of x, where x is guaranteed to be a non-negative integer.

8 => 2
4 => 2
9 => 3
*/
class Solution {
    /*
        we want the next lowest(Floor value) or equal ....
        so we should NOT move L,  L=mid because that "L" could be our ans
        & r should be r=mid-1


        Rule of thumb
        m = r - (r-l)/2                     m = l + (r-l)/2
        with                                with
        l = m                               l = m+1
        r = m - 1                           r = m

        (Finding Floor Value or equal)      (Finding Ceiling Value or equal)

        This can prevent m from getting stuck at r (or l) after the updating step.

        We want floor value and left could be answer.
    */

    public int mySqrt(int x) {
        if (x == 0)
            return 0;

        int left = 1, right = x;
        int ans =0;

        while (left<right) {
            int mid = right - (right - left)/2;

            int div =  x/mid;

            if (div < mid) { // mid=10 -> 81/10 -> div=8 which is less than mid
                right = mid - 1;
            } else {
                left = mid ;
            }
        }

        return left;
    }

}
