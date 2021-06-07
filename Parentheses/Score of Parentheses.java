/*
https://leetcode.com/problems/score-of-parentheses/
Given a balanced parentheses string S, compute the score of the string based on the following rule:

() has score 1
AB has score A + B, where A and B are balanced parentheses strings.
(A) has score 2 * A, where A is a balanced parentheses string.

Input: "()"
Output: 1


Input: "(())"
Output: 2


Input: "()()"
Output: 2

Input: "(()(()))"
Output: 6

"(())" 2
"((()))" 4
"(((())))" 8
"(((()))())" 10 
"(((((())))))" 32
*/
class Solution {
    int i=0;
    public int scoreOfParentheses(String S) {
        
        int res=0;
        
        while(i<S.length()){
            char ch = S.charAt(i);
            
            i++; 
            
            if ( ch=='(' && i != S.length()  && S.charAt(i)==')'){
                res+=1;
                i++; // i.e '(' w already considered that 
            }
            else if(ch=='(') {
                i++;
                res+=2*scoreOfParentheses(S);
            }
            else {  // it is ')' 
                i++;
                return res;
            }
        }
        
        return res;
    }
    
    // another way to re-arrange if else 
    public int scoreOfParentheses2(String S) {
        int res=0;
        while(i<S.length()){
            char c=S.charAt(i);
            i++
            if (c=='('){ // if current is (
                
                if (S.charAt(i)==')'){  // if next is )
                    res += 1;
                    i++;
                } else {
                    res += 2*scoreOfParentheses(S);
                }
            }else{ 
                return res;
            }
        }
        return res;
    }
    
}
