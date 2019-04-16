/*
 https://leetcode.com/problems/group-anagrams/
 Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
    Output:
    [
      ["ate","eat","tea"],
      ["nat","tan"],
      ["bat"]
    ]
    
 use Map<Sorted_String,Set<String>> to avoid duplicates we can use set in the map.  
 we are storing the strings in this .. but some people have created prime number array 
 int[] prime = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103}; 
 so a=2,c=5 ans so on  you multiply these numbers and you get unique number for each anagram and then you save it in map. 
*/

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        
        Map<String,List<String>> map = new HashMap();
        for(String str : strs){
            char []sortedChar = str.toCharArray();
            Arrays.sort(sortedChar);
            // Important if you put char[] as key in the map it wont work 
            // A HashMap compares keys using equals() and two arrays in Java are equal only if they are the same object/ref. 
            // If you want value equality, then write your own container class that wraps an array[] and provides the appropriate semantics for equals() and hashCode()
            List<String> list = map.getOrDefault(String.valueOf(sortedChar),new ArrayList());
            list.add(str);
            map.put(String.valueOf(sortedChar),list);
        }
        
        List<List<String>> ans = new ArrayList();
        for(List<String> list : map.values()){
           ans.add(list);
        }
        
        return ans;
    }
}
