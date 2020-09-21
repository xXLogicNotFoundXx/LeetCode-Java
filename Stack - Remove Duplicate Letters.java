/*

TODO :

Remove Duplicate Letters
Given a string which contains only lowercase letters, remove duplicate letters so that every letter appears once and only once. 
You must make sure your result is the smallest in lexicographical order among all possible results.

Example 1:

Input: "bcabc"
Output: "abc"
Example 2:

Input: "cbacdcbc"
Output: "acdb"
Note: This question is the same as 1081: https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/
*/

class Solution {
    /*
    273 / 286 test cases passed.
    DOESNT work 
    Input : "abacb"
    Output :"acb"
    Expected :"abc"
    */
    public String removeDuplicateLetters(String s) {
        // Count occurance of each char
        HashMap<Character,Integer> map = new HashMap<>();
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        
        // Push chars on stack and try to keep the stack in increasing order.
        Deque<Character> stack = new ArrayDeque<>();
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            
            // if this char is less than peek() remove that char iff top is repeated char 
            // keep doin it in while loop: example input dcbadcb
            while(!stack.isEmpty() && stack.peek()>ch && map.get(stack.peek()) > 1){
                // Apparently we cant blindly remove the peek()
                char c = stack.pop();
                map.put(c, map.get(c)-1);
            }
            
            stack.push(ch);
        }
        
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            char ch = stack.pop();
            if(map.get(ch)>1){
                map.put(ch, map.get(ch)-1);
            } else {
                sb.append(ch);
            }
        }
        
        return sb.reverse().toString();
    }
}
