/*
Easy .. not many companies 

https://leetcode.com/problems/shortest-distance-to-a-character/

Given a string S and a character C, 
return an array of integers representing the shortest distance from the character C in the string.

Input: S = "loveleetcode", C = 'e'
Output: [3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0]
*/
class Solution {
    public int[] shortestToChar(String S, char C) {
        int[] res = new int[S.length()];
        int count = S.length();
        for(int i=0; i < S.length(); i++){
            if(S.charAt(i) == C) {
                count = 0;
            }
            res[i] = count;
            if(count!=S.length())
                count++;
        }
        count = S.length();
        for(int i=S.length()-1; i>=0; i--){
             if(S.charAt(i) == C) {
                count = 0;
            }
            res[i] = Math.min(count,res[i]);
            if(count!=S.length())
                count++;
        }
        return res;
    }
}
