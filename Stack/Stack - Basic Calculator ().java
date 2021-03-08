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

Think of this Input: "(1-(4+5+2)-3)+(6+8)"
output=1

From these inputs we cant really ignore the ( & ). 
There is a sign before'(' & sign after ') 
The idea is
  * when you get '(' do the recursive call 
  * on ')' return the Caculated ans . 
  * Also, we are gonna put -ve number on the stack when we see the operation '-' .
  * we can pass the index of the string but returning to the caller we can keep the track of index.
     So index we can keep it as global variable or we can just pass the string in the queue form.

*/
// Check the Basic Calculator II first it will be easy to understand why are we putting -ve number on stack. 
// Check the second program that doesnt work in Basic Calculator II
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
            
            // If you dont add '+' in the queue .. then you can have index as a global variable and move that 
            // and this condition would be 
            // if(index+1 == s.length() || ch==')' || ch=='+'|| ch=='-') ...
            if(ch==')' || ch=='+'|| ch=='-'){ // very IMP that we have ) in this 
                switch(prevSign){
                    case '+' :
                        st.push(num);
                        break;
                    case '-' :
                        st.push(-num);
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
