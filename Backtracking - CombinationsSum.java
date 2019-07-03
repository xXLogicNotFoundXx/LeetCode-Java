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
all elements in the candidate array are distinct and the array size is n 
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
