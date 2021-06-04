/*
https://leetcode.com/problems/implement-trie-prefix-tree/

Implement a trie with insert, search, and startsWith methods.
Example:
Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");   // returns true
trie.search("app");     // returns false
trie.startsWith("app"); // returns true
trie.insert("app");   
trie.search("app");     // returns true
*/
class Trie {
    
    class TrieNode {
        char val;
        TrieNode [] nodes = new TrieNode[26];
        boolean isWord = false;
        
        TrieNode(char ch){
            val = ch;
        }
    }
    
    TrieNode root;
    
    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode('#');
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode current = root;
        for(int i=0;i<word.length();i++){
            char ch = word.charAt(i);
            if(current.nodes[ch-'a'] == null){
               current.nodes[ch-'a'] = new TrieNode(ch);
            }
            current = current.nodes[ch-'a'];
        }
        current.isWord = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode current = root;
        for(int i=0;i<word.length();i++){
            char ch = word.charAt(i);
            if(current.nodes[ch-'a'] == null){
                return false;
            }
            current = current.nodes[ch-'a'];
        }
        return current.isWord;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode current = root;
        for(int i=0;i<prefix.length();i++){
            char ch = prefix.charAt(i);
            if(current.nodes[ch-'a'] == null){
                return false;
            }
            current = current.nodes[ch-'a'];
        }
        return true;
    }
}
