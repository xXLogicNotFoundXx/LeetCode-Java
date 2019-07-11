/*
https://leetcode.com/problems/lfu-cache/
*/
public class LFUCache {
    HashMap<Integer, Integer> keyToVal;
    HashMap<Integer, Integer> keyToCount;
    HashMap<Integer, LinkedHashSet<Integer>> countToKeys;
    int cap;
    int min = -1;
    public LFUCache(int capacity) {
        cap = capacity;
        keyToVal = new HashMap<>();
        keyToCount = new HashMap<>();
        countToKeys = new HashMap<>();
        countToKeys.put(1, new LinkedHashSet<>());
    }
    
    public int get(int key) {
        if(!keyToVal.containsKey(key))
            return -1;
        int count = keyToCount.get(key);
        keyToCount.put(key, count+1);
        countToKeys.get(count).remove(key);
        if(count==min && countToKeys.get(count).size()==0)
            min++;
        if(!countToKeys.containsKey(count+1))
            countToKeys.put(count+1, new LinkedHashSet<>());
        countToKeys.get(count+1).add(key);
        return keyToVal.get(key);
    }
    
    public void put(int key, int value) {
        if(cap<=0)
            return;
        if(keyToVal.containsKey(key)) {
            keyToVal.put(key, value);
            get(key);
            return;
        } 
        if(keyToVal.size() >= cap) {
            int evit = countToKeys.get(min).iterator().next();
            countToKeys.get(min).remove(evit);
            keyToVal.remove(evit);
        }
        keyToVal.put(key, value);
        keyToCount.put(key, 1);
        min = 1;
        countToKeys.get(1).add(key);
    }
}
