/*
1209. Remove All Adjacent Duplicates in String II
https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/

Input: s = "deeedbbcccbdaa", k = 3
Output: "aa"
Explanation: 

Input: s = "pbbcggttciiippooaais", k = 2
Output: "ps"

Constraints:
1 <= s.length <= 10^5
2 <= k <= 10^4
s only contains lower case English letters
*/
class Solution {
    
    class Pair{
        public char chr; 
        public int count; 
        Pair(char ch){
            chr=ch;
            count=1;
        }
    }
    
    public String removeDuplicates(String s, int k) {
        
        Deque<Pair> stack = new ArrayDeque<>();
        for(int i=0;i<s.length();i++){
            
            if(stack.isEmpty() || stack.peek().chr !=s.charAt(i)){
                stack.push(new Pair(s.charAt(i)));
            } else{
               stack.peek().count++;
            }
            
            if(stack.peek().count==k)
                stack.pop();
        }
        
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            Pair p = stack.pop();
            while(p.count!=0){
                sb.append(p.chr);
                p.count--;
            }
        }
        
        return sb.reverse().toString();
    }
}
