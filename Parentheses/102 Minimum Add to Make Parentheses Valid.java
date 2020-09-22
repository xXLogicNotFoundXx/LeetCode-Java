/*
https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/
Input: "())"
Output: 1

Input: "((("
Output: 3

Input: "()"
Output: 0

Input: "()))(("
Output: 4
*/
class Solution {
    // Time and Space O(n)
    public int minAddToMakeValid1(String S) {
        Deque<Character> stack = new ArrayDeque<>();
        
        for(int i=0;i<S.length();i++){
            char ch = S.charAt(i);
            if(ch=='(')
                stack.push('(');
            else if(ch==')'){
                
                if(stack.isEmpty() || stack.peek()!='(')
                    stack.push(')');
                else 
                    stack.pop();
            }
        }
        return stack.size();
    }
    
    // Time O(n) & constant space
    public int minAddToMakeValid(String S) {
        int rmOpen=0, rmClose =0;
        
        for(int i=0;i<S.length();i++){
            char ch = S.charAt(i);
            if(ch=='(')
                rmOpen++;
            else{
                if(rmOpen>0)
                    rmOpen--;
                else
                    rmClose++;
            }
        }
        
        return rmOpen +rmClose;
    }
}
