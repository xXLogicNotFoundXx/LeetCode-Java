/* 
https://leetcode.com/problems/time-based-key-value-store/
Create a timebased key-value store class TimeMap, that supports two operations.
1. set(string key, string value, int timestamp)
    Stores the key and value, along with the given timestamp.
2. get(string key, int timestamp)
    Returns a value such that set(key, value, timestamp_prev) was called previously, with timestamp_prev <= timestamp.
    If there are multiple such values, it returns the one with the largest timestamp_prev.

Approach 1: HashMap + Binary Search
Approach 2: HashMap + TreeMap

TreeMap implements NavigableMap<K,V>, 
    A Red-Black tree based NavigableMap implementation. 
METHODS : get/put and all methods takes log(n) time 
K	            ceilingKey(K key)
Map.Entry<K,V>	ceilingEntry(K key)
Map.Entry<K,V>	floorEntry(K key)
K	            floorKey(K key)
*/

class TimeMap {

    /** Initialize your data structure here. */
    HashMap<String, TreeMap<Integer,String>> mapTree; 
    public TimeMap() {
        mapTree = new HashMap<String,TreeMap<Integer,String>>();
    }
    
    public void set(String key, String value, int timestamp) {
        mapTree.putIfAbsent(key, new TreeMap<Integer,String>());
        mapTree.get(key).put(timestamp,value);
    }
    
    public String get(String key, int timestamp) {
        if(mapTree.containsKey(key)){
            TreeMap<Integer,String> tree = mapTree.get(key);
            Integer i = tree.floorKey(timestamp);
            return i==null ? "" : tree.get(i);
        }
        return "";
    }
}
