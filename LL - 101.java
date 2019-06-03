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

24. Swap Nodes in Pairs
https://leetcode.com/problems/swap-nodes-in-pairs/submissions/
Given 1->2->3->4, you should return the list as 2->1->4->3.
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
