https://leetcode.com/problems/first-unique-character-in-a-string/submissions/
return index of First unique char
Note: You may assume the string contain only lowercase letters.

class Solution {
// 1 PASS  This is faster by 10+ms
   public int firstUniqChar(String s) {
        Map<Character, Integer> map = new LinkedHashMap<>(); // preserves the order of insertion
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) {
                if(map.containsKey(s.charAt(i)))
                    map.remove(s.charAt(i));
            } else {
                map.put(s.charAt(i), i);
                set.add(s.charAt(i));
            }
        }
        return map.size() == 0 ? -1 : map.entrySet().iterator().next().getValue();
    }
    
// 2 pass
    public int firstUniqChar(String s) {
        Map<Character,Integer> map = new HashMap<>(); 
        
        for(int i=0;i<s.length();i++)
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i),0));
        
        for(int i=0;i<s.length();i++)
            if(map.get(s.charAt(i)) == 1)
                return i;
        
        return -1;
    }
}
