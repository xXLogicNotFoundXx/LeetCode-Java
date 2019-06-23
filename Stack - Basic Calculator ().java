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
         if(s==null || s.isEmpty())
            return 0;
        // get rid of the spaces 
        // s = s.replaceAll("\\s+","");
        
        Queue<Character> q = new LinkedList<>();
        for (char c : s.toCharArray()) {
            q.offer(c);
        }
        q.offer('+'); // IMP very good trick 
        
        return calculate(q);
    }
    
    public int calculate(Queue<Character> q) {
        Stack<Integer> st = new Stack<Integer>();
        int num=0;
        char prevSign = '+';
        
        while(!q.isEmpty()){
            char ch = q.poll();
            
            if(ch== ' ')
                continue;
            
            if(ch>='0' && ch <='9'){
                num = (num*10) + (ch-'0');
            } 
            
            if(ch==')' || ch=='+'|| ch=='-'){ // very IMP that we have ) in this 
                switch(prevSign){
                    case '+' :
                        st.push(num);
                        break;
                    case '-' :
                        st.push(num*-1);
                        break;
                }
                num=0;
                prevSign = ch;
            }
            
            if(ch=='('){
                // remember there is a sign before this 
                // that means num is 0 here and sign is on prevSign
                num = calculate(q);
            }
            
            if(ch==')'){
                // we made sure we calculate result before 
                break;
            }
            
        }
        
        int total = 0;
        while(!st.isEmpty())
            total += st.pop();
        
        return total;
    } 
}
