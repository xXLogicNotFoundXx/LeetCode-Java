/*
https://leetcode.com/problems/isomorphic-strings/
LinkedIn7 Amazon7 Google4 Uber2

Given two strings s and t, determine if they are isomorphic.
Two strings are isomorphic if the characters in s can be replaced to get t.
Input: s = "egg", t = "add"
Output: true

String 1:              A B E A C D B
index pattern:         0 1 2 0 4 5 1
String 2:              X Y I X H K Y
index pattern:         0 1 2 0 4 5 1
Whether the two strings are isomorphic can be judged by the index patterns.
In the above example, these two strings are isomorphic with the same index patterns.
*/

class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s == null || t == null) return false;
        if (s.length() != t.length()) return false;

        Map<Character, Integer> mapS = new HashMap<Character, Integer>();
        Map<Character, Integer> mapT = new HashMap<Character, Integer>();

        for (int i = 0; i < s.length(); i++) {
            int indexS = mapS.getOrDefault(s.charAt(i), -1);
            int indexT = mapT.getOrDefault(t.charAt(i), -1);

            if (indexS != indexT)
                return false;

            mapS.put(s.charAt(i), i);
            mapT.put(t.charAt(i), i);
        }

        return true;
    }
}
