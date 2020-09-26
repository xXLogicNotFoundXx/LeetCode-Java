/*

316. Remove Duplicate Letters
https://leetcode.com/problems/remove-duplicate-letters/solution/

Given a string which contains only lowercase letters, remove duplicate letters so that every letter appears once and only once. 
You must make sure your result is the smallest in lexicographical order among all possible results.

Example 1:

Input: "bcabc"
Output: "abc"
Example 2:

Input: "cbacdcbc"
Output: "acdb"
Note: This question is the same as 1081: https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/


SAME PROBLEM :

1081. Smallest Subsequence of Distinct Characters
https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/

Return the lexicographically smallest subsequence of text that contains all the distinct characters of text exactly once.

Input: "cdadabcc"
Output: "adbc"

Input: "abcd"
Output: "abcd"

*/

// DOES not work 
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
            
            // if this char is less than peek() then remove peek() iff peek() is repeated char lateron
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

// LeetCode solution 
class Solution {
    public String removeDuplicateLetters(String s) {

        Stack<Character> stack = new Stack<>();

        // this lets us keep track of what's in our solution in O(1) time
        HashSet<Character> seen = new HashSet<>();

        // this will let us know if there are any more instances of s[i] left in s
        HashMap<Character, Integer> last_occurrence = new HashMap<>();
        for(int i = 0; i < s.length(); i++) last_occurrence.put(s.charAt(i), i);

        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            // we can only try to add c if it's not already in our solution
            // this is to maintain only one of each character
            if (!seen.contains(c)){
                // if the last letter in our solution:
                //     1. exists
                //     2. is greater than c so removing it will make the string smaller
                //     3. it's not the last occurrence
                // we remove it from the solution to keep the solution optimal
                while(!stack.isEmpty() && c < stack.peek() && last_occurrence.get(stack.peek()) > i){
                    seen.remove(stack.pop());
                }
                seen.add(c);
                stack.push(c);
            }
        }
    StringBuilder sb = new StringBuilder(stack.size());
    for (Character c : stack) sb.append(c.charValue());
    return sb.toString();
    }
}
