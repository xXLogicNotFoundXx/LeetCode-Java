/*
Medium - IMP
https://leetcode.com/problems/palindrome-linked-list/

 1 add values in the ArrayList & check  if they are same starting from end of the array list
 2 reverese the linked list and see if two linked lists are equal
 but that would be o(n) extra space
 fast pointer to send it and find mid point but then if even number then n/2 is the middle or n/2+1 is the middle? too complicated.
*/
class Solution {
    public boolean isPalindrome(ListNode head) {
        if(head==null || head.next==null) return true;
        ListNode slow = new ListNode(0);
        ListNode fast = slow;
        slow.next = head;
        ListNode middle = null;

        while(fast!=null){
            if(fast.next == null){ // even number perfect partition
                middle = slow.next;
                slow.next = null;
                 break;
            }
            if(fast.next.next==null){ // odd numbers so forget slow.next
                middle = slow.next.next;
                slow.next = null;
                break;
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        // Reveerse the first half list
        ListNode dummy = null;
        while(head!=null){
            ListNode temp = head.next;
            head.next = dummy;
            dummy = head;
            head = temp;
        }
        head= dummy;
        while(head!=null){
            if(head.val != middle.val)
                return false;
            head=head.next;
            middle=middle.next;
        }
        return true;
    }
}
