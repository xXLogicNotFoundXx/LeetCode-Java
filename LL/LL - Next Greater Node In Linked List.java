/*
https://leetcode.com/problems/next-greater-node-in-linked-list/
    (Similar to Daily Temperatures problem)
    https://leetcode.com/problems/daily-temperatures/
    https://github.com/xXLogicNotFoundXx/LeetCode-Java/edit/master/Stack%20-%20Daily%20Temperatures%20find%20next%20warm.java
    
Input: 2->1->5
Output: [5,5,0]

Input: 2->7->4->3->5]
Output: [7,0,5,5,0]

Input: 1->7->5->1->9->2->5->1
Output: [7,9,9,9,0,5,0,0]
*/

class Solution {
    
    // O(N)
    public int[] nextLargerNodes(ListNode head) {
        Deque<Pair<Integer, Integer>> stack = new LinkedList<>();
        //Map<Integer, Integer> map = new HashMap<>();
        int length = 0;
        ListNode tmp = head;
        while(tmp!=null){
            length++;
            tmp=tmp.next;
        }
        int[] result = new int[length];
        int index = 0;
        while(head!=null){
            while(!stack.isEmpty() && stack.peek().getValue() < head.val) {
                result[stack.pop().getKey()]=head.val;
            }
            stack.push(new Pair<Integer, Integer>(index, head.val));
            head = head.next;
            index++;
        }
        return result;
    }
    
    // O(N)
    public int[] nextLargerNodes1(ListNode head) {
        if(head==null)
            return new int[0];
        
        Deque<Integer> stack = new ArrayDeque<>();
        ArrayList<Integer> aList = new ArrayList<>();
        while(head!=null){
            aList.add(head.val);
        }
        
        int[] ans = new int[aList.size()];
        
        for(int i=0;i<aList.size();i++){
            int currentNumber = aList.get(i);
            
            while(!stack.isEmpty() && aList.get(stack.peek()) < currentNumber){
                ans[stack.pop()] = currentNumber;
            }
            stack.push(i);
        }
        
        while(!stack.isEmpty())
            ans[stack.pop()] = 0;
        
        return ans;
    }
    // Time: (N*Log(N))  (if it is sorted it will be N insertions in minHeap)
    // Space : N
    public int[] nextLargerNodes2(ListNode head) {
        if(head==null)
            return new int[0];
        
        Deque<Integer> stack = new ArrayDeque<>();
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
