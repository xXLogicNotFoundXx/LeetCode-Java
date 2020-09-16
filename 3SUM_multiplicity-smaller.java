/*
https://leetcode.com/problems/3sum-with-multiplicity/
Given an integer array A, and an integer target, return the number of tuples i, j, k  
such that i < j < k and A[i] + A[j] + A[k] == target.

As the answer can be very large, return it modulo 10^9 + 7.

Time : O(n^2)
Space : O(n^2)    
*/

class Solution {
    // This one is N^2 takes  599 ms 
    public int threeSumMulti(int[] A, int target) {
        HashMap<Integer, Integer> count = new HashMap<>();
        int n = A.length;
        long result = 0;
        
        for(int i = 0; i + 1 < n; i++){
            for(int j = i + 1; j < n; j++){
                if(count.containsKey(target - A[i] - A[j])){
                    result += ( 1L * count.get(target - A[i] - A[j]) ); 
                    //System.out.println(result);
                }
            }
            count.put(A[i], count.getOrDefault(A[i], 0) + 1);
        }
        
        // System.out.println(result);
        return (int) (result%(1000000000 + 7));
    }
}

/*
https://leetcode.com/problems/3sum-smaller/
Given an integer array A, and an integer target, return the number of tuples i, j, k  
such that i < j < k and A[i] + A[j] + A[k] < target.
Complexity analysis
Time complexity : O(n^2). 
The twoSumSmaller function takes O(n) time because both left and right traverse at most n steps. 
Therefore, the overall time complexity is O(n^2).
Space complexity : O(1).

nums = [1, 2, 3, 5, 8], and target = 7.

[1,  2,   3, 5,  8]
 i   left       right 
 target= 7-a[i] = 6 

*/

    public int threeSumSmaller(int[] nums, int target) { 
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            sum += twoSumSmaller(nums, i + 1, target - nums[i]); 
        }
        return sum; 
    }

    private int twoSumSmaller(int[] nums, int startIndex, int target) { 
        int sum = 0;
        int left = startIndex;
        int right = nums.length - 1; 
        while (left < right) {
            if (nums[left] + nums[right] < target) { 
                sum += right - left;
                left++;
            } else {
                right--;
            } 
        }
        return sum; 
    }
