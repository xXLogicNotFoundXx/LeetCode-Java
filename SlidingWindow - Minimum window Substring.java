/*
https://leetcode.com/problems/minimum-window-substring/
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
Input: S = "AADOBECODEBANAC", T = "ABAC"
Output: "BANAC"
*/
// Good question would be: Are there duplicate characters in T => the ans is yes T can have duplicate chars
// so we need the count of each char, Set is not sufficient so will use hashmap
class Solution {
    public String minWindow(String s, String t) {
        
        HashMap<Character,Integer> map = new HashMap();
        String ans = "";
        
        if(t==null || s==null || s.length()<t.length())
            return ans;
        
        // build a map for what chars and how many we need to satisfy the condition
        for(int i=0;i<t.length();i++){
            int count = map.getOrDefault(t.charAt(i),0);
            map.put(t.charAt(i),count+1);
        }
        
        int start = 0;
        int count = 0; // we keep the track of how many characters in T are macthed so far
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if(map.containsKey(ch)){
                map.put(ch,map.get(ch)-1); // some duplicate unnecessary chars gonna go in -ve e.g. i/p= "BBBA" "AB"
                if(map.get(ch)>=0){ // we ignore -ve values because we already have required number of chars 
                   count++;
                }
            }
            
            if(count==t.length()){ // all chars are covered 
                // now we have to move start so that we remove all unnecessary chars
                while(start<i){
                    char chStart = s.charAt(start);
                    if(map.containsKey(chStart)){
                        // remove the char  
                        map.put(chStart,map.get(chStart)+1);
                    
                        // make sure we still have reuired number of chars in the string
                        if(map.get(chStart)==1)  // this means we removed the char, where condition doesnt not satisfy anymore
                            break;   // we need this char so break
                    }
                    start++;
                }
                // note down the ans
                if(ans.isEmpty() || ans.length() > i-start+1){
                    ans = s.substring(start,i+1);
                }
                
                start++; // we already considered start for the ans and we also marked that char as removed in our map so move it 
                count--; // we removed one required char so update the count
            }
        }
        return ans;
    }
}
