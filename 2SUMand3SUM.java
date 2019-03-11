
https://leetcode.com/problems/two-sum/
Given an array of integers, return indices of the two numbers such that they add up to a specific target.
Given nums = [2, 7, 11, 15], target = 9,
return [0, 1]
// We do target-nums[i] and put that in the map with index 
// if we find that number going through loop we found the two indices  

class Solution {
    public int[] twoSum(int[] nums, int target) {
        if(nums==null) return null;
        Map<Integer,Integer> sumMap = new HashMap<Integer,Integer>(); 
        for(int i=0;i<nums.length;i++){
            if(sumMap.containsKey(nums[i]))
                return new int[]{sumMap.get(nums[i]),i};
            sumMap.put(target-nums[i],i);
        }
        return null;
    }
}

//The key idea is the same as the TwoSum problem. 
//When we fix the 1st number, the 2nd and 3rd number can be found following the same reasoning as TwoSum.
class Solution3SumTo0 {
    public List<List<Integer>> threeSum(int[] num) {
        Arrays.sort(num);
        List<List<Integer>> res = new ArrayList<>(); 
        for (int i = 0; i < num.length-2; i++) {
            if (i == 0 || (i > 0 && num[i] != num[i-1])) {
                int lo = i+1, hi = num.length-1, sum = 0 - num[i];
                while (lo < hi) {
                    if (num[lo] + num[hi] < sum){
                        lo++;
                    }else if (num[lo] + num[hi] > sum){
                        hi--;
                    }else{
                        List<Integer> set = new ArrayList<Integer>();
                        set.add(num[i]);
                        set.add(num[lo]);
                        set.add(num[hi]);
                        res.add(set);
                        while (lo < hi && num[lo] == set.get(1)) lo++;
                        // we dont need to change hi bcz low is changed 
                        // so hi will change naturally 
                        // while (lo < hi && num[hi] == set.get(2)) hi--;
                    } 
               }
            }
        }
        return res; 
    }
}