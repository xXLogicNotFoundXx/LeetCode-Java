/*
https://leetcode.com/problems/sliding-window-maximum/
Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.

Example:

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
*/
/*
if the the array is sorted we will never poll any element from priority queue. 
O(N*logN)
*/
class Solution {

    class Pair{
        int val;
        int index;
        Pair(int val, int index){
            this.val = val;
            this.index = index;
        }
    }
    
    public int[] maxSlidingWindow(int[] nums, int k) {
       
        if(nums==null||nums.length==0 ) 
            return new int[0];
        
        PriorityQueue<Pair> maxHeap = new PriorityQueue<Pair>(new Comparator<Pair>(){
            public int compare(Pair a, Pair b){
                return b.val-a.val;
            }
        });
        
        int N = nums.length;
        int K = k>N ? N:k;
        
        int []result = new int[N-K+1];
        int j=0;
        
        for(int i=0;i<nums.length;i++){
            maxHeap.add(new Pair(nums[i],i));
            
            while(maxHeap.size()>0 && i-maxHeap.peek().index>=K)
                maxHeap.poll();
            
            if(maxHeap.size()>=k)
                result[j++] = maxHeap.peek().val;
        }
        return result;
    }
}
