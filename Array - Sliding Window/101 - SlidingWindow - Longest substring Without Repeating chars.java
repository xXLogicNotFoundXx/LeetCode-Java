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
                set.remove(str.charAt(start)); // remove the char at start position
                start++;
            }

            set.add(ch);
            max = Math.max(max,set.size()); // or i-start+1
        }
        return max;
    }
}


/*
Q Given a string str and an integer k, your task is to split str into a minimal possible number of substrings 
so that there are no more than k different symbols in each of them. Return the minimal possible number of such substrings.
eg: s = "aabeefegeeccrr" k = 3 Output = 3

*/
int solve(String string) {
    int ans = 1;
    Set<Character> set = new HashSet<>();
    for(int i = 0; i < string.length(); i++) {

	    if(set.contains(string.charAt(i))
	        continue;

        if(set.size() == K) {
           set = new HashSet<>();
           ans += 1;
        }

        set.add(string.charAt(i));
    }
    return ans;
}
