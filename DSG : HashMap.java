/*
https://leetcode.com/problems/design-hashmap/
put(key, value) : Insert a (key, value) pair into the HashMap. If the value already exists in the HashMap, update the value.
get(key): Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
remove(key) : Remove the mapping for the value key if this map contains the mapping for the key.

Note:
All keys and values will be in the range of [0, 1000000].
The number of operations will be in the range of [1, 10000].

As there gonna be max 10000  operations:
    lets say they all are insertion so we make the storage for 10000 at least 
    we chose good hash function that will most likely map to every slot of the storage.
    but, collision is inevitable.We handle collition by linked list.
    we can put linked list at every slot that would contain key and value.
    We create dummy linked node just make it easy to handle. 
*/
class MyHashMap {
    
    class LinkedNode {
        int key;
        int value;
        LinkedNode next = null;
        LinkedNode(int key,int value){
            this.key = key;
            this.value = value;
        }
    }    
    
    int size = 10000;
    LinkedNode [] map = new LinkedNode[size];
    int getIndex(int key){
          return Integer.hashCode(key) % size;      // => Important 
    }
    
    /** Initialize your data structure here. */
    public MyHashMap() {
        // create dummy nodes for every slot
        for(int i=0;i<size;i++)
            map[i] = new LinkedNode(-1,-1);
    }
    
    /** value will always be non-negative. */
    public void put(int key, int value) {
        int index = getIndex(key);
        LinkedNode prev = findNode(index,key);
        if(prev==null){
            LinkedNode newNode = new LinkedNode(key,value);
            LinkedNode bucket = map[index];
            newNode.next = bucket.next;
            bucket.next = newNode;
        }else {
            prev.next.value = value;
        }
    }
    
    // returns previous node                     // => Important 
    LinkedNode findNode(int index,int key){        
        LinkedNode node = map[index];
        while(node.next!=null){
            if(node.next.key==key){
                return node;
            }
            node = node.next;
        }
        return null;
    }
    
    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int index = getIndex(key);
        LinkedNode prev = findNode(index,key);
        if(prev==null)
            return -1;
        return prev.next.value;
    }
    
    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int index = getIndex(key);
        LinkedNode prev = findNode(index,key);
        if(prev!=null)
            prev.next = prev.next.next;
        return;
    }
}
