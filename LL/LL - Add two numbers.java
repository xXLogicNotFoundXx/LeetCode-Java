// Medium - 
// https://leetcode.com/problems/add-two-numbers-ii/
// Input: (7 -> 2 -> 4 -> 3) +
//             (5 -> 6 -> 4)
// Output: 7 -> 8 -> 0 -> 7

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverse(l1);
        l2 = reverse(l2);
        return reverse(addTwoNumbers2(l1,l2));
    }

    ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode start = new ListNode(0);
        ListNode dummy= start;
        int carry = 0;

        while(l1 != null || l2 != null){
            int val = (l1==null? 0 : l1.val) + (l2==null ? 0 : l2.val ) + carry;
            carry = val/10;
            val = val%10;

            dummy.next = new ListNode(val);
            dummy = dummy.next;

            l1 = l1==null ? null : l1.next;
            l2 = l2==null ? null : l2.next;

        }

        if(carry!=0)
           dummy.next = new ListNode(carry);

        return start.next;
    }

    ListNode reverse(ListNode head){ // This is important too
        ListNode dummy = null;
        while(head!=null){
            ListNode temp = head.next;
            head.next = dummy;
            dummy = head;
            head = temp;
        }
        return dummy;
    }
}
