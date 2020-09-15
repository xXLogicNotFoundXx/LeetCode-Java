/*
https://leetcode.com/problems/next-greater-node-in-linked-list/

Input: 2->1->5
Output: [5,5,0]

Input: 2->7->4->3->5]
Output: [7,0,5,5,0]

Input: 1->7->5->1->9->2->5->1
Output: [7,9,9,9,0,5,0,0]
*/

class Solution {
    public int[] nextLargerNodes(ListNode head) {
        if(head==null)
            return new int[0];
        
        Stack<Integer> stack = new Stack<>();
        while(head!=null){
            stack.push(head.val);
            head = head.next;
        }
        
        int[] ans = new int[stack.size()];
        // minHeap even if you dont use Comparator.naturalOrder()
        // Comparator.reverseOrder() is maxHeap
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(Comparator.naturalOrder()); 
        
        for(int i=stack.size()-1;i>=0;i--){
           
            while(minHeap.size()!=0 && 
                  minHeap.peek()<=stack.peek()){
                minHeap.poll();
            }

            if(minHeap.size()==0)
                ans[i]=0;
            else 
                ans[i]=minHeap.peek();
                
            minHeap.offer(stack.pop());
        }
        
        return ans;
    }
}
