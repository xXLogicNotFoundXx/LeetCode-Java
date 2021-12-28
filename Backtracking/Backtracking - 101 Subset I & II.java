/*
Facebook24  Amazon8 Google4 ByteDance4 Bloomberg3 Goldman Sachs3 Adobe2 Paypal2 Twitter2
VIMP
https://leetcode.com/problems/subsets/

Given a set of distinct integers, nums, return all possible subsets (the power set).
Note: The solution set must not contain duplicate subsets.

Input: nums = [1,2,3]
Output:
[ [], [1], [1,2], [1,2,3], [1,3], [2], [2,3], [3] ]

A set with n elements has 2^n subsets and number of elements in a set is n.

Time : O(n * 2^n)  =  generate all subsets and then copy them into output list
Space : It is also O(n * 2^n) = we store all the subsets.
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


/*
Subsets II with duplicates
https://leetcode.com/problems/subsets-ii/

We are supposed to return all unique subsets.
Time : O(n * 2^n)
Space : It is also O(n * 2^n) = we store all the subsets.
*/
class Solution {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);

        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start){

        list.add(new ArrayList<>(tempList));

        for(int i = start; i < nums.length; i++){
            // this is what exactly we did for combination sum with duplicate problem . nice trick 
            if(i>start && nums[i]==nums[i-1]) // this is what handles the dulicates [1, 1, 1, 1, 2, 3]
                continue;

            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }
}
