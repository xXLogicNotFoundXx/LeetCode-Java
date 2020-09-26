/*
https://leetcode.com/problems/basic-calculator-iii/

It is similar like basic-calculator-ii where we calculate all operands but because of "(" and ")" we have to add more code 
what we can do is when we see ( we call the same function recursively and on ) we just calculate whole stack value and return.
The problem is how do you keep track of indexes while traversing the String? 
We just put all chars in the string into the queue and that will process char one by one untill it ends.
https://leetcode.com/problems/basic-calculator-ii/

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
            
            if(ch==')' || ch=='+'||ch=='-'||ch=='*'||ch=='/'){ // very IMP that we have ) in this 
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
            
            if(ch=='('){
                // remember there is a sighn before this 
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
