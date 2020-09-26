/*
503. Next Greater Element II
https://leetcode.com/problems/next-greater-element-ii/
Given a circular array (the next element of the last element is the first element of the array), 
print the Next Greater Number for every element.
*/
class Solution {
    public int[] nextGreaterElements(int[] nums) {
        
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans,-1);
        
        int j=0;
        Deque<Integer> stack = new ArrayDeque<>();
        while(j < 2*n){
            while(!stack.isEmpty() && nums[stack.peek()] < nums[j%n]){
                ans[stack.peek()] = nums[j%n];
                stack.pop();
            }
            stack.push(j%n);
            j++;
        }
        
        return ans;
    }
}
