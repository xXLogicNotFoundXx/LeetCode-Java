/*
https://leetcode.com/problems/reverse-bits/
Reverse bits of a given 32 bits unsigned integer.
Input: 00000010100101000001111010011100
Output: 00111001011110000010100101000000
Input: 11111111111111111111111111111101
Output: 10111111111111111111111111111111
*/
public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result += n & 1;
            n >>>= 1;   // NOTE: must do unsigned shift
            if (i < 31) // NOTE: for last digit, don't shift! or else you gonna lose the very first digit 
                result <<= 1;
        }
        return result;
    }
}
