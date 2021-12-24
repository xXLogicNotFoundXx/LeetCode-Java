/*
859. Buddy Strings - Easy
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

        boolean isTwoChars=false;

        Map<Character,Integer> count = new HashMap<>();

        int a=-1,b=-1;
        for(int i=0; i<A.length(); i++){

            count.put(A.charAt(i), count.getOrDefault(A.charAt(i), 0) + 1 );

            // In the end if the strings are same
            // we still need to switch two chacters
            // we make sure we have two same chars that we can switch \
            if(count.get(A.charAt(i)) >= 2)
                isTwoChars = true;

            if(A.charAt(i)!=B.charAt(i)){
                if(a==-1)
                    a=i;
                else if(b==-1)
                    b=i;
                else
                    return false; // we found 3 unmatching chars

            }
        }

        // both are same string and we can switch same chars
        if(A.equals(B) && isTwoChars==true)
            return true;

        if(a!=-1 && b!=-1 && A.charAt(a) == B.charAt(b) && A.charAt(b) == B.charAt(a))
            return true;

        return false;
    }
}
