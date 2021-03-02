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

/*
Kich kat problem ... bc itne corner cases ...
I think you have to first realize that each number can either be the beginning or the end of the sequence at a given point of iteration. 
Another subtle insight to this solution is proving that appending to a previous sequence is always better than creating a new sequence. 
Consider the problem [1,2,3,4,5,5,6,7] and how prioritizing the creation of a subsequence over appending would cause an issue.

class Solution2 {
    public boolean isPossible(int[] nums) {
         
        Map<Integer,Integer> mapCount = new HashMap<>();
        for( int n : nums ){
            mapCount.put(n, mapCount.getOrDefault(n,0)+1);
        }
        
        //    we need frequency too we cant use set 
        //    Bcz let say [4 4 5 5 6 6] by the time you process second 4th 
        //    we can accomodate two 7 
        //    so our map entry nextNumSequence entry should be <7,2> -> that is we can take two 7s
        
        HashMap<Integer,Integer> nextNumSequence = new HashMap<>();
        for(int i=0; i<nums.length; i++){
            
            int next2 = nums[i]+1;
            int next3 = nums[i]+2;
            
            // This number is already taken by previous subsequence
            if(mapCount.get(nums[i]) == 0)
                continue; 
            
            // we have to make sure we append to existing sequence before we 
            // absolutely need to create another sequence 
            // nextNumSequence tells us if there is a sequence that can take this number 
            if(nextNumSequence.getOrDefault(nums[i], 0) > 0 ){
                // put the next expected number that we cant take
                // so that if we come across nums[i]+1 number, we should put that in this sequence 
                nextNumSequence.put(nums[i]+1 , nextNumSequence.getOrDefault(nums[i]+1,0) + 1);
                
                // reduce current count 
                nextNumSequence.put(nums[i], nextNumSequence.get(nums[i])-1); 
                
                // delete one count of current number  from original map
                mapCount.put(nums[i], mapCount.get(nums[i]) - 1);
                continue;
            }
            
            // At this point we need next two elements ..if not there return false
            if(!mapCount.containsKey(next2)  ||  !mapCount.containsKey(next3))
                return false;
            
            // if there are next two numbers avialable then take those numbers 
            if(mapCount.get(next2) > 0 && mapCount.get(next3) > 0){
                // delete one count for this and next 3 numbers 
                mapCount.put(nums[i], mapCount.get(nums[i]) - 1);
                mapCount.put(next2, mapCount.get(next2) - 1);
                mapCount.put(next3, mapCount.get(next3) - 1);
                // we expect 4th number in the sequence now
                nextNumSequence.put(next3+1, nextNumSequence.getOrDefault(next3+1, 0) + 1 );  
            } else {
                return false;
            }
            
        }
        
        // dont have to split 
        // we just want to make sure all arrays has subsequence with 3 increasing numbers. 
        return true;
    }
}
*/
