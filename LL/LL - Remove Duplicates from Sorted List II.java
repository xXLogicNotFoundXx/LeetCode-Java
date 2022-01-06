// https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
// Input: 1->2->3->3->4->4->5
// Output: 1->2->5
class Solution {
    public ListNode deleteDuplicates(ListNode head) {

        ListNode fakeStart = new ListNode(-1);
        fakeStart.next = head;

        ListNode start = fakeStart;
        while(start.next!=null){
            ListNode current = start.next;

            while(current.next!=null && current.val == current.next.val){
                current = current.next;
            }

            if(start.next == current){ // there were no duplicates
                start = start.next;
            } else { // if there were duplicates update the start.next 
                start.next = current.next;
            }
        }

        return fakeStart.next;
    }
}
