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
class Solution {
    public Node flatten(Node head) {
        Stack<Node> stack = new Stack();
        Node start = head;
        Node temp = null;
        while(head!=null){
            temp = head;
            if(head.child!=null){
                if(head.next!=null)
                    stack.push(head.next);
                head.next = head.child;
                head.next.prev = head;
                head.child = null;
            }
            head = head.next;
            if(head==null){
                if(!stack.isEmpty()){
                    temp.next = stack.pop();
                    temp.next.prev = temp;
                    head = temp.next;
                }
            }
        }
        return start;
    }
}
