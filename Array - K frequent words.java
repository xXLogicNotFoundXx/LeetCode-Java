/*
https://leetcode.com/problems/top-k-frequent-words/
Given a non-empty list of words, return the k most frequent elements.
Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
Output: ["i", "love"]
Explanation: "i" and "love" are the two most frequent words.
    Note that "i" comes before "love" due to a lower alphabetical order.
*/
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String,Integer> map = new HashMap<>();
        for(String str : words)
            map.put(str,map.getOrDefault(str,0)+1);
        
        PriorityQueue<Map.Entry<String,Integer>> pq = new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>(){
            @Override
            public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                if(e1.getValue().equals(e2.getValue())){ // as it is an Integer object 
                    return e2.getKey().compareTo(e1.getKey());
                }
                return e1.getValue().compareTo(e2.getValue());
            }
        });
        
        for(Map.Entry<String,Integer> e : map.entrySet()){
            pq.add(e);
            if(pq.size()>k)
                pq.poll();
        }
        
        List<String> ans = new ArrayList<>();
        while(!pq.isEmpty()){
            ans.add(0,pq.poll().getKey());
        }
        return ans;
    }
}
