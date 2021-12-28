/*
Hard - Lots of companies
FB 20+ AMZ 10+ LinkedIn 10+

https://leetcode.com/problems/minimum-window-substring/
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
Input: S = "AADOBECODEBANAC", T = "ABAC"
Output: "BANAC"
*/
// Good question would be: Are there duplicate characters in T => the ans is yes T can have duplicate chars
// order of the characters does not matter
// so we need the count of each char, Set is not sufficient so will use hashmap

// Time complexity = S + T
// Space - T
class Solution {
    public String minWindow(String s, String t) {
        if(s ==null || s.isEmpty() || t==null || t.isEmpty())
            return "";

        Map<Character, Integer> tCount = getStringCharCount(t);

        int tLength = t.length();
        int count = 0;
        int left = 0;
        int diff= -1 , ansLeft = -1, andRight = -1;

        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);

            if(tCount.containsKey(ch)){
                // some duplicate unnecessary chars gonna go be in -ve e.g. i/p= "BBBA" "AB"
                tCount.put(ch, tCount.get(ch)-1);
                if(tCount.get(ch)>=0)
                    count++;
            }

            if(count==tLength){
                while(left<=i){
                    char lch = s.charAt(left);

                    if(tCount.containsKey(lch)){
                        tCount.put(lch, tCount.get(lch)+1);
                        if(tCount.get(lch)>0){
                            count--;

                            // we removed unwanted char from the left
                            // make a note of the ans
                            if(diff == -1 || diff > i-left){
                                diff = i-left;
                                ansLeft = left;
                                andRight = i;
                            }

                            left++; // IMP
                            break;
                        }
                    }

                    left++;
                }
            }
        }

        return diff == -1 ? "" : s.substring(ansLeft, andRight+1);
    }


    Map<Character, Integer> getStringCharCount(String t){
        Map<Character, Integer> tCount = new HashMap<>();
        for(char ch : t.toCharArray()){
            tCount.putIfAbsent(ch, 0);
            tCount.put(ch, tCount.get(ch)+1);
        }
        return tCount;
    }
}
