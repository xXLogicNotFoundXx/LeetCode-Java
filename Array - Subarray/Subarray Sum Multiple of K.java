/*        

https://leetcode.com/problems/continuous-subarray-sum/
Given a list of NON-NEGATIVE numbers and a target integer k, write a function to check
if the array has a continuous subarray of size at least 2 that sums up to a multiple of k, 
that is, sums up to n*k where n is also an integer.

Input: [23, 2, 4, 6, 7],  k=6
Output: True
Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.

FUCKED UP CORNER CASES - but idea is same as to take mod into consideration 
*/

/*
No doubt the edge cases were the trickiest part of this problem. 
I did not expect to see k being zero or negative or that a factor would be times zero. 
Anyhow, you definitely end up with a few more if statements!
    Some damn it! test cases:
    [0,0], 0 -> true
    [0], 0 -> false
    [0, 0], 100 -> true
    [1,5], -6 -> true
    [1,0], 2 -> false
    [0,1,0], -1 -> true
    [2,2], 4 -> true 
*/
class Solution {
    
    
    // Fucked up corner cases.
    public boolean checkSubarraySum(int[] nums, int k) {
        if(nums==null || nums.length<=1)
            return false;
        
        // [0,0], 0 -> true & [0, 0], 100 -> true
        // Two continuous 0 will form a subarray 
        // which has sum = 0. 0 * k == 0 will always be true.
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == 0 && nums[i + 1] == 0) 
                return true;
        }
        
        // k can't be "0" any longer.
        if (k == 0) 
            return false;
        
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,-1); // -1 bcz to get [2,2], 4 -> true  
        
        int prefixSum =0;
        for(int i=0;i<nums.length;i++){
            
            prefixSum +=nums[i];
            int mod = prefixSum%k;
            
            // all numbers in an array are NON-NEGATIVE 
            // so we dont have to worry about mod being -ve
            
            if(map.containsKey(mod)){
                if(map.get(mod) < i-1) 
                    return true;
            } else{
                map.put(mod,i);
            }
        }
        return false;
    }  
}
