/* 
https://leetcode.com/problems/substring-with-concatenation-of-all-words/
Sliding window 
hashmap.equals method compares two hashmaps by key-value pairs. same key-value pairs and both must be of same size. 
Time : O(n*m) n(size of s) m(number of words)
Space : O(m)
*/
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        if(s==null || s.length()==0 || words==null || words.length==0)
            return ans;
        
        int wordSize = words[0].length();
        int wordNum = words.length;
        int subStrLength = wordNum*wordSize;
        Map<String,Integer> wordCount = new HashMap<>();
        
        for(String w : words)
            wordCount.put(w, wordCount.getOrDefault(w,0)+1);
        
        for(int i=0;i<=s.length()-subStrLength;i++){
            String subStr = s.substring(i,i+subStrLength);
            if(isConcat(subStr, wordSize, wordCount)){
                ans.add(i);
            }
        }
        
        return ans;
    }
    
    boolean isConcat(String str, int wordSize, Map<String,Integer> wordCount){
        Map<String,Integer> wordCount2 = new HashMap<>();
        for(int i=0; i<=str.length()-wordSize; i=i+wordSize){
            String word = str.substring(i,i+wordSize);
            if(!wordCount.containsKey(word))
                return false;
            wordCount2.put(word, wordCount2.getOrDefault(word,0)+1);
        }
        return  wordCount.equals(wordCount2);
    }
}
