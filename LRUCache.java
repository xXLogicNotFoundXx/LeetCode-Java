/*
https://leetcode.com/problems/lru-cache/

Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.
get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

Follow up:
Could you do both operations in O(1) time complexity?

 * First circular array/queue sounds good but if the element is accessed then we have to move to the top and move other elemensts. so it is not O(1)
 
 * Linked list sounds good .. we can save refrences in hashtable and move that element to head of the linked list simlliarly 
    add new element to the start and remove from end if we want to kick.
    This is promising but we dont have previous pointer to move the element to the start 
    We have direct access to pointer but no prev pointer. You may think in the hashmap you can also store prev pointer but 
    that previous pointer can change as we add,remove, update the linked list.
 
 * we can do doubly linked list that way it is easy to ditach the node once you have pointer stored in hashmap 
    hash map stores key and DoublyLinkedList node. With prev and next pointer it will be easy to detach that node. 
    
 ** Moreover if you cretae pseudo head and tail in DoublyLinkedList to mark the boundary, then we don't need to check the NULL nodes during operations
 ** also there could be put(1,1) then put (1,4) in that case the value should be updated and its value and move node to the start.
 ** We also store key in the doubly linkedList node bcz when we evict the node we need to know the key to remove it from HashMap.
*/
class LRUCache {
    class Node {
        int key;
        int value; 
        Node next;
        Node prev;
        Node(int k, int v){
            key=k;
            value=v;
        }
        
        Node(){
         next=null;
         prev=null;
        }
    }
    
    Node Head = new Node();
    Node Tail = new Node();
    
    Map<Integer, Node> map; 
    int size=0;
    
    public LRUCache(int capacity) {
        if(capacity<=0){
            return; 
        }
        size=capacity;
        Head.next = Tail;
        Tail.prev = Head;
        map = new HashMap<>(capacity);
    }
    
    public int get(int key) {
        if(size==0)
            return-1; 
        if(map.containsKey(key)){
            // update doubly link list
            detachNode(map.get(key));
            putNodeAtFirst(map.get(key));
            return map.get(key).value;
        }
        return -1;
    }
    
    
    public void put(int key, int value) {
        if(size==0)
            return; 
        if(map.containsKey(key)){
            map.get(key).value = value;
            detachNode(map.get(key));
            putNodeAtFirst(map.get(key));
        } else {
            if(size==map.size()){
                map.remove(removeLastNode().key);
            }
            Node node = new Node(key,value);
            
            putNodeAtFirst(node);
            map.put(key,node);
        }
    }
    
    void detachNode(Node node){
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;   
    }
    
    void putNodeAtFirst(Node node){
        Node next = Head.next; 
        node.prev = Head;
        node.next = next;
        next.prev = node;
        Head.next = node;
    }
    
    Node removeLastNode(){
        Node lastNode = Tail.prev;
        Node pre = lastNode.prev;
        pre.next = Tail;
        Tail.prev = pre;
        
        return lastNode;
    }
}
/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
