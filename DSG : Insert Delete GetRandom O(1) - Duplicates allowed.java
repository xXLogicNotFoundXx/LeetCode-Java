/*
 https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed/
 Insert Delete GetRandom O(1) - Duplicates allowed
 Design a data structure that supports all following operations in average O(1) time.

    Note: Duplicate elements are allowed.
    insert(val): Inserts an item val to the collection.
    remove(val): Removes an item val from the collection if present.
    getRandom: Returns a random element from current collection of elements. 
        The probability of each element being returned is linearly related to the number of same value the collection contains.
*/
class RandomizedCollection {
    Map<Integer,Set<Integer>> map;
    ArrayList<Integer> list;
    Random rand;
    /** Initialize your data structure here. */
    public RandomizedCollection() {
        map  = new HashMap();
        list = new ArrayList();
        rand = new Random();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        Set<Integer> set = map.getOrDefault(val,new HashSet<Integer>());
        list.add(val);
        set.add(list.size()-1);
        map.put(val,set);
        return set.size()==1;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if(map.containsKey(val)){
            
            int index = map.get(val).iterator().next();  // Important set doesnt have direct access to elements 
            map.get(val).remove(index); // remove that index 
            
            if(index < list.size()-1){
                int lastElement = list.get(list.size()-1);
                list.set(index,lastElement); // overrite lastElement  to the index 
                map.get(lastElement).remove(list.size()-1); 
                map.get(lastElement).add(index);
            }
           
            if(map.get(val).size()==0) // if the set of val is empty remove it from map 
                map.remove(val);
            
            list.remove(list.size()-1); // remove last value from the list 
            return true;
        }
        return false;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        if(list.size()==0) 
            return -1;
        return list.get(rand.nextInt(list.size()));
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
