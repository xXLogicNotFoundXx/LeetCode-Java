/*
https://leetcode.com/problems/subsets/

Given a set of distinct integers, nums, return all possible subsets (the power set).
Note: The solution set must not contain duplicate subsets.

Input: nums = [1,2,3]
Output:
[ [], [1], [1,2], [1,2,3], [1,3], [2], [2,3], [3] ]

A set with n elements has 2^n subsets and max elements in a subset is n.

Time : O(n*2^n)  =  generate all subsets and then copy them into output list
Space : It is also O(n*2^n) = we store all the subsets.
*/
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start){
        list.add(new ArrayList<>(tempList));
        for(int i = start; i < nums.length; i++){
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }
}
