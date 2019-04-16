https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/
Given a string, find the longest substring that contains only two unique characters.
Given "abcadcacacaca" and 3, it returns "cadcacacaca".

public class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int K){
        if(K<=0) 
            return 0;
        
        Map<Character,Integer> map = new HashMap<Character, Integer>();
        int maxLen = 0;
        int start =0;
        
        for(int i=0;i<s.length();i++){
            int count = map.getOrDefault(s.charAt(i),0);
            map.put(s.charAt(i),count+1);
            
            if(map.size()==K) {    // remember size may stay K for next chars so you have to calcualte this again.
                maxLen = Math.max(maxLen,i-start+1); // +1 bcz we are including the current index too
            }
            
            if(map.size()>K){
                while(map.size()>K) {
                    int existingCharCount = map.get(s.charAt(start)) -1;
                    if (existingCharCount == 0) {
                        map.remove(s.charAt(start));
                    } else {
                        map.put(s.charAt(start), existingCharCount );
                    }
                    start++;
                }
            }
        }
       return maxLen==0 ? s.length() : maxLen; // may be the whole string didnt hit the K distinct chars
    }
}
