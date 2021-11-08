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
}


