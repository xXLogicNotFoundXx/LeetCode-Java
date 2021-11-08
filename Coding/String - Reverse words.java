/* 
https://leetcode.com/problems/reverse-words-in-a-string/
Given an input string s, reverse the order of the words.

A word is defined as a sequence of non-space characters. 
The words in s will be separated by at least one space.
Return a string of the words in reverse order concatenated by a single space.
  Note that s may contain leading or trailing spaces or multiple spaces between two words. 
  The returned string should only have a single space separating the words. Do not include any extra spaces.

"the sky is blue"
"blue is sky the"

"  hello world  "
"world hello"

"  Bob    Loves  Alice   "
"Alice Loves Bob"
*/

public class Solution {
    public String reverseWords(String s) {
        if(s == null)
            return s;
        s = s.trim();
        char []ch = s.toCharArray();
        int i=0;
        for(int j=0;j<ch.length;j++){
            if(ch[j]==' '){
                reverse(ch,i,j-1); // dont reverse the space
                i=j+1;
            }
        }
        if(i<ch.length)
            reverse(ch,i,ch.length-1);
        
        reverse(ch,0,ch.length-1);
        return cleanSpacesBetweenWords(ch);
    }
    
    void reverse(char []ch,int i, int j){
        for(;i<j;i++,j--){
            char c = ch[i];
            ch[i] = ch[j];
            ch[j] = c;
        }        
    }
    
    String cleanSpacesBetweenWords(char []ch){
        int i=0;    
        for(int j=0;j<ch.length;j++){
            ch[i++]=ch[j];
            if(ch[j]==' '){
                while(j<ch.length && ch[j] == ' ')
                    j++;
                j--;
            }
        }
        return new String(ch,0,i);
    }
}
