/*
https://leetcode.com/problems/wiggle-sort/
280. Wiggle Sort
Medium
Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
Input: nums = [3,5,2,1,6,4]
Output: One possible answer is [3,5,1,6,2,4]
*/
class Solution {
    // O(nlogn)
    public void wiggleSort1(int[] nums) {
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
    // O(n)
    public void wiggleSort(int[] nums) {
        boolean less = true;
        for (int i=0; i<nums.length-1; i++) {
            if (less) {
                if (nums[i]>nums[i+1])
                    swap(nums, i, i+1);
                }
            else {
                if (nums[i]<nums[i+1])
                    swap(nums, i, i+1);
            }
            less = !less;
        }
    }
    
    private void swap(int[] nums, int i, int j) {
       int temp = nums[i];
        nums[i]= nums[j];
        nums[j] = temp;
    }
    
}
