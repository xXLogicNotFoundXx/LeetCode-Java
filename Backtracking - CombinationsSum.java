/*
https://leetcode.com/problems/combination-sum/
Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
all elements in the candidate array are distinct and the array size is n.

C(n,1) + C(n,2) + ... + C(n,n) = 2^n - C(n,0) = O(2^n)

*/
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Map< Integer,List<List<Integer>> > map = new HashMap();
        Arrays.sort(candidates); // This will avoid duplications 
        List<List<Integer>> Ans  = new ArrayList<List<Integer>>();
        List<Integer> subAns = new ArrayList<Integer>();
        combinationSumHelper(candidates,target,Ans,subAns,0);
        return Ans;
    }
    
    void combinationSumHelper(int[] candidates, int target, List<List<Integer>> Ans, List<Integer> subAns, int start){
        if(target == 0){
            Ans.add(new ArrayList<Integer>(subAns));
            return;
        }
        
        if(target > 0){
            for(int i=start;i<candidates.length;i++){
                subAns.add(candidates[i]);
                combinationSumHelper(candidates,target - candidates[i],Ans,subAns,i); // not i + 1 because we can reuse same elements
                subAns.remove(subAns.size()-1);
            }
        }
    }
}


/*
Combination Sum II with duplucates 
https://leetcode.com/problems/combination-sum-ii/

Given a collection of candidate numbers (candidates) and a target number (target), 
find all unique combinations in candidates where the candidate numbers sums to target.
Each number in candidates may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:
Input: candidates = [10,1,2,7,6,1,5], target = 8,
A solution set is:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]

time complexity = O(n!)
space O(m) m is the size of the Ans array 
*/
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> Ans  = new ArrayList<List<Integer>>();
        List<Integer> subAns = new ArrayList<Integer>();
        combinationSumHelper(candidates,target,Ans,subAns,0);
        return Ans;
    }
    
    void combinationSumHelper(int[] candidates, int target, List<List<Integer>> Ans, List<Integer> subAns, int start){
        if(target == 0){
            Ans.add(new ArrayList<Integer>(subAns));
            return;
        }
        
        if(target > 0){
            for(int i=start;i<candidates.length;i++){
                if(target - candidates[i] < 0) // this is good because we dont want to process all the array
                    break;
                if(i>start && candidates[i]==candidates[i-1]) 
                    continue;
                subAns.add(candidates[i]);
                combinationSumHelper(candidates,target - candidates[i],Ans,subAns,i+1);
                subAns.remove(subAns.size()-1);
            }
        }
    }
}
