/*
https://leetcode.com/problems/design-add-and-search-words-data-structure/
Design a data structure that supports adding new words and finding if a string matches any previously added string.
Input
["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
[[],["bad"],["dad"],["mad"], ["pad"],["ba"],["bad"],[".ad"],["b.."]]
Output
[null,null,null,null,        false,  false,   true,   true,   true]

*/
class WordDictionary {
    
    class Trie {
        char ch;
        boolean end=false; 
        Map<Character, Trie> map = new HashMap<>();
        
        Trie(char ch){
            this.ch = ch; 
        }
        
        void addWord(String word, int i){

            if(i == word.length()){
                this.end = true;
                return;
            }

            char curCh = word.charAt(i);
            map.putIfAbsent(curCh, new Trie(curCh));
            map.get(curCh).addWord(word, i+1);
        }
        
        boolean search(String word, int i){
            if(word.length() == i){
                return end == true;
            }
            
            char curCh = word.charAt(i);
            
            if(curCh == '.')
                return SearchWholeMap(word, i);

            if(!map.containsKey(curCh))
                return false;
            
            return map.get(curCh).search(word, i+1);
        }
        
        private boolean SearchWholeMap(String word, int i){
            
            for(Map.Entry<Character,Trie> e : map.entrySet()) {
                boolean ret = e.getValue().search(word, i+1);  
                if(ret)
                    return true; 
            }
            return false;
        }
        
    }
    
    
    Trie trie;
    public WordDictionary() {
        trie = new Trie('#');
    }
    
    public void addWord(String word) {
        
        if(word==null || word.isEmpty())
            return;
        
        trie.addWord(word, 0);
    }
    
    public boolean search(String word) {
        if(word==null || word.isEmpty())
            return true;
        
        return trie.search(word, 0);
    }
}
