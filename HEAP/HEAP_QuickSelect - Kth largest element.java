https://leetcode.com/problems/kth-largest-element-in-an-array/solution/
class Solution {
    /*
    add all elements from the array into min_heap one by one keeping the size of the heap always less or equal to k
    NLogK
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

    /*
      Quick Select avg O(N) 
    */
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0 ||
            k < 1 || k > nums.length)
            return -1;

        int start =0, end = nums.length-1;
        k = nums.length - k; // k becomes the index we are trying to find

        while(start<end){
            int pIndex = quickSelectPartition(nums,start,end);

            if(pIndex==k)
                return nums[pIndex];

            if(pIndex<k)
                start = pIndex+1;
            else
                end = pIndex-1;
        }
        return nums[start]; // input : [1] 1
    }

    int quickSelectPartition(int []nums,int start,int end){
        int pivot = nums[end];
        int pIndex = start;   // very IMP
        for(int i=start;i<end;i++){
            if(nums[i]<=pivot){
                swap(nums,pIndex,i);
                pIndex++;
            }
        }
        swap(nums,pIndex,end);
        return pIndex;
    }

    void swap(int []nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
