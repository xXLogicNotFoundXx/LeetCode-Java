/*
https://leetcode.com/problems/insert-delete-getrandom-o1/
Design a data structure that supports all following operations in average O(1) time.

insert(val): Inserts an item val to the set if not already present.
remove(val): Removes an item val from the set if present.
getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.

1 Now Set<Integer> can easily satisfy inser and remove. 
  rand.nextInt(size) -> also will give random index with equal probability.
    Accessing that ith value in a set is not possible. You would need to iterate over it. 

2 Now we could think about putting it in the set and array but then remove in o(1) not possible 
    but we would need index attached to the value 

3 Map<val,index> and ArrayList<Integer> could work 
  checkign value is easy. and removing value from map is easy 
  for ArrayList index we can 
    SWAP the value with last value in the arrayList O(1)
    update the map index for the last value         O(1)
    resize the array                O(1)
    remove entry from the map   O(1)
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
            
            if(index<list.size()-1){
                int lastElement = list.get(list.size()-1);
                list.set(index, lastElement);
                map.put(lastElement,index);
            }
            
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
