/* 
https://leetcode.com/problems/substring-with-concatenation-of-all-words/
You are given a string s and an array of strings words of the same length.
Return all starting indices of substring(s) in s that is a concatenation of each word in words exactly once, in any order, and without any intervening characters.

You can return the answer in any order.

Sliding window 
Input: s = "barfoothefoobarman", words = ["foo","bar"]
Output: [0,9]
Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" respectively.
The output order does not matter, returning [9,0] is fine too.

hashmap.equals method compares two hashmaps by key-value pairs. same key-value pairs and both must be of same size. 
Time : O(n*m) n(size of s) m(number of words)
Space : O(m)
*/
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        if(s==null || s.length()==0 || words==null || words.length==0)
            return ans;
        
        int wordsTotal= words.length;
        int wordLen =  words[0].length();
        int subStrLength = wordsTotal*wordLen;
        Map<String,Integer> wordCountMap = new HashMap<>();
        
        for(String w : words)
            wordCountMap.put(w, wordCountMap.getOrDefault(w,0)+1);
        
        for(int i=0;i<=s.length()-subStrLength;i++){
            String subStr = s.substring(i,i+subStrLength);
            if(isConcat(subStr, wordLen, wordCountMap)){
                
                ans.add(i);
            }
        }
        
        return ans;
    }
    
    boolean isConcat(String str, int wordSize, Map<String,Integer> wordCountMap){
        Map<String,Integer> wordCountMap2 = new HashMap<>();
        for(int i=0; i<=str.length()-wordSize; i=i+wordSize){
            String word = str.substring(i,i+wordSize);
            if(!wordCountMap.containsKey(word))
                return false;
            wordCountMap2.put(word, wordCountMap2.getOrDefault(word,0)+1);
        }
        
        return wordCountMap.equals(wordCountMap2);
    }
    
    boolean isMapEqual(Map<String,Integer> wordCountMap,  Map<String,Integer> wordCountMap2){
        System.out.println(wordCountMap.get("a") + " "+ wordCountMap2.get("a") );
        Iterator<String> it = wordCountMap.keySet().iterator();
        while(it.hasNext()){
            String key = it.next();
            if(!wordCountMap2.containsKey(key) || wordCountMap2.get(key) != wordCountMap.get(key))
                return false;    
        }
        return true; 
    }
}
