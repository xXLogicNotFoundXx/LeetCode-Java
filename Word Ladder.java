/*
https://leetcode.com/problems/word-ladder/
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]
Output: 5 .   Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",

beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]
Output: 0
*/
class Solution {
    // The base Idea is BFS 
    // level -1 Find all 1 distance wordList
    // level -2 iterate over level-1 list and find all one distance wordList (also do not conside already visited words)
    // keep doing that until you reach the endWord 
    // As this is BFS the momemnt we hit the word thats the shortest path 
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(!wordList.contains(endWord)) return 0;
        Set<String> currentLevelWords = new HashSet<>();
        Set<String> visitedWords = new HashSet<>();
        currentLevelWords.add(beginWord);
        visitedWords.add(beginWord);
        int level = 1;
        while(!currentLevelWords.contains(endWord) && currentLevelWords.size()>0){
            Set<String> nextLevelWords = new HashSet<>();
            for(String startWord  : currentLevelWords){
                for(String word : wordList){
                    if(oneDistanceApart(startWord,word) && !visitedWords.contains(word)){
                        nextLevelWords.add(word);
                        // we could remove word from wordList but that would be 
                        // concurrent modification while traversing 
                        visitedWords.add(word); 
                    }
                }
            }
            currentLevelWords = nextLevelWords;
            level++;
        }
        if(currentLevelWords.size() == 0 ) // we went through all levels 
            return 0;
        return level;
    }
    
    public boolean oneDistanceApart(String str, String str2){
        int distance =0;
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)!=str2.charAt(i))
                distance++;
        }
        return distance==1;
    }
}
