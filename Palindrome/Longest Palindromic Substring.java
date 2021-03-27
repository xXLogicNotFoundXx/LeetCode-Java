/*https://leetcode.com/problems/longest-palindromic-substring/
Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
*/

// Divide the problem - how can we find a palindrome from each i? 
// well from each i we can expand to the left and right until palindromic condition satisfy.
// gonna be  N^2 which is fine
public class Solution {

    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2)
            return s;
        
        Pair ans = new Pair(0,0);
        for (int i = 0; i < len-1; i++) {
            
            //assume odd length, try to extend Palindrome both sides
            Pair subAns1 = extendPalindrome(s, i, i);
            ans = ans.size() < subAns1.size() ? subAns1 : ans;
            
            //assume even length, try to extend Palindrome both sides
            Pair subAns2 = extendPalindrome(s, i, i+1); 
            ans = ans.size() < subAns2.size() ? subAns2 : ans;
        }
        
        return s.substring(ans.i, ans.j);
    }

    Pair extendPalindrome(String s, int start, int end) {
        while (start >= 0 && end < s.length() && 
               s.charAt(start) == s.charAt(end)) {
            start--;
            end++;
        }
        
        return new Pair(start+1, end);
    }
    
    
    class Pair{
        int i, j;
        
        Pair(int a, int b){
            i=a;
            j=b;
        }
        
        int size(){
            return j-i;
        }
    }
}
