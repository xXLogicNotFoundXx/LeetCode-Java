/*
https://leetcode.com/problems/insert-delete-getrandom-o1/
Design a data structure that supports all following operations in average O(1) time.

insert(val): Inserts an item val to the set if not already present.
remove(val): Removes an item val from the set if present.
getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.

Map<val,index> and ArrayList during remove we swap last element in the list and remove last element.  
*/
class RandomizedSet {
    Map<Integer,Integer> map = new HashMap();
    ArrayList<Integer> list = new ArrayList();
    Random rand = new Random();
    /** Initialize your data structure here. */
    public RandomizedSet() {
        map.clear();
        list.clear();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(map.containsKey(val))
            return false;
        
        list.add(val);
        map.put(val,list.size()-1);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(map.containsKey(val)){
            int index = map.get(val);
            
            list.set(index, list.get(list.size()-1));
            map.put(list.get(index),index);
            
            map.remove(val);
            list.remove(list.size()-1);
            return true;
        }
        return false;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        if(list.size()==0) 
            return -1;
        return list.get(rand.nextInt(list.size()));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
