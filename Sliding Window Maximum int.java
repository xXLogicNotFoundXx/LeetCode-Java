https://leetcode.com/problems/sliding-window-maximum/
Given an array nums, there is a sliding window of size k which is moving from the very 
left of the array to the very right.
You can only see the k numbers in the window.
Each time the sliding window moves right by one position. Return the max sliding window.
Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
Output: [3,3,5,5,6,7] 
Explanation: 

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
class Solution {
    /*
    In an output array they will be N-K+1 elements.
    The first idea is to use a heap, since in a maximum heap  will give us maximum 
    then remove() and insert operation both are log K so total time is n*logK
    By default Min Heap is implemented by PriorityQueue
    To implement Max Heap, we use Collections.reverseOrder()
    PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>(Collections.reverseOrder());
     // same as queue operation
    pQueue.add(m)   // log n
    pQueue.poll()  // log n 
    pQueue.peek()
    pQueue.remove(m);  // Removing Java using remove()
    O( (N-K+1) * (log(K)(insert) + K(remove)) )
    */
    public int[] maxSlidingWindow(int[] nums, int k) {
       
        if(nums==null||nums.length==0 ) return new int[0];
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
        int N = nums.length;
        int K = k>N ? N:k;
        int []result = new int[N-K+1];
        for(int i=0;i<K;i++)
            maxHeap.add(nums[i]);
       
        int i=0,j=K;
        while(j<N){
            result[i++] = maxHeap.peek();
            maxHeap.remove(nums[j-K]);
            maxHeap.add(nums[j++]);            
        }
        result[i] = maxHeap.poll();
        return result;
    }
}
