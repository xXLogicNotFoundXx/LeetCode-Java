/*
https://leetcode.com/problems/strobogrammatic-number-iii/
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.
Input: low = "50", high = "100"
Output: 3 
Explanation: 69, 88, and 96 are three strobogrammatic numbers.
*/
class Solution {
  
    private static final char[][] PAIRS = 
        new char[][] {{'0', '0'}, {'1', '1'}, {'6', '9'}, {'8', '8'}, {'9', '6'}};
    
    public int strobogrammaticInRange(String low, String high) {
        if (low == null || high == null || low.length() > high.length()
            || (low.length() == high.length() && low.compareTo(high) > 0)) {
            return 0;
        }
        int count = 0;
        for (int len = low.length(); len <= high.length(); len++) {
            count += dfs(low, high, new char[len], 0, len - 1);
        }
        return count;
    }
    
    private int dfs(String low, String high, char[] ch, int left, int right) {
        
        if (left > right) {
            String s = new String(ch);
            if (   (ch.length == low.length()  && s.compareTo(low)  < 0)
                || (ch.length == high.length() && s.compareTo(high) > 0)) {
                return 0;
            } else {
                return 1;
            }
        }
        
        int count = 0;
        for (char[] p : PAIRS) {
            ch[left] = p[0];
            ch[right] = p[1];
            
            
            if (left!=right && left == 0 && p[0] == '0') {
                continue; //don't start with 0
            }
            
            if (left == right && (p[0] == '6' || p[0] == '9')) {
                continue; //don't put 6/9 at the middle of string.
            }
            
            count += dfs(low, high, ch, left + 1, right - 1);
        }
        return count;
    }
}
