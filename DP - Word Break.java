/*
https://leetcode.com/problems/word-break/
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, 
determine if s can be segmented into a space-separated sequence of one or more dictionary words.
Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
*/
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        if(wordDict==null || wordDict.size() == 0) return false;
        if(s==null || s.isEmpty()) return true;
       
        Map<String,Boolean> map = new HashMap();
        for(String str : wordDict){
            map.put(str,true);
        }
        return wordBreakHelper(s,map);
    }
    
    boolean wordBreakHelper(String str, Map<String,Boolean> map){
        if(map.containsKey(str))
            return map.get(str);
                
        for(int i=1;i<str.length();i++){
            String sbStr = str.substring(0,i);
            if(map.getOrDefault(sbStr,false)){
                boolean endStr = wordBreakHelper(str.substring(i),map);
                if(endStr){
                    map.put(str.substring(i),endStr);
                    map.put(str,true);
                    return true;
                }
            }
        }
        map.put(str,false);
        return false;
    }
}
