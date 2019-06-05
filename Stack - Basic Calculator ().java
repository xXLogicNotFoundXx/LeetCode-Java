/*
https://leetcode.com/problems/basic-calculator/
224. Basic Calculator
Implement a basic calculator to evaluate a simple expression string.
The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
Input: "1 + 1"
Output: 2
Example 2:
Input: " 2-1 + 2 "
Output: 3
Example 3:
Input: "(1+(4+5+2)-3)+(6+8)"
Output: 23
*/
class Solution {
    public int calculate(String s) {
        
        int number = 0; 
        Stack<Integer> st = new Stack<>();
        int result=0;
        int sign=1;
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if(Character.isDigit(ch)){
                number *= 10;
                number += ch-'0';
            }
            if(ch=='+'){
                result += sign*number;
                number=0;
                sign = 1 ;
            }else if( ch=='-'){
                result += sign*number;
                number=0;
                sign = -1 ;
            }else if( ch=='('){
                st.push(result);
                st.push(sign);
                result=0;
                sign=1;
            }else if( ch==')'){
                result += sign * number;  
                number = 0;
                result *= st.pop();    //stack.pop() is the sign before the parenthesis
                result += st.pop();   //stack.pop() now is the result calculated before the parenthesis
            }
        }
        if(number!=0)
            result += sign*number;
        return result;
    } 
}
