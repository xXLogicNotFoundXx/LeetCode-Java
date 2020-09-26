/*
496. Next Greater Element I
https://leetcode.com/problems/next-greater-element-i/
 

You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2. Find all the next greater numbers for nums1's elements in the corresponding places of nums2.

nums1 are essentially queries for the numbers in nums2 array. 
Finding next greater element for numbers in nums1 based on array nums2. 


Same as Next Greater Element OR Daily Temprature problem.

nums1 = [4,1,2], nums2 = [1,3,4,2].

Stack 
                            2
1       3           4       4

Map 
--       <1,3>      <3,4> 
*/
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer,Integer> map = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>();
        
        for(int i=0;i<nums2.length;i++){
            
            while(!stack.isEmpty() && stack.peek() < nums2[i]){
                map.put(stack.peek(),nums2[i]);
                stack.pop();
            }
            
            stack.push(nums2[i]);
        }
        
        for(int i=0;i<nums1.length;i++)
            nums1[i] = map.getOrDefault(nums1[i],-1);
        
        return nums1;
    }
}
