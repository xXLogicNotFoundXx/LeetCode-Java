/*
https://leetcode.com/problems/basic-calculator-ii/
Implement a basic calculator to evaluate a simple expression string. Spaces in the strings are ignored. 
The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . 
The integer division should truncate toward zero.
You may assume that the given expression is always valid.
Do not use the eval built-in library function.
Input: "3+2*2"
Output: 7
Input: " 3/2 "
Output: 1
*/
class Solution {
    public int calculate(String s) {
        if(s==null || s.isEmpty())
            return 0;
        Stack<Integer> st = new Stack<Integer>();
        int num=0;
        char prevSign = '+';
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            
            if(ch>='0' && ch <='9'){
                num = (num*10) + (ch-'0');
            } 
            
            if(i==s.length()-1 || ch=='+'||ch=='-'||ch=='*'||ch=='/'){
                switch(prevSign){
                    case '+' :
                        st.push(num);
                        break;
                    case '-' :
                        st.push(num*-1);
                        break;
                    case '/' :
                        st.push(st.pop()/num);
                        break;
                    case '*' :
                        st.push(st.pop()*num);
                        break;
                }
                num=0;
                prevSign = ch;
            }
            
        }
        
        int total = 0;
        while(!st.isEmpty())
            total += st.pop();
        
        return total;
    }
}
