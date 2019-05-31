/*
https://leetcode.com/problems/wiggle-sort/
280. Wiggle Sort
Medium
Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
Input: nums = [3,5,2,1,6,4]
Output: One possible answer is [3,5,1,6,2,4]
*/
class Solution {
    public void wiggleSort(int[] nums) {
        PriorityQueue<Integer> pqMin = new PriorityQueue<>();
        PriorityQueue<Integer> pqMax = new PriorityQueue<>(Collections.reverseOrder());
        for(int i : nums){
            pqMax.add(i);
            pqMin.add(pqMax.poll());
            if(pqMax.size()<pqMin.size())
                pqMax.add(pqMin.poll());
        }
        int i=0;
        while(pqMax.size()!=0){
            nums[i++] = pqMax.poll(); 
            if(pqMin.size()!=0)
                nums[i++] = pqMin.poll(); 
        }
    }
}
