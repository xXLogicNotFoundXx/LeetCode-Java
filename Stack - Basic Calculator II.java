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
// Trick here is put the -ve number on the stack when operation is '-'
// Also Think of the last number i.e. index on = s.length()-1
class Solution {
    public int calculate(String s) {
        if(s==null || s.isEmpty())
            return 0;
        Deque<Integer> st = new ArrayDeque<Integer>();
        int num =0;
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
        while(!st.isEmpty()){
            total += st.pop();
        }
        
        return total;
    }
    
    /*
    Doesnt work for the input
    "1-1+1"
    stdout
    1 +
    1 -
    1 +

    Output
    -1
    Expected
    1
    
    So lesson learnt here is that for '-' opration put negative number
    */
    public int calculate1(String s) { // doesnt work 
        int total=0;
        
        Deque<Integer> numbers = new ArrayDeque<>();
        Deque<Character> op = new ArrayDeque<>();
        
        numbers.push(0);
        op.push('+');
        char prevSign = '+';
        int num=0;
        
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            
            
            if(Character.isDigit(ch)){
                num = (num*10) + ch - '0';
            } 
            
            if(s.length()-1 == i || ch=='+' || ch=='-' || ch=='*' || ch=='/'){
                switch(prevSign){
                    case '+':
                        numbers.push(num);
                        op.push('+');
                        break;
                    case '-':
                        numbers.push(num);
                        op.push('-');
                        break;
                    case '*':
                        numbers.push(numbers.pop()*num);
                        break;
                    case '/':
                        numbers.push(numbers.pop()/num);
                        break;
                    default:
                        return -1;
                }
                prevSign = ch;
                num=0;
                System.out.println(numbers.peek()+" "+op.peek());
            }
            
        }
        
        while(numbers.size()>1){
            switch(op.pop()){
                case '+':
                    numbers.push(numbers.pop()+numbers.pop());
                    break;
                case '-':
                    int a = numbers.pop();
                    numbers.push(numbers.pop()-a);
                    break;
            }
        }
    
        return numbers.pop(); 
    }
}
