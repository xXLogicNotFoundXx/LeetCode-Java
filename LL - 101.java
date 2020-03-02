// Delete node, direct access to delete the node 
class Solution {
    public void deleteNode(ListNode node) {
        ListNode dummy = node;
        node=node.next;
        while(node.next!=null){
            dummy.val = node.val;
            dummy = node;
            node = node.next;
        }
        dummy.val=node.val;
        dummy.next =null;
    }
}

/**
https://leetcode.com/problems/rotate-list/submissions/

Given a linked list, rotate the list to the right by k places, where k is non-negative.

Input: 1->2->3->4->5->NULL, k = 2
Output: 4->5->1->2->3->NULL

Input: 0->1->2->NULL, k = 4
Output: 2->0->1->NULL

The basic idea is to link the tail of the list with the head, make it a cycle. Then count to the rotate point and cut it.
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || k == 0) 
            return head;
        
        ListNode p = head;
        int len = 1;
        while(p.next != null) {
            p = p.next;
            len++;
        }
        
        p.next = head;
        k %= len;
        for(int i = 0; i < len - k; i++) 
            p = p.next;
        
        head = p.next;
        p.next = null;
        return head;
    }
}

/*
Swap Nodes in Pairs
https://leetcode.com/problems/swap-nodes-in-pairs/submissions/
Given 1->2->3->4, you should return the list as 2->1->4->3.
*/

class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode start = dummy;
        while(dummy.next != null && dummy.next.next!=null){
            swap(dummy);
            dummy = dummy.next.next;
        }
        return start.next;
    }
    void swap(ListNode dummy){
        ListNode first = dummy.next;
        ListNode second = dummy.next.next;
        first.next = second.next;
        second.next = first;
        dummy.next = second;
    }
} 
