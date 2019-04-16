https://leetcode.com/problems/longest-valid-parentheses/
Input : )()())
output: 4

/*
The workflow of the solution is as below.

1 Scan the string from beginning to end.
2 If current character is '(', push its index to the stack. 
    If current character is ')' and the character at the index of the top of stack is '(', we just find a
    matching pair so pop from the stack. Otherwise, we push the index of ')' to the stack.
3 If the stack is empty, the whole input string is valid.
4 else After the scan is done, the stack will only
    contain the indices of characters which couldnt be matched. 
    Then we take the diff between indices int stack one by one and calculate the maxlen.
    The diff between indices are the valid parenthesis substrings.
*/

class Solution {
    public int longestValidParentheses(String s) {
        int n = s.length(), longest = 0;
        Stack<Integer> st = new Stack<Integer>();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') 
                st.push(i);
            else {
                if (!st.empty() && s.charAt(st.peek()) == '(') 
                    st.pop();
                else
                    st.push(i); // didnt match push 
            }
        }
        
        if (st.empty()) 
            longest = n; // whole string is valid
        else {
            int a = n, b = 0;
            while (!st.empty()) {
                b = st.pop();
                longest = Math.max(longest, a-b-1);
                a = b;
            }
            longest = Math.max(longest, a);             // don't forget this one! 
        }
        return longest;
    }
}
