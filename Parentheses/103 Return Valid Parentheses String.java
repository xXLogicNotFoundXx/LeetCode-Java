/*
Medium - VIMP
Facebook218 Bloomberg6 Amazon4 Microsoft2 tiktok2 Snapchat2

https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/

Given a string s of '(' , ')' and lowercase English characters.
Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions )
so that the resulting parentheses string is valid and return any valid string.



Input: s = "lee(t(c)o)de)"
Output: "lee(t(c)o)de"
Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.

Input: s = "a)b(c)d"
Output: "ab(c)d"

Input: s = "))(("
Output: ""
Explanation: An empty string is also valid.
*/
// Test Case think of this :
// ((()
// (((()))(((
// ))((

class Solution {
    public String minRemoveToMakeValid(String s) {
        if(s==null || s.isEmpty())
            return s;

        Deque<Integer> stack = new ArrayDeque<Integer>();

        for(int i=0;i<s.length();i++){

            if(s.charAt(i)=='(')
              stack.push(i);

            if(s.charAt(i)==')'){
                if(stack.isEmpty())
                    stack.push(i);
                else {
                    if(s.charAt(stack.peek()) == '(')
                        stack.pop();
                    else
                        stack.push(i);
                }
            }


        }

        /*
        StringBuilder sb = new StringBuilder(s);
        while(!stack.isEmpty()){
           sb.deleteCharAt(stack.pop());            // <- THIS is O(n) operation can lead to O(N^2) if all indices are in the stack.
        }
        return sb.toString();
        */

        StringBuilder ans = new StringBuilder();
        int i=0;
        while(i<s.length()){
            if(!stack.isEmpty() && stack.peekLast().equals(i)) // <- THIS deque's first number is peekLast().. bcz we used push and pop
              stack.removeLast();
            else
              ans.append(s.charAt(i));
            i++;
        }
        return ans.toString();
    }
}
