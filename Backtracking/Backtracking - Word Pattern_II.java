/*
Medium - In last 3 years: uber2, amz4

291. Word Pattern II
https://leetcode.com/problems/word-pattern-ii/
Given a pattern and a string str, find if str follows the same pattern.
Input: pattern = "abab", str = "redblueredblue"
Output: true
Input: pattern = "aabb", str = "xyzabcxzyabc"
Output: false

*/
class Solution {
    public boolean wordPatternMatch(String pattern, String str) {
        return wordPatternMatchHelper(pattern, 0, str, 0, new HashMap<Character,String>(), new HashSet<String>());
    }

    boolean wordPatternMatchHelper(String pattern, int i, String str, int start, Map<Character,String> map, Set<String> set){

        if(i==pattern.length() && start==str.length())
            return true;

        if(i==pattern.length() || start==str.length())
            return false;

        char pat = pattern.charAt(i);

        // if pattern already exist
        if(map.containsKey(pat)){
            String existingPat = map.get(pat);

            if(str.startsWith(existingPat,start)){
               return wordPatternMatchHelper(pattern, i+1,
                                             str, start+existingPat.length(),
                                             map, set);
            }
            return false;
        }

        for(int k=start+1;k<=str.length();k++){
            String subStr = str.substring(start,k);
            
            // avoid creating same pattern string
            if(set.contains(subStr))
                continue;

            map.put(pat,subStr);
            set.add(subStr);

            if(wordPatternMatchHelper(pattern, i+1, str, k, map, set))
                return true;

            map.remove(pat);
            set.remove(subStr);
        }

        return false;
    }
}
