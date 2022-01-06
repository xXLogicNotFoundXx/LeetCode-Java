/*
Medium - VIMP
So many companies.

https://leetcode.com/problems/top-k-frequent-words/
Given a non-empty list of words, return the k most frequent elements.
Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
Output: ["i", "love"]
Explanation: "i" and "love" are the two most frequent words.
    Note that "i" comes before "love" due to a lower alphabetical order.
*/
class Solution {
    public List<String> topKFrequent(String[] words, int k) {

        if(words==null || words.length==0 || k<=0)
            return new ArrayList<String>();

        Map<String, Integer> countMap = new HashMap<>();

        for(String str : words){
            if(str==null)
                continue; 

            countMap.put(str, countMap.getOrDefault(str,0)+1);
        }

        PriorityQueue<Map.Entry<String,Integer>> pq = new PriorityQueue<>(new Comparator<Map.Entry<String,Integer>>(){
                public int compare(Map.Entry<String,Integer> one, Map.Entry<String,Integer> two ){
                    if(one.getValue()==two.getValue()){
                        return two.getKey().compareTo(one.getKey()); // we did the reverse bcz later on we reverse the list
                    }
                    // MIN_HEAP - we want to keep large count strings
                    return one.getValue() - two.getValue();
                }
            });
        /* Another way to do it
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
                 (a,b) -> a.getValue()==b.getValue() ? b.getKey().compareTo(a.getKey()) : a.getValue()-b.getValue()
        );*/
        for(Map.Entry<String,Integer> e : countMap.entrySet()){
            pq.add(e);
            if(pq.size()>k)
                pq.poll();
        }

        List<String> ans = new ArrayList<String>();
        while(!pq.isEmpty()){
            ans.add(pq.poll().getKey());
        }

        Collections.reverse(ans);
        return ans;
    }
}
