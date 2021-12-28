/*
Medium
VIMP
FB-91 lkd-7 msft7 apl5 google4 and some more in last 6 months
https://leetcode.com/problems/powx-n/

50. Pow(x, n)
Implement pow(x, n), which calculates x raised to the power n (i.e. x^n).

x ^0         = 1
x ^-3        = (1/x)^3
(2/10) ^-3   = (10/2) ^3

1. if n is 0, we return 1.
2. if n is -ve then we do the x=1/x and make n +ve

Now,
if n is even : we want to compute 2^4
    if we have result of 2^2 then we just multiply same number and we have 2^4
if n is odd :  we want to compute 2^3
    then we have to multiply that extra x.
*/

class Solution {

    public double myPow(double x, int n) {

        if (n < 0) {
            x = 1 / x;
            n = -n;
        }

        return fastPow(x, n);
    }

    private double fastPow(double x, int n) {

        if (n == 0) {
            return 1.0;
        }

        double half = fastPow(x, n / 2);

        if (n % 2 == 0) {
            return half * half;
        } else { // what if n is odd  n=5   5/2 = 2
            // so we miss that one count
            // Note we are multiplying by that extra 'x' that we missed 
            return half * half * x;
        }
    }
}
