https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/
Given a string, find the longest substring that contains only k unique characters.
Given "abcadcacacaca" and 3, it returns "cadcacacaca".

class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if(k<=0) return 0; 
        
        Map<Character,Integer> map = new HashMap<>();
        int left = 0;
        int maxLength =0;
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            
            if(map.containsKey(ch) || map.size()<k){ 
                map.put(ch, map.getOrDefault(ch,0)+1);
                continue;
            }  
            
            // at this point you have to take chars out from left
            // note the length of this substring
            maxLength = Math.max(maxLength,i-left);
            
            while(map.size()==k && left<i){
                char leftCh = s.charAt(left);
                
                map.put(leftCh, map.get(leftCh) - 1 );
                if( map.get(leftCh) == 0){
                    map.remove(leftCh);
                    left++;
                    break;
                }
                left++;
            }
            
            map.put(ch, 1);
        }
        // compare last remaining substring length 
        maxLength = Math.max(maxLength, s.length()-left);
        return maxLength;
    }
}
