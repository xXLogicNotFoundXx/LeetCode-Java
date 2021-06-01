/*
https://leetcode.com/problems/minimum-window-substring/
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
Input: S = "AADOBECODEBANAC", T = "ABAC"
Output: "BANAC"
*/
// Good question would be: Are there duplicate characters in T => the ans is yes T can have duplicate chars
// order of the characters does not matter 
// so we need the count of each char, Set is not sufficient so will use hashmap
class Solution {
    public String minWindow(String s, String t) {
        if(s ==null || s.isEmpty() || t==null || t.isEmpty())
            return "";
        
        Map<Character, Integer> tCount = getStringCharCount(t);
        
        int tLength = t.length();
        int count = 0;
        int left = 0; 
        String ans = "";
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
                            if(ans.isEmpty() ||  ans.length() > i-left+1){ 
                                ans = s.substring(left,i+1); // includes ith index 
                            }
                            left++; // IMP
                            break;
                        }
                    }
                    
                    left++;
                }
            }
        }
        
        return ans;
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


