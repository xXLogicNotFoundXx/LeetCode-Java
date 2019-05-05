/*
https://leetcode.com/problems/longest-substring-without-repeating-characters/
Input: "abcabcbb"
Output: 3 
Explanation: The answer is "abc", with the length of 3. 

Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.

Input: "pwwkew"
Output: 3
Explanation: The answer is "wke",
*/

class Solution {
    public int lengthOfLongestSubstring(String str) {
        
        if(str==null) return 0;
        Set<Character> set = new HashSet<>();
        int start = 0, max = 0;
        
        for(int i=0;i<str.length();i++){
            char ch = str.charAt(i);
            
            while(!set.isEmpty() && set.contains(ch)){
                set.remove(str.charAt(start));
                start++;
            }
            
            set.add(ch);
            max = Math.max(max,set.size());
        }
        return max;
    }
}
