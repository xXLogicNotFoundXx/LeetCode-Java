/*
167. Two Sum II - Input array is sorted
https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.
The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.
*/
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        if ( numbers==null || numbers.length == 0) {
            return new int[2];
        }
        int[] result = new int[2];
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right]; // overflow can happen .. just use long 
            if (sum == target) {
                result[0] = left + 1;
                result[1] = right + 1;
                return result;
            }
            if (sum < target)
                left++;
            else
                right--;
        }
        return result;
    }

    /* If numbers are not sorted */
    public int[] twoSum(int[] numbers, int target) {
        // save the difference in map with index
        // if difference found in map then return both indices
        int[] ans = new int[2];
        if(numbers==null) return ans;

        HashMap<Integer,Integer> table = new HashMap<Integer,Integer>();
        for(int i=0;i<numbers.length;i++){
            if(table.containsKey(numbers[i])){
                ans[0] = table.get(numbers[i]);
                ans[1] = i+1;
                return ans;
            }
            table.put(target-numbers[i],i+1);
        }
        return ans;
    }
}
