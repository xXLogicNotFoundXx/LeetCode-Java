/*
https://leetcode.com/problems/4sum/

Given an array nums of n integers and an integer target, are there elements a, b, c,and d 
such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

*/class Solution {
    public List<List<Integer>> fourSum(int[] numbers, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(numbers);
        for (int i = 0; i < numbers.length - 3; i++) {
            if(i!=0 && numbers[i]==numbers[i-1]) continue;  // avoid duplicates 
            
            for (int j = i + 1; j < numbers.length - 2; j++) {
                if(j!=i+1 && numbers[j]==numbers[j-1]) continue; // avoid duplicates 
                
                int left = j + 1;
                int right = numbers.length - 1;
                
                while (left < right) {
                    int sum = numbers[i] + numbers[j] + numbers[left] + numbers[right];
                    if (sum == target) {
                        List<Integer> tuple = new ArrayList<Integer>();
                        tuple.add(numbers[i]);
                        tuple.add(numbers[j]);
                        tuple.add(numbers[left]);
                        tuple.add(numbers[right]);
                        result.add(tuple);
                        
                        while(left<right && numbers[left]==numbers[left+1])
                            left++; //skipping over duplicate on left right will move automatically 
                        
                        left++; 
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return result;
    }
}
