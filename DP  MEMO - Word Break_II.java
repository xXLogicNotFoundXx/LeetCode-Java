/*
https://leetcode.com/problems/word-break/
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, 
add spaces in s to construct a sentence where each word is a valid dictionary word.
Return all such possible sentences.
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
Output:
  ["cats and dog","cat sand dog"]
  
Time complexity  : O(n^3). Size of recursion tree can go up to n^2. The creation of list from subans takes n time.
Space complexity : O(n^3)The depth of the recursion tree can go up to n and 
                    each activation record can contains a string list of size nn. 
*/
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        if(wordDict==null || wordDict.size() == 0) 
            return wordDict;
        if(s==null || s.isEmpty()) 
            return wordDict;
        
        HashMap<Integer,List<String>> map = new HashMap<>();
        
        return wordBreakHelper(s, new HashSet(wordDict), 0, map);
    }
    
    public List<String> wordBreakHelper(String s, Set<String> wordDict, int start, HashMap<Integer,List<String>> map) {
        
        List<String> res = new ArrayList<>();
        
        if (start == s.length()) {
            res.add("");
            return res;
        }
            
        if (map.containsKey(start)) 
            return map.get(start);
        
        for (int j = start + 1; j <= s.length(); j++) { // Important  <= s.length()
            
            String subStr = s.substring(start, j); // substring second index is upperbound (non-including j's value)
            
            if(wordDict.contains(subStr)){
                
                List<String> subResults = wordBreakHelper(s, wordDict, j, map);
                
                for(String sr : subResults){
                    if(sr.isEmpty())   // if it is coming from end we dont need space at the end
                        res.add(subStr);
                    else
                        res.add(subStr + " " + sr);
                }
            }
        }
        
        map.put(start,res);
        return map.get(start);
    }
}
