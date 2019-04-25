/*
https://leetcode.com/problems/3sum/
Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? 
Find all unique triplets in the array which gives the sum of zero.
The solution set must not contain duplicate triplets.
*/
class Solution {
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
