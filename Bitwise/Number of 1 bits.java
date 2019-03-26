/*
https://leetcode.com/problems/number-of-1-bits/
Write a function that takes an unsigned integer and return the number of '1' bits it has (also known as the Hamming weight).

*/
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int z) {
        int count =0;
        while(z != 0) {
          count += z & 1;
          z = z >>> 1;   // we used >>> for unsigned right shift
        } 
        return count;
    }
}
