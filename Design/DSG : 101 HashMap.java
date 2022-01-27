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
    
    
    
HashMap implementation: You need Hash Funciton that will return index for a given key.
1, You can get the same index for two different keys. 
    So you need linkedlist nodes in the array to store all the keys generating same key.
    here collision are resolved by linkedList. 
2. Once that is clear, we need a function that return the previous node in the bucket list. 
    So that we can delete the key operation. which will be pointed by 'next'
3. You should initialize your array with dummy nodes right away so that you avoid null checks later. 
     Array .. bucket should have dummy nodes. 
    [dummyNode(0,0)]-> key,value -> key,value -> null
    [dummyNode(0,0)]-> key,value -> key,value -> null 
       ...

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
    
    /** Initialize your data structure here. */
    public MyHashMap() {
        // create dummy nodes for every slot.
        // makes it easy for not checking null in future.
        // creating dummy head in linked list structure helps a lot to avoid null checks 
        for(int i=0;i<size;i++)
            map[i] = new LinkedNode(-1,-1);
    }
    
    // hash function 
    private int getIndex(int key){
          return Integer.hashCode(key) % size;      // => Important 
    }
    
    // returns previous node                     // => Important 
    // this will be good during the delete operation 
    private LinkedNode findNode(int index,int key){        
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
    
    /** value will always be non-negative. */
    public void put(int key, int value) {
        int index = getIndex(key);
        LinkedNode prev = findNode(index,key);
        // add node to the start of the bucket
        if(prev==null){
            LinkedNode newNode = new LinkedNode(key,value);
            LinkedNode bucket = map[index];
            newNode.next = bucket.next;
            bucket.next = newNode;
        }else { // update the value for the same key 
            prev.next.value = value;
        }
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
