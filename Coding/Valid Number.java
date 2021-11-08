/*
https://leetcode.com/problems/valid-number/

Hard 
FB25, LinkedIn7 

A valid number can be split up into these components (in order):

A decimal number or an integer.
    (Optional) An 'e' or 'E', followed by an integer.

A decimal number can be split up into these components (in order):
    (Optional) A sign character (either '+' or '-').
    One or more digits, followed by a dot '.'.
    One or more digits, followed by a dot '.', followed by one or more digits.
    A dot '.', followed by one or more digits.

An integer can be split up into these components (in order):
    (Optional) A sign character (either '+' or '-').
    One or more digits.


Some examples:
"0" => true
"4." => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true
" -90e3   " => true
" 1e" => false
"e3" => false
" 6e-1" => true
" 99e2.5 " => false
"53.5e93" => true
" --6 " => false
"-+3" => false
"95a54e53" => false
Note: It is intended for the problem statement to be ambiguous. 
All we need is to have a couple of flags so we can process the string in linear time:
*/
class Solution {
   public boolean isNumber(String s) {
       
        boolean pointSeen = false;
        boolean eSeen = false;
        boolean numberSeen = false;
       
        s = s.trim(); // Nice! Returns a copy of the string, with leading and trailing whitespace omitted.
       
        for(int i=0; i<s.length(); i++) {
            if('0' <= s.charAt(i) && s.charAt(i) <= '9') {
                numberSeen = true;
            } else if(s.charAt(i) == '.') {
                if(eSeen || pointSeen){
                    return false;
                }
                pointSeen = true;
            } else if(s.charAt(i) == 'e' || s.charAt(i) == 'E') {
                if(eSeen || !numberSeen){
                    return false;
                }
                numberSeen = false;
                eSeen = true;
            } else if(s.charAt(i) == '-' || s.charAt(i) == '+') {
                if(i != 0 && (s.charAt(i-1) != 'e' || s.charAt(i) == 'E')){
                    return false;
                }
            } else
                return false;
        }
       
        return numberSeen;
    }
}
