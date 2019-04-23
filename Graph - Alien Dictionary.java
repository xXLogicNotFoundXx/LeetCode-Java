
/*
https://leetcode.com/problems/alien-dictionary/
There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. 
You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this 
new language. Derive the order of letters in this language.
*/

class Solution {
    public String alienOrder(String[] words) {
        /* A map which will tells how many direct connections coming to a char e.g. a->c b->c d->b : a =0, b =1 ,c=2, d=0
        // That means we have we have those many parents for a given character. 
        // Another map says what it connect to map<char,set>
        // once we estabaslish the links and connection count, we start with chars that has no parent for processing in a queue
        // then we traverse there links and add char with one parent for further processing .. 
        // also this is topological sort too 
        */
        if(words==null || words.length==0) return "";
        
        Map<Character,Integer> connectionCountMap = new HashMap<Character,Integer>();
        for(String s: words){
            for(char c: s.toCharArray()){
                connectionCountMap.put(c,0);
            }
        }
        //This the map gonna record direct links we can establish from given character
        Map<Character,Set<Character>> linksMap = new HashMap<Character,Set<Character>>();
        for(int i=0;i< words.length-1;i++){
            char[] word1 = words[i].toCharArray();
            char[] word2 = words[i+1].toCharArray();
            int minLen = Math.min(word1.length,word2.length);
            
            for(int j=0;j<minLen;j++){
                if(word1[j]!=word2[j]){
                    // found the link
                    Set<Character> set = linksMap.getOrDefault(word1[j], new HashSet<Character>());
                    // so if we blindly put char in set and directly increase the connectionCount number of 2nd char it fails ...
                    // for ["za","zb","ca","cb"] we find same rule a->b and a->b twice and then b is gonna be 2 and we end up never visiting b
                    if(!set.contains(word2[j])){
                        set.add(word2[j]);
                        linksMap.put(word1[j],set);
                        // increase the degree of 2nd char 
                        connectionCountMap.put(word2[j],connectionCountMap.getOrDefault(word2[j],0)+1);
                    }
                    // for rest of the chars we cant really establish any link so take next two words 
                    break; // break is not in the if condition.
                }
            }
        }
        
        // start with no parent
        Queue<Character> queue = new LinkedList<Character>();
        for(Map.Entry<Character,Integer> e : connectionCountMap.entrySet()){
            if(e.getValue() == 0){
                queue.add(e.getKey());
            }
        }
        
        String str = "";
        while(!queue.isEmpty()){
            char ch = queue.poll();
            str = str + ch;
            if(linksMap.containsKey(ch)){
                for(char chLink : linksMap.get(ch)){
                    connectionCountMap.put(chLink,connectionCountMap.get(chLink)-1);
                    if(connectionCountMap.get(chLink) == 0){
                        queue.add(chLink);
                    }
                }   
            }
        }
        System.out.println("str = "+ str);
        return str.length() == connectionCountMap.size() ? str : "";
    }
}
