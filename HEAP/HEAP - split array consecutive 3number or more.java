/*
https://leetcode.com/problems/split-array-into-consecutive-subsequences/

Given an integer array nums that is sorted in ascending order, 
return true if and only if you can split it into one or more subsequences such 
that each subsequence consists of consecutive integers and has a length of at least 3.

Input: nums = [1,2,3,3,4,5]
Output: true
Explanation:
1, 2, 3
3, 4, 5

Input: nums = [1,2,3,3,4,4,5,5]
Output: true
You can split them into two consecutive subsequences : 
1, 2, 3, 4, 5
3, 4, 5


Case 1 : num == pq.peek().end, we offer a new interval (num, num) to pq => #1
Case 2 : num == pq.peek().end+ 1, we poll a interval prev, offer a new interval (prev.start, num) => #2
Case 3 : num > p1.peek().end + 1,
we keep abandoning intervals (if the length of the interval to abandon is smaller than 3, return false) until we could reduce to case 1 or case 2 => #3
The order of 3 cases above matters. For easier implementation, Case 3 should be checked first.

In the priority queue, all intervals are sorted by end increasingly, if there is a tie, we sort them by size increasingly.
*/

class Solution { 
    public boolean isPossible(int[] nums) {
        
        PriorityQueue<Interval> pq = new PriorityQueue<>(new Comparator<Interval>(){
            @Override
            public int compare(Interval a, Interval b) {
                if (a.end == b.end) {
                    return Integer.compare(a.length, b.length);
                }
                return Integer.compare(a.end, b.end);
            }
        }); 
        // PriorityQueue<Interval> pq = new PriorityQueue<>( (a, b) -> (a.end == b.end ? a.len - b.len: a.end - b.end ) );
        
        for (int num : nums) {
            while (!pq.isEmpty() && pq.peek().end + 1 < num) {
                if (pq.poll().length < 3)
                    return false;
            }
            if (pq.isEmpty() || pq.peek().end == num) {
                pq.add(new Interval(num, num));
            } else { // pq.peek().end + 1 == num
                pq.add(new Interval(pq.poll().start, num));
            }
        }
        
        while (!pq.isEmpty()) {
            if (pq.poll().length < 3)
                return false;
        }
        
        return true;
    }
    
    class Interval {
        int start;
        int end;
        int length;
        
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
            length = end - start + 1;
        }
    }
}

/*  Using deque and Priority Queue. 
Another subtle insight to this solution is proving that appending to a previous sequence is always better than creating a new sequence. 
*/
     public boolean isPossible(int[] nums) {
        
         PriorityQueue<Deque<Integer>> pq = new PriorityQueue<>( new Comparator<Deque<Integer>>(){
            public int compare(Deque<Integer> a, Deque<Integer> b){
                if(a.peekLast() == b.peekLast()){
                   return a.size() - b.size(); 
                } else {
                    return a.peekLast() - b.peekLast();
                }
            }
         });
        
        
         for(int i=0; i<nums.length; i++){

            while(!pq.isEmpty() && pq.peek().peekLast() < nums[i]-1){
                Deque<Integer> deque = pq.poll();
                if(deque.size()<3)
                    return false; 
            }
            
            if(pq.isEmpty()){
                Deque<Integer> deque = new ArrayDeque<Integer>();
                deque.add(nums[i]);
                pq.add(deque);
                continue; 
            }
            
            else if(pq.peek().peekLast() == nums[i]){
                Deque<Integer> newDeque = new ArrayDeque<Integer>();
                newDeque.add(nums[i]);
                pq.add(newDeque);
            }
             
            else if (pq.peek().peekLast() == nums[i]-1){
                Deque<Integer> deque = pq.poll();
                deque.add(nums[i]);
                pq.add(deque);
            }
            
         }
         
         while(!pq.isEmpty()){
            Deque<Integer> deque = pq.poll();
               if(deque.size()<3)
                   return false; 
         }
         return true;
     }
