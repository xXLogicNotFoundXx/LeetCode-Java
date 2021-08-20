/*
Medium 
Apple6 Amazon5 Zillow4 Yandex3 Google2 Microsoft2

https://leetcode.com/problems/design-hit-counter/
Design a hit counter which counts the number of hits received in the past 5 minutes.

Each function accepts a timestamp parameter (in seconds granularity) and you may assume that calls are being made to the system in chronological order 
(ie, the timestamp is monotonically increasing). You may assume that the earliest timestamp starts at 1.

It is possible that several hits arrive at the same time.


*/

class HitCounter {
    Queue<Integer> queue;

    /** Initialize your data structure here. */
    public HitCounter() {
        queue = new LinkedList<Integer>();
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        queue.add(timestamp);
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        while(!queue.isEmpty() && queue.peek() <= timestamp - 300) {
            queue.remove();
        }
        return queue.size();
    }
}

/*
Follow up:
What if the number of hits per second could be very large? Does your design scale?
Hit method should be very fast.

Usually in cases where we dont need the data after certain window or time or size. 
    -- You could always use Deque and use sliding window algorithm. 
    -- I think mainintaining first entry and last entry diff 300 seconds .. 
    -- you add at the end and remove it from the front .. you can keep the 
    -- both funciton will max O(300) 
    

Solution : 1 - 1ms 
hit - O(300)        amortized O(1) 
getHits - O(300)    amortized O(1) 

*/
class HitCounter {
    class Node {
        int time;
        int hits;
        
        public Node(int t, int h){
            time=t;
            hits=h;
        }
    }
    
    Deque<Node> dq = new ArrayDeque<Node>();
    int total = 0;
    
    /** Initialize your data structure here. */
    public HitCounter() {      
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    // Time : O(300) we end up removing all entrie in the deque 
    // amortized 
    public void hit(int timestamp) {
        
        if(dq.size()==0 || dq.peekLast().time != timestamp)
            dq.add(new Node(timestamp,1));
        else 
            dq.peekLast().hits++;
        
        total+=1;
        
        
        // we are writting here to maintain only 300 entries 
        while(!dq.isEmpty() && timestamp-dq.peekFirst().time >=300){
            total -= dq.peekFirst().hits;
            dq.pollFirst();
        }
            
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    // Time : O(300) we end up removing all entrie in the deque 
    public int getHits(int timestamp) {
        
        // timestamp could be way bigger than last entry so we do 
        // have to remove some entries 
        while(!dq.isEmpty() && timestamp-dq.peekFirst().time >=300){
            total -= dq.peekFirst().hits;
            dq.pollFirst();
        }
        
        return total;
    }
}


/*
Solution : 2 - 1ms 

hit - O(1) 
getHits - O(300)
*/
public class HitCounter {
    private int[] timeStampRecord;
    private int[] hits;
    /** Initialize your data structure here. */
    public HitCounter() {
        timeStampRecord = new int[300];
        hits = new int[300];
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        int index = timestamp % 300;
        if (timeStampRecord[index] != timestamp) {
            timeStampRecord[index] = timestamp;
            hits[index] = 1;
        } else {
            hits[index]++;
        }
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        int total = 0;
        for (int i = 0; i < 300; i++) {
            if (timestamp - timeStampRecord[i] < 300) {
                total += hits[i];
            }
        }
        return total;
    }
}
