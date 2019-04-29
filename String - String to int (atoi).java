/*
https://leetcode.com/problems/string-to-integer-atoi/
Input: "   -42"
Output: -42
Input: "4193 with words"
Output: 4193
Input: "words and 987"
Output: 0
Input: "-91283472332"
Output: -2147483648
*/
class Solution {
    public int myAtoi(String str) {
        int index = 0, sign = 1, total = 0;
        //1. Empty string
        if(str==null || str.length() == 0) return 0;

        //2. Remove Spaces
        while(index < str.length() && str.charAt(index) == ' ')
            index ++;

        //3. Handle signs
        if(index < str.length()  && (str.charAt(index) == '+' || str.charAt(index) == '-')){
            sign = str.charAt(index) == '+' ? 1 : -1;
            index ++;
        }

        //4. Convert number and avoid overflow
        while(index < str.length()){
            int digit = str.charAt(index) - '0';
            if(digit < 0 || digit > 9) break;

            //check if total will be overflow 
            // We are dividing by 10 to check for overflow because later we are multiplying by 10 in the statement
            if(Integer.MAX_VALUE/10 < total || Integer.MAX_VALUE/10 == total && Integer.MAX_VALUE %10 < digit)
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;

            total = 10 * total + digit;
            index ++;
        }
        return total * sign;
    }
}
