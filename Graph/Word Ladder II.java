/*
https://leetcode.com/problems/word-ladder-ii/
Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:

Only one letter can be changed at a time
Each transformed word must exist in the word list. 
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output:
[
  ["hit","hot","dot","dog","cog"],
  ["hit","hot","lot","log","cog"]
]
*/
class Solution {
     
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ans = new ArrayList<List<String>>();
        Set<String> visited = new HashSet();
        List<String> subAns = new ArrayList();
        Map<String,Set<String>> map = new HashMap<>();
        
        if(beginWord==null  || endWord==null )
             return ans;

        if(beginWord.equals(endWord)){
            subAns.add(beginWord);
            ans.add(subAns);
            return ans;
        }

        int minDist = buildTreeBFS(beginWord,endWord,wordList,map);
        subAns.add(beginWord);
        computeAnsDFS(beginWord,endWord,minDist,map,ans,subAns);
        
        return ans;
    }
    
    void computeAnsDFS(String beginWord, String endWord, int minDist, 
                       Map<String,Set<String>> map, List<List<String>> ans, List<String> subAns){
        
        if(beginWord.equals(endWord) && minDist==0){
            ans.add(new ArrayList(subAns));
            return;
        }
        if(minDist<=0)
            return;
        
        for(String word : map.get(beginWord)){
            subAns.add(word);
            computeAnsDFS(word,endWord,minDist-1,map,ans,subAns);
            subAns.remove(subAns.size()-1);
        }
        
    }
    
    int buildTreeBFS(String beginWord, String endWord, List<String> wordList, Map<String,Set<String>> map){
        Queue<String> queue = new LinkedList<>();
        Map<String,Integer> distance = new HashMap<>();
        queue.add(beginWord);
        int dist = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            dist++;
            for(int i=0;i<size;i++){
                String qWord = queue.poll();
                Set<String> children = map.getOrDefault(qWord,new HashSet<String>());
                map.put(qWord,children);
                for(String word : wordList){
                    if(isOneLetterApart(qWord,word)){
                        if(!distance.containsKey(word) || distance.get(word) >= dist){
                            distance.put(word,dist);
                            queue.add(word);  
                            children.add(word);
                        }
                    }
                }
            }
        }
        
        return distance.getOrDefault(endWord,-1);
    }
    
    boolean isOneLetterApart(String s, String t){
        int count=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i) != t.charAt(i))
                count++;
        }
        return count==1;
    }
}
