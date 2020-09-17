/*
https://leetcode.com/problems/decode-string/

Input: s = "3[a]2[bc]"
Output: "aaabcbc"

Input: s = "3[a2[c]]"
Output: "accaccacc"

Input: s = "2[abc]3[cd]ef"
Output: "abcabccdcdcdef"

Input: s = "abc3[cd]xyz"
Output: "abccdcdcdxyz"

*/
class Solution {
    public String decodeString(String s) {
        String res = "";
        
        Deque<Integer> countStack = new ArrayDeque<Integer>();
        Deque<String>  stringStack = new ArrayDeque<String>();
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            
            if(Character.isDigit(ch)){
                
                int count=0;
                while(i<s.length() && Character.isDigit(ch)){
                    count*=10;
                    count+= s.charAt(i) - '0';
                    i++;
                    ch = s.charAt(i);
                }
                countStack.push(count);
                i--;
            } 
            else if(ch=='['){
                stringStack.push(res);
                res=""; 
            } 
            else if(ch==']'){
                int count = countStack.pop();
                
                StringBuilder sb = new StringBuilder();
                sb.append(stringStack.pop());
                
                while(count>0){
                    sb.append(res);
                    count--;
                }
                res = sb.toString();
            } 
            else {
                res = res + ch;
            }
        }
        return res;
    }
}
