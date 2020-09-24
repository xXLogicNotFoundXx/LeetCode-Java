https://leetcode.com/problems/lru-cache/

Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.
get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

Follow up:
Could you do both operations in O(1) time complexity?

// First circular array/queue sounds good but if the element is accessed then we have to move to the top and move other elemensts. so it is not O(1)
// Linked list sounds good .. we can save refrences in hashtable and move that element to head of the linked list simlliarly add new element to the start and remove from end if we want to kick.
// This is promising but we dont have previous pointer to move the element to the start 4->3->2->->1 now 2 is accessed we have direct pointer to 2 but now prev pointer so 
// we can do doubly linked list that way it is easy to ditach the node once you have pointer stored in hashmap 
// hash map stores key and DoublyLinkedList node reference...
// ** Moreover if you cretae pseudo head and tail in DoublyLinkedList to mark the boundary, then we don't need to check the NULL nodes during operations
// ** also there could be put(1,1) then put (1,4) in that case the value should be updated its value and move node to the start...key is same so we keep that in cash (Hash Table)..
class LRUCache {
    int capacity = 0;
    
    class DLinkedNode {
      int key; // we need to know the key when we remove last element from the list ... key to remove it from hashmap
      int value;
      DLinkedNode prev;
      DLinkedNode next;
    }
    
    Map<Integer,DLinkedNode> map = new HashMap();  // key and node
    DLinkedNode Start = new DLinkedNode();
    DLinkedNode End = new DLinkedNode();
    
    public LRUCache(int capacity) {
        this.capacity=capacity;
        
        Start.next = End;
        Start.prev = null;
        End.prev = Start;
        End.next = null;
    }
    
    public int get(int key) {
        if(capacity<=0) return -1;
        if(map.containsKey(key)){
            update(map.get(key), map.get(key).value);
            return map.get(key).value;
        } 
        return -1;
    }
    
    void update(DLinkedNode node, int val){
        node.prev.next = node.next;
        node.next.prev = node.prev;
        insert(node);
        node.value = val;
    }
    
    void insert(DLinkedNode node){
        node.prev = Start;
        node.next = Start.next;
        node.next.prev = node;
        Start.next = node;
    }
    
    void remove(){
        End.prev.prev.next = End;
        End.prev = End.prev.prev;
    }
    
    public void put(int key, int value) {
        if(capacity<=0) return;
        
        if(map.containsKey(key)){
           update(map.get(key), value);
           return;
        }
        
        if(capacity == map.size()){
            map.remove(End.prev.key);
            remove();
        }
        
        DLinkedNode node = new DLinkedNode();
        node.key = key;
        node.value = value;
        insert(node);
        map.put(key,node);
        
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
