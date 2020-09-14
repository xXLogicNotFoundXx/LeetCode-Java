/*
https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/
Input:
 1---2---3---4---5---6--NULL
         |
         7---8---9---10--NULL
             |
             11--12--NULL

Output:
1-2-3-7-8-11-12-9-10-4-5-6-NULL
*/
// Corner case : Original LL, last node has a child.
class Solution {
    public Node flatten(Node head) {
        Stack<Node> stack = new Stack<>();
        Node HEAD = head; 
        
        while(head!=null){
            
            if(head.child != null){
                stack.push(head.next); // could be pushing null 
                head.next = head.child; 
                head.next.prev = head; 
                head.child = null; 
            }
            
            Node dummy = head;
            head = head.next;
            
            if(head==null && !stack.isEmpty()){
                dummy.next = stack.pop(); // could be popping null 
                if(dummy.next!=null){
                    dummy.next.prev = dummy;
                    head = dummy.next;
                }
            }
        }
        
        return HEAD;
    }
}
