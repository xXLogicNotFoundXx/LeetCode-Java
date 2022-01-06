/*
Medium - imp
Bloomberg27 Amazon3 Oracle3 Facebook2

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

        Deque<Node> stack = new ArrayDeque<>();

        Node HEAD = head;
        while(head!=null){

            if(head.child!=null){
                if(head.next!=null)
                    stack.push(head.next);

                head.next = head.child;
                head.next.prev = head; 
                head.child = null;
            }

            if(head.next==null && !stack.isEmpty()){
                head.next = stack.pop();
                head.next.prev = head;
            }

            head = head.next;
        }

        return HEAD;
    }
}
