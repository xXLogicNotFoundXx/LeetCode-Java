Note : 
Alwasy use dummy node before the start it makes easy for corner cases.


// Reverse a Linked list 
// https://leetcode.com/problems/reverse-linked-list/
class Solution {
    // iterative 
    public ListNode reverseList1(ListNode head) {
        ListNode dummy = null;
        while(head!=null){
            ListNode temp = head.next;
            head.next = dummy;
            dummy = head;
            head = temp;
        }
        return dummy;
    }
    
    // Recursive 
    public ListNode reverseList(ListNode head) {
        return reverseList12(head,null);
    }
    ListNode reverseList12(ListNode current,ListNode prev) {
        if(current==null) return prev;
        ListNode temp = current.next;
        current.next = prev;
        prev = current;
        return reverseList12(temp,prev);
    }
}


//Input: 1->2->4, 1->3->4
//Output: 1->1->2->3->4->4
// Trick here create a dummy START and return START.next 
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode START = new ListNode(0);
        ListNode dummy = START;
        while(l1!=null && l2!=null){
            if(l1.val > l2.val){
                dummy.next = l2;
                l2 = l2.next;
            } else {
                dummy.next = l1;
                l1 = l1.next;
            }
            dummy = dummy.next;
        }
        dummy.next = l1 == null ? l2 : l1;
        return START.next;
    }
}

// https://leetcode.com/problems/delete-node-in-a-linked-list/
// Delete node, direct access to delete the node. 
// It wont be a last node.
class Solution {
    
    public void deleteNode(ListNode node) {
        ListNode  dummy = node; 
        while(dummy.next.next!=null){
            dummy.val = dummy.next.val; 
            dummy = dummy.next;
        }
        dummy.val = dummy.next.val;
        dummy.next=null;
    }
    
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
