https://leetcode.com/problems/word-pattern/
Input: pattern = "abba", str = "dog cat cat dog"
Output: true
Input:pattern = "abba", str = "dog cat cat fish"
Output: false
class Solution {
    public boolean wordPattern(String pattern, String str) {
        
        HashMap<Character,String> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        String []strs = str.split(" ");
        
        if(pattern.length()!=strs.length)
            return false;
        
        for(int i=0;i<strs.length;i++){
            char c = pattern.charAt(i);
            String s = strs[i];
            
            if(map.containsKey(c)){
                if(!s.equals(map.get(c)))
                    return false;
            } else {
                if(set.contains(s))
                    return false; 
                map.put(c,s);
                set.add(s);
            }
        }
        return true;
    }
}
