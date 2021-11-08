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

class Solution {
    
    public String reverseWords(String s) {
      
        String sArray[] = s.split(" ");     // this creates empty strings if there are consecutive spaces. 
      //String sArray[] = s.split(" ");     // this creates empty strings if there are consecutive spaces. 
      
        StringBuilder sb = new StringBuilder(); 
        
        for(int i=sArray.length-1; i>=0; i--){
            String s1= sArray[i];
            
            if(s1!=null && !s1.isEmpty()){
                if(sb.length()!=0){
                    sb.append(" ");
                }
                sb.append(s1);
            }
        }
        
        return sb.toString();
    }
  
  
   // In case if we are given the string in the char array. 
    public String reverseWords(String s) {

        s = s.trim();
        char []ch = s.toCharArray();
        
        int i=0;
        // reverse the words 
        for(int j=0;j<ch.length;j++){
            if(ch[j]==' '){
                reverse(ch,i,j-1);
                i=j+1;
            }
        }
        
        if(i<ch.length) // dont forget the last word.
            reverse(ch,i,ch.length-1);
        
        // reverse the whole string 
        reverse(ch,0,ch.length-1);
        
        // clean the spaces
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


