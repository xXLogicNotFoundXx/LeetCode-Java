/*
Combination Sum II with duplucates 
https://leetcode.com/problems/combination-sum-ii/
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
