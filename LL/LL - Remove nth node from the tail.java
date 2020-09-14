/* 
https://leetcode.com/problems/remove-nth-node-from-end-of-list/
Given a linked list, remove the n-th node from the end of list and return its head.

Note:
Given n will always be valid.

  inp : 1->2 n=2     |    1    n=1
  out : 2            |    null

*/

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        
        ListNode dummy = new ListNode(-1);
        dummy.next = head; 
        ListNode slow = dummy, fast = dummy; 
        
        // Move fast in to n+1 position so that the gap between slow and fast becomes n+1
        // n+1 bcz we want slow pointer to point one node before actual node being removed.
        for(int i=1;i<=n+1;i++){
            fast=fast.next; 
        }
        
        // move fast to end of the list and move slow pointer as well 
        while(fast!=null){
            slow = slow.next;
            fast = fast.next;
        }
        
        //skip the desired node
        slow.next = slow.next.next;
        return dummy.next;
    }
}
