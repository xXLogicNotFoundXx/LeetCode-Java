/*
https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/ 
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
        
        StringBuilder sb = new StringBuilder(s);
        Deque<Integer> stack = new ArrayDeque<Integer>();
        
        for(int i=0;i<s.length();i++){
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
            else if(s.charAt(i)=='(')
                stack.push(i);     
        }
        
        while(!stack.isEmpty())
            sb.deleteCharAt(stack.pop());
        
        return sb.toString();
    }
}
