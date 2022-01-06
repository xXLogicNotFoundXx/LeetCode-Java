/*
Hard - but not really - VVIMP
Facebook45 Amazon38 Microsoft14 Google7

https://leetcode.com/problems/merge-k-sorted-lists/
Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6

N*Log(K)
     1 we can add start nodes of all linked list into MIN_HEAP (PriorityQueue)
     2 take one out from priority queue
     3 we put then next node of the taken node in step 2
     4 repeat 2 and 3 untill minheap is null
*/
class Solution {

    class sortByValye implements Comparator<ListNode>{
        public int compare(ListNode a,ListNode b){
               return a.val - b.val;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists==null || lists.length==0) return null;

        PriorityQueue<ListNode> minHeap= new PriorityQueue<ListNode>(new sortByValye());

        ListNode Start = new ListNode(0);
        ListNode dummy=Start;

        for (ListNode node:lists){
            if (node!=null)
                minHeap.add(node);
        }

        while (!minHeap.isEmpty()){
            dummy.next=minHeap.poll();
            dummy=dummy.next;

            if (dummy.next!=null)
                minHeap.add(dummy.next);
        }
        return Start.next;
    }


    // Can you do it in O(1) space.
    // O(N^2) .. if all lists are sorted then we would keep traversing same number agian and again
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists==null || lists.length==0) return null;

        ListNode first = lists[0];
        for(int i=1; i<lists.length; i++){
          ListNode second = lists[i];
          first = mergeLists(first, second);
        }
        return first;
    }

    ListNode mergeLists(ListNode first, ListNode second){
        ListNode dummy = new ListNode();
        ListNode start = dummy;
        while(first!=null && second!=null){
          if(first.val<= second.val){
            dummy.next = first;
            first = first.next;
          } else {
            dummy.next = second;
            second = second.next;
          }
          dummy = dummy.next;
        }

        dummy.next = first == null ? second : first;
        return start.next;
    }

    // You can pair two-two lists and merge divide and conquer 
}
