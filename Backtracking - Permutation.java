https://leetcode.com/problems/permutations/submissions/
class Solution {
    public List<List<Integer>> permute(int[] nums) {
       List<List<Integer>> list = new ArrayList<>();
       backtrack(list, new ArrayList<>(), nums);
       return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums){
       if(tempList.size() == nums.length){
          list.add(new ArrayList<>(tempList));
       } else{
          for(int i = 0; i < nums.length; i++){ 
             if(tempList.contains(nums[i])) continue; // element already exists, skip
             tempList.add(nums[i]);
             backtrack(list, tempList, nums);
             tempList.remove(tempList.size() - 1);
          }
       }
    } 
}

//Backtracking - Permutation_II.java
//Duplicates. 
    
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>>  ans = new ArrayList<List<Integer>>();
        if(nums.length == 0)
            return ans;
        
        Map<Integer,Integer> map = new<Integer,Integer>HashMap();
        for(int n : nums)
            map.put(n,map.getOrDefault(n,0)+1);
        
        int[] count = new int[map.size()], numbers = new int[map.size()];
        int i=0;
        for(Map.Entry<Integer,Integer> e : map.entrySet()){
            numbers[i] = e.getKey().intValue();
            count[i++] = e.getValue().intValue();  
        }
        
        List<Integer> subAns = new ArrayList<Integer>();
        permuteAvoidDuplicates(ans, subAns, numbers, count, nums.length); 
        return ans;
    }
    
    private void permuteAvoidDuplicates(List<List<Integer>> ans,  List<Integer> subAns, int[] numbers, int[] count, int ansSize){
        if(subAns.size()==ansSize)
            ans.add(new ArrayList<Integer>(subAns));
        
        for(int i=0;i<count.length;i++){
            if(count[i]==0)
                continue;
            
            subAns.add(numbers[i]);
            count[i]--;
            permuteAvoidDuplicates(ans, subAns, numbers, count, ansSize);
            count[i]++;
            subAns.remove(subAns.size()-1); 
            
        }
    }
}
