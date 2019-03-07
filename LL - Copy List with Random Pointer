https://leetcode.com/problems/copy-list-with-random-pointer/
A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
Return a deep copy of the list.
/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;
    public Node random;
};
*/
// just put map of original node and new node so that you know which one is random node 
class Solution {
    public Node copyRandomList(Node head) {
        Map<Node,Node> map = new HashMap();
        Node dummy = head;
        // copy all nodes
        while(dummy!=null){
            Node n = new Node(dummy.val,null,null);
            map.put(dummy,n);
            dummy = dummy.next;
        }
        // assign next and random 
        Node Start = new Node(-1,null,null);
        dummy = Start;
        while(head!=null){
            dummy.next = map.get(head);
            dummy.next.random = map.getOrDefault(head.random,null);
            dummy = dummy.next;
            head = head.next;
        }
        dummy.next= null;
        return Start.next;
    }
}
