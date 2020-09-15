/* 
https://leetcode.com/problems/merge-k-sorted-lists/
Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6 
Solution 1 : n^2 
    merger 2 linked list and then take 3rd , 4th to merge 
    it could be N^2  consider follwoing inlut list everytime we will be traversing those 100 nodes to add a node in a next list 
     1->2->....1000
     1001
     1002
Solution 2  N*Log(K)
     1 we can add start nodes of all linked list into MIN_HEAP (PriorityQueue)
     2 take one out from priority queue 
     3 we put then next node of the take node in step 2 
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
        
        PriorityQueue<ListNode> queue= new PriorityQueue<ListNode>(new sortByValye());
        
        ListNode Start = new ListNode(0);
        ListNode dummy=Start;
        
        for (ListNode node:lists){
            if (node!=null)
                queue.add(node);
        }
            
        while (!queue.isEmpty()){
            dummy.next=queue.poll();
            dummy=dummy.next;
            
            if (dummy.next!=null)
                queue.add(dummy.next);
        }
        return Start.next;
    }
}
