/*
Medium - Very important there are other problems like that 1146. snapshot array
Google12 Lyft6 Microsoft4 Apple3 Amazon3 Netflix2


https://leetcode.com/problems/time-based-key-value-store/
981. Time Based Key-Value Store

Create a timebased key-value store class TimeMap, that supports two operations.

1. set(string key, string value, int timestamp)

Stores the key and value, along with the given timestamp.
2. get(string key, int timestamp)

Returns a value such that set(key, value, timestamp_prev) was called previously, with timestamp_prev <= timestamp.
If there are multiple such values, it returns the one with the largest timestamp_prev.
If there are no values, it returns the empty string ("").

// Approach 1: HashMap + TreeMap
TreeMap implements NavigableMap<K,V>,
    A Red-Black tree based NavigableMap implementation.
METHODS : get/put and all methods takes log(n) time
K	            ceilingKey(K key)
Map.Entry<K,V>	ceilingEntry(K key)
Map.Entry<K,V>	floorEntry(K key)
K	            floorKey(K key)

// Approach 2: HashMap + Binary Search - This is important look at the binary search

*/
class TimeMap {

    // Approach 1: HashMap + TreeMap
    class TimeMap1 {

        /** Initialize your data structure here. */
        // TreeMap is a -> class TreeMap<K,V> extends AbstractMap<K,V> implements NavigableMap<K,V>,
        // A Red-Black tree based NavigableMap implementation. The map is sorted according to the natural ordering of its keys,
        // or by a Comparator provided at map creation time,
        HashMap<String, TreeMap<Integer,String>> mapTree;
        public TimeMap1() {
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

    // Approach 2: HashMap + Binary Search - This is important look at the binary search
    // I strugled a lot here wrong and infinite loop
    class TimeValue{
        int time;
        String value;
        TimeValue(int time, String value){
            this.time = time;
            this.value = value;
        }
    }

    Map<String,List<TimeValue>> map;

    /** Initialize your data structure here. */
    public TimeMap() {
        map = new HashMap<String,List<TimeValue>>();
    }

    public void set(String key, String value, int timestamp) {
        map.putIfAbsent(key,new ArrayList<TimeValue>());
        map.get(key).add(new TimeValue(timestamp,value));
    }


    // Log(N)
    public String get(String key, int timestamp) {
        if(!map.containsKey(key))
            return "";

        List<TimeValue> list = map.get(key);
        TimeValue v = binarySearch(list, timestamp);

        return v.value;
    }

    /*
        we want the next lowest or equal ....
        so we should NOT move L (like +1),  L=mid & R=mid-1
        L could be our ans

        Role of thumb
        m = r - (r-l)/2                     m = r - (r-l)/2
        with                                with
        l = m                               r = m-1
        r = m - 1.                          l = m

        This can prevent m from getting stuck at r (or l) after the updating step.
    */
    TimeValue binarySearch(List<TimeValue> list, int timestamp){

        int l=0, r=list.size()-1;

        if(list.get(l).time > timestamp)
            return new TimeValue(-1,"");

        while(l<r){
            int mid = r-(r-l)/2;
            int midTime = list.get(mid).time;

            if(midTime > timestamp)
                r=mid-1;
            else
                l=mid;
        }
        // here l and r are same
        return list.get(l);
    }
}
