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
        
        if(head==null || k<=1) 
            return head; 
      
        ListNode dummy = new ListNode(-11, head);
        ListNode START = dummy; 
        
        while(dummy!=null){
            dummy = reverseKGroupHelper(dummy,k);
        }
        
        return START.next;
    }
    
    ListNode reverseKGroupHelper(ListNode dummyHead, int k){
        ListNode runner = dummyHead;
        ListNode head = dummyHead.next; // After reverse this will be new tail
        
        while(runner.next!=null && k>0){
            runner =runner.next;
            k--;
        }
        
        if(k>0) 
            return null;
        
        ListNode tailNext = runner.next;
        reverse(dummyHead,tailNext);
        
        return head; // this is our new tail (dummyHead)
    }
    
    void reverse(ListNode dummyHead, ListNode tailNext){
        ListNode prev = dummyHead;  
        ListNode head = dummyHead.next;
    
        while(head!=tailNext){
            ListNode temp = head.next;
            head.next = prev;
            prev = head;
            head = temp;
        }
        
        ListNode orignalFirst = dummyHead.next; 
        ListNode orignalLast = prev;
        
        orignalFirst.next = tailNext;
        dummyHead.next = orignalLast;
    }
}
