https://leetcode.com/problems/kth-largest-element-in-an-array/solution/
class Solution {
    /*
    add all elements from the array into min_heap one by one keeping the size of the heap always less or equal to k
    */
    public int findKthLargest(int[] nums, int k) {
        // init heap 'the smallest element first'
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>();

        // keep k largest elements in the heap
        for (int n: nums) {
          heap.add(n);
          if (heap.size() > k)
            heap.poll();
        }

        // output
        return heap.poll(); 
    }
}
