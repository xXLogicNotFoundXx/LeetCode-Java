/*
https://leetcode.com/problems/subsets/
Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.
Example:
Input: nums = [1,2,3]
Output:
[ [3], [1], [2], [1,2,3], [1,3], [2,3], [1,2], [] ]

While iterating through all numbers, for each new number, we can either pick it or not pick it
1, if pick, just add current number to every existing subset.
2, if not pick, just leave all existing subsets as they are.
We just combine both into our result.

For example, {1,2,3} intially we have an emtpy set as result [ [ ] ]
Considering 1, if not use it, still [ ], if use 1, add it to [ ], so we have [1] now
Combine them, now we have [ [ ], [1] ] as all possible subset

Next considering 2, if not use it, we still have [ [ ], [1] ], if use 2, just add 2 to each previous subset, we have [2], [1,2]
Combine them, now we have [ [ ], [1], [2], [1,2] ] 
*/
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>(); 
        ans.add(new ArrayList());
        for(int n : nums){
            List<List<Integer>> subAns = new ArrayList<List<Integer>>(); 
            for(List<Integer> list : ans){
                subAns.add(new ArrayList<Integer>(list));
            }
            
            for(List<Integer> list: subAns){
                  list.add(n);  
            }
            
            for(List<Integer> list : ans){
                subAns.add(list);
            }
            
            List<List<Integer>> temp = subAns;
            subAns = ans;
            ans = temp;
        }
        return ans;
    }
}
