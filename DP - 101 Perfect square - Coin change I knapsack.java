Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
Example 1:
Input: n = 12
Output: 3 
Explanation: 12 = 4 + 4 + 4.
Example 2:
Input: n = 13
Output: 2
Explanation: 13 = 4 + 9.

class Solution {
    // This is exactly same as coin change problem. (We have to build data set (which will be treated as coins)
    public int numSquares(int SUM) {
        if(SUM<=0) return 0;
        
        // Build all perfect square numbers we need to consider 
        int nums[] = new int[(int) Math.sqrt(SUM) +1];
        for(int i=1;i<=nums.length;i++) {
            nums[i-1] = i*i;
            if(nums[i-1] == SUM)
                return 1;
        }
        
        int []solDP= new int[SUM+1];
        solDP[0] = 0;
        for(int target=1;target<=SUM;target++){  
            int min = Integer.MAX_VALUE; 
            for(int num : nums){
                if(target < num)
                    break; // no need process larger numbers 
                min = Math.min(min,solDP[target-num] +1);
            }
            solDP[target] = min;
        }
        return solDP[SUM];
    }
}
