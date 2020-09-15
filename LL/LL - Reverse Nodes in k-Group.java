/*
https://leetcode.com/problems/reverse-nodes-in-k-group/
Reverse Nodes in k-Group
Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
Given this linked list: 1->2->3->4->5
For k = 2, you should return: 2->1->4->3->5
For k = 3, you should return: 3->2->1->4->5
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-11);
        dummy.next = head;
        ListNode start = dummy; 
        
        if(k<=1) return head; 
        if(head==null) return head;
        while(dummy!=null){
            dummy = reverseKGroupHelper(dummy,k);
        }
        return start.next;
    }
    
    ListNode reverseKGroupHelper(ListNode dummyHead, int k){
        ListNode dummy = dummyHead;
        ListNode head = dummyHead.next;
        while(dummy.next!=null && k>0){
            dummy =dummy.next;
            k--;
        }
        if(k>0) return null;
        
        ListNode tail = dummy;
        reverse(dummyHead,tail);
        return head; // this will be new dummyHead for recursive operations
    }
    
    // used to reverse mid nodes of the LL 
    void reverse(ListNode dummyHead, ListNode tail){
        // tail will never be null 
        ListNode dummy = null;  // this null will be detached in the end 
        ListNode head = dummyHead.next;
        ListNode tailNext = tail.next;
        while(dummy!=tail){
            ListNode temp = head.next;
            head.next = dummy;
            dummy = head;
            head= temp;
        }
        dummyHead.next.next = tailNext; // null is detached from prevhead and prevhead points to tailNext
        dummyHead.next = tail; // ( head becomes tail)
    }
}
