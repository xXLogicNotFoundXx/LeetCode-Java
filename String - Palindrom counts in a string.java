/*
Given a string, your task is to count how many palindromic substrings in this string.
Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".

Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".

without hashmap its 132 ms ( N^3) generating every substring O(N^2) and the checking if it is palindrom O(N). 
for(int i=0;i<str.length();i++)
    for(int j=i+1;j<=str.length();j++)
            String preSubStr = str.substring(i,j);
            //check if palindrom
with hashmap memory limit exceeds bcz it required N^2 space 
but still with that much space runtime wil be roughly O(N^3) bcz N^2 are the number of substring generated if all are unique then no use of hashmap
    unless input is like aaaaaaaaaaaaa 
    
Man with N^2 you can just take every index and expand towards both side (considering even and odd palindrom)
this is same algorithm we use to find Largest palindrom substring in a given string.
 4ms
*/
class Solution {
    
    public int countSubstrings(String str) {
        if(str==null || str.isEmpty()) return 0;
        
        int count =0;
        for(int i=0;i<str.length();i++){
           count +=  calculatePalindromes(str,i,i);
           count +=  calculatePalindromes(str,i,i+1);
        }
        return count;
    }
    
    int calculatePalindromes(String str, int i,int j){
        int count =0;
        while(i>=0 && j<str.length()){
            if(str.charAt(i)==str.charAt(j))
                count++;
            else
                break;
            
            i--;j++;
        }
        return count;
    }
}
