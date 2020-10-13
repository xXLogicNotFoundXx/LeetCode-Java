/*
859. Buddy Strings
https://leetcode.com/problems/buddy-strings/
Given two strings A and B of lowercase letters, return true if you can swap two letters in A so the result is equal to B, otherwise, return false.

Swapping letters is defined as taking two indices i and j (0-indexed) such that i != j and swapping the characters at A[i] and A[j].
For example, swapping at indices 0 and 2 in "abcd" results in "cbad".

Example 1:

Input: A = "ab", B = "ba"
Output: true
Explanation: You can swap A[0] = 'a' and A[1] = 'b' to get "ba", which is equal to B.

Input: A = "aaaaaaabc", B = "aaaaaaacb"
Output: true
*/

class Solution {
    public boolean buddyStrings(String A, String B) {
        if(A==null || B==null || A.length()!=B.length())
           return false; 
        
        boolean canSwitch=false;
        int[] chars = new int[26];
        
        int a=-1,b=-1;
        for(int i=0;i<A.length();i++){
            chars[A.charAt(i)-'a']++;
            
            if(chars[A.charAt(i)-'a'] >=2) // we can switch same char
                canSwitch = true;
            
            if(A.charAt(i)!=B.charAt(i)){
                if(a==-1)
                    a=i;
                else if(b==-1)
                    b=i;
                else
                    return false; // we found 3 unmatching chars 
                    
            }
        }
        
        // both are same so switch same characters 
        if(A.equals(B) && canSwitch==true)
            return true;
        
        if(a!=-1 && b!=-1 && A.charAt(a) == B.charAt(b) && A.charAt(b) == B.charAt(a))
            return true;
        
        return false;
    }
}
