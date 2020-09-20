/*
https://leetcode.com/problems/remove-k-digits/

Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.
The length of num is less than 10002 and will be ≥ k.
The given num does not contain any leading zero.

Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.

Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.

Input: num = "10", k = 2
Output: "0"
*/
// This problem may seem easy now that I solved. 
// but there were so many corner cases I missed.
// this of leading zero problem , remaining k's after main loop 
class Solution {
    public String removeKdigits(String num, int k) {
        if(num==null)
            return num;

        Deque<Integer> stack = new ArrayDeque<>();
        
        for(int i=0;i<num.length();i++){
            int n = num.charAt(i)-'0';
            
            // keep popping stack untill the top element is less than currentNumber 
            while(!stack.isEmpty() && k>0 && stack.peek()>n){
                stack.pop();
                k--;
            }
            stack.push(n);
        }
        
        // remove remaining k characters input: 11111111111 k=5
        while(!stack.isEmpty() && k>0){
            stack.pop(); 
            k--;
        }
        
        StringBuilder sb = new StringBuilder();
        
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
        
        sb = sb.reverse();

        // remove leading zeros input : 10000123 k=3 
        while(sb.length()!=0 && sb.charAt(0)=='0'){
            sb.deleteCharAt(0);
        }
        
        return sb.length()==0 ? "0" :sb.toString(); 
    }
}
