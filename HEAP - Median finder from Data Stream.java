/*
https://leetcode.com/problems/find-median-from-data-stream/solution/
Median is the middle value in an ordered integer list. 
If the size of the list is even, there is no middle value. 
So the median is the mean of the two middle value.

For example,
[23] =>  23
[2,3], the median is (2 + 3) / 2 = 2.5
[2,8,10] => 8

*/

class MedianFinder {
    PriorityQueue<Integer> leftMaxHeap = new PriorityQueue<Integer>(Collections.reverseOrder()); 
    PriorityQueue<Integer> rightMinHeap =  new PriorityQueue<Integer>(); 
    /** initialize your data structure here. */
    public MedianFinder() {
    }
   
    public void addNum(int num) {
        leftMaxHeap.offer(num);
        rightMinHeap.offer(leftMaxHeap.poll());
        if (leftMaxHeap.size() < rightMinHeap.size()){
            leftMaxHeap.offer(rightMinHeap.poll());
        }
    }

    // Returns the median of current data stream
    public double findMedian() {
        if (leftMaxHeap.size() == rightMinHeap.size()) 
            return leftMaxHeap.peek()/2.0 + rightMinHeap.peek()/2.0;
        else return leftMaxHeap.peek();
    }
    
}
