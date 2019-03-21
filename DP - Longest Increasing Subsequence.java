https://leetcode.com/problems/longest-increasing-subsequence/
Input: [10,9,2,5,3,7,101,18]
Output: 4 
The longest increasing subsequence is [2,3,7,101], OR [2,5,7,101] therefore the length is 4. 
// O(N^2)
// loops start from the back and record the biggest chain possible for i
// j=i+1 if num[j]>num[i] then is this is gonna be biggest chain 
[10,9,2,5,3,7,101,18]
[2, 2,4,3,3,2,1  ,1]

class Solution {
    public int lengthOfLIS(int[] nums) {
        if(nums ==null || nums.length == 0) return 0;
        int n =nums.length;
        int []memo = new int[n];
        int max=0;
        Arrays.fill(memo, 1); 
        for(int i=n-1;i>=0;i--){
            for(int j=i+1;j<n;j++){
                if(nums[i]<nums[j])
                    memo[i] = Math.max(memo[i],memo[j]+1);
            }
            max= Math.max(memo[i],max);
        }   
        return max;
    }
}
