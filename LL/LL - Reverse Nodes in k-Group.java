/*
https://leetcode.com/problems/reverse-nodes-in-k-group/
Reverse Nodes in k-Group
Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
Given this linked list: 1->2->3->4->5
For k = 2, you should return: 2->1->4->3->5
For k = 3, you should return: 3->2->1->4->5
 */

class Solution {

    // Solution1 : O(n) and O(k) space
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode();
        ListNode HEAD = dummy;
        dummy.next = head;

        Deque<ListNode> stack = new ArrayDeque<>();

        while(head!=null){
            int count=k;

            while(count>0 && head!=null ){
                stack.push(head);
                head = head.next;
                count--;
            }

            if(stack.size()!=k)
                break;

            while(!stack.isEmpty() && dummy!=null){
                dummy.next = stack.pop();
                dummy = dummy.next;
            }

            dummy.next=head;
        }
        return HEAD.next;
    }

    // Solution12 : O(n) and O(1) space
    public ListNode reverseKGroup(ListNode head, int k) {

        if(head==null || k<=1)
            return head;

        ListNode dummyHead = new ListNode(-99, head);
        dummyHead.next = head;
        ListNode START = dummyHead;

        while(dummyHead!=null){
            dummyHead = reverseKGroupHelper(dummyHead,k);
        }

        return START.next;
    }

    ListNode reverseKGroupHelper(ListNode dummyHead, int k){
        ListNode runner = dummyHead;
        ListNode actualHead = dummyHead.next; // After reverse this will be at last and will be our next dummyHead

        while(runner.next!=null && k>0){
            runner =runner.next;
            k--;
        }

        if(k>0)
            return null;

        ListNode tailNext = runner.next;
        reverse(dummyHead,tailNext);

        return actualHead; // this is our new dummyHead
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

        dummyHead.next.next = tailNext;
        dummyHead.next = prev;
    }


}
