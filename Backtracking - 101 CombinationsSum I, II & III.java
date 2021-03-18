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

// as with each backtracking call fan out is N  ( every backtrack call can call N backtracking)
// So obviously it will be N^*
// The maximum depth of the tree could be T/M  ( T is total and M is minimum number)
// so height of the tree from original functional call would be (T/M+1)
// so that gives us N^(T/M+1)
// Space is O(T/M) thats the height of the tree 

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

// Amazon - How would you do combination of sum for 2D array?
// sorting of 2D array - > https://github.com/xXLogicNotFoundXx/LeetCode-Java/blob/master/2D/101%20-%202D%20Array%20sort.java
// then you can have 0 to m*n index 
// for k index 2d[k/col][k%col] would be your item in 2D

// how about if asked how many ways are there to have target? 
// check this out 
// https://leetcode.com/problems/coin-change-2/                 <-  Check this out too. 
// it becomes classic knapsack problem.

/*
Combination Sum II with duplucates 
https://leetcode.com/problems/combination-sum-ii/

Combination ... 
nCr={n!}/{r! (n-r)!}

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

Time complexity = O(2^N) we either select a number or we dont ... height of the tree is N/ 
Space O(m) m is the size of the Ans array 
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
                combinationSumHelper(candidates,target - candidates[i],Ans,subAns,i+1); // i+1 we are not using same numbers unlike last problem 
                subAns.remove(subAns.size()-1);
            }
        }
    }
}

/*
Find all possible combinations of k numbers that add up to a number n, 
given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

Note:
All numbers will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: k = 3, n = 7
Output: [[1,2,4]]
Example 2:

Input: k = 3, n = 9
Output: [[1,2,6], [1,3,5], [2,3,4]]


The deepest level that the recursion can go is k.
C(9,k) that is O(9 choose k)
time complexity = O(9^k)

*/

class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        
        if(k<0 || n<0)
            return ans; 
        
        List<Integer> oneAns = new ArrayList<>();
        combinationSum3Helper(ans,oneAns,1,k,n);
        return ans;
    }
    
    void combinationSum3Helper(List<List<Integer>> ans,List<Integer> oneAns, 
                               int start, int k, int sum){
        if(oneAns.size()==k && sum==0){
            List<Integer> finalOneAns = new ArrayList<Integer>(oneAns);
            ans.add(finalOneAns);
            return;
        }
        
        for(int i=start;i<=9;i++){
            if(i>sum) // this is good because we dont want to process remaining numbers 
                return; 
            
            oneAns.add(i);  
            combinationSum3Helper(ans,oneAns,i+1, k, sum-i);  
            oneAns.remove(oneAns.size()-1);  
        }
    }
}
