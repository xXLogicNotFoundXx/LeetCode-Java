/*
642. Design Search Autocomplete System
https://leetcode.com/problems/design-search-autocomplete-system/
return the top 3 historical hot sentences
'#' - means end of the string/sentence on this character you should update the system with new sentence.
Input : 
"i love you" : 5 times
"island" : 3 times
"ironman" : 2 times
"i love leetcode" : 2 times
Now, the user begins another search:

input('i')
Output: ["i love you", "island","i love leetcode"]
input(' ')
Output: ["i love you","i love leetcode"]
input('a')
Output: []
Operation: input('#')
Output: []
The user finished the input, the sentence "i a" should be saved as a historical sentence in system. And the following input will be counted as a new search.
*/
class AutocompleteSystem {
    
    class TrieNode {
        Map<String,Integer> sentenceCount = new HashMap<>();
        Map<Character,TrieNode> tries = new HashMap<>();
    }
    TrieNode root;
    String prefix;
    TrieNode searchStart;
    
    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        prefix = "";
        
        for(int i=0;i<sentences.length;i++)
            add(sentences[i],times[i]);
        
        searchStart = root;
    }
    
    private void add(String sentence, int count){
        TrieNode cur = root;
        for(char ch : sentence.toCharArray()){
            cur.tries.putIfAbsent(ch, new TrieNode());
            TrieNode next = cur.tries.get(ch);
            next.sentenceCount.put(sentence, next.sentenceCount.getOrDefault(sentence,0) + count);
            cur = next;
        }
    }
    
    public List<String> input(char c) {
        if(c=='#'){
            add(prefix,1);
            prefix="";
            searchStart = root;
            return new ArrayList<>();
        }
        
        prefix = prefix + c;
        if(searchStart.tries.get(c)==null){
            searchStart.tries.put(c, new TrieNode()); // Very Important otherwise next char can give you wrong output
            searchStart = searchStart.tries.get(c);
            return  new ArrayList<>();
        } 
        
        searchStart = searchStart.tries.get(c);
        
        PriorityQueue<Map.Entry<String,Integer>> pq = new PriorityQueue<Map.Entry<String,Integer>>(new Comparator<Map.Entry<String,Integer>>(){
            
            public int compare(Map.Entry<String,Integer> e1, Map.Entry<String,Integer> e2){
                int comp =  e2.getValue() - e1.getValue();
                if(comp == 0){
                    return e1.getKey().compareTo(e2.getKey());  // Very Important
                }
                return comp;
            }
        });
        
        pq.addAll(searchStart.sentenceCount.entrySet());
        List<String> ans = new ArrayList<>();
        while(!pq.isEmpty()){
            ans.add(pq.poll().getKey());
            if(ans.size()==3)
                break;
        }
        
        return ans;
    }
}
