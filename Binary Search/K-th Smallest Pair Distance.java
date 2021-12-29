/*\
Hard - very nice concept thouigh.
In last 2 years Google2 Facebook2

Find K-th Smallest Pair Distance

https://leetcode.com/problems/find-k-th-smallest-pair-distance/
Given an integer array, return the k-th smallest distance among all the pairs.
The distance of a pair (A, B) is defined as the absolute difference between A and B.

Example 1:
Input:
nums = [1,3,1]
k = 1
Output: 0
Explanation:
Here are all the pairs:
(1,3) -> 2
(1,1) -> 0
(3,1) -> 2
Then the 1st smallest distance pair is (1,1), and its distance is 0.
*/
class Solution {

    // HEAP - Time Limit Exceeded
    // Still very good idea.
    // With N numbers we can form N^2 pairs.
    // If we form all those and put in HEAP it will be
    //  N^2*log(N^2) + K*log(N^2) [poll k elements from MIN_HEAP]
    // BUT we can improove with MAX_HEAP
    // we can just maintain k elements in the MAX_HEAP in N^2 loop
    // N^2 + log.K
    public int smallestDistancePair1(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for(int i=0; i<nums.length;i++){
            for(int j=i+1; j<nums.length; j++){

                int diff = Math.abs(nums[i]-nums[j]);
                if(pq.size()<k){
                    pq.offer(diff);
                }
                else if(pq.peek() > diff){
                    pq.poll();
                    pq.offer(diff);
                }
            }
        }
        return pq.peek();
    }


    // BINARY SEARCH
    public int smallestDistancePair(int[] nums, int k) {

        Arrays.sort(nums);

        int left = 0;
        int right = nums[nums.length-1]-nums[0];

        while(left<right){
            int mid = left + (right-left)/2;

            int count = countPairsLessOrEqual(nums, mid);

            if(count>=k)
                right = mid;
            else
                left = mid+1;
        }

        return left;
    }
    // ****** This is a good example on how to calcluate .. comulative numbers of how many question. 
    int countPairsLessOrEqual(int[] nums, int mid){

        int count = 0;
        //count = number of pairs with distance <= mi
        int j=1;
        for(int i=0;i<nums.length;i++){

            while(j<nums.length && nums[j]-nums[i] <= mid) // this is good logic
                j++;

            // we have seen this same logic in the problem  Subarray Product Less Than K (How Many?)

            count += j-i-1;
        }

        return count;
    }
}
