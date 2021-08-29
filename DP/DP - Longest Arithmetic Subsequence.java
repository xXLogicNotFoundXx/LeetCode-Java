/*
https://leetcode.com/problems/longest-arithmetic-subsequence/

Input: A = [9,4,7,2,10]
Output: 3
The longest arithmetic subsequence is [4,7,10].  ... diff is 3 

Input: A = [20,1,15,3,10,5,8]
Output: 4
The longest arithmetic subsequence is [20,15,10,5].   ... diff is 5

    Simply they are asking with same "K" difference find longes increasing subsequence
    like 2,4,6,8. OR 3,6,9 or 4,7,10 etc 
    and K here could be anything you have to figure that out.
    
    Almost like Longest Increasing subsequence ... but we dont know the diff .. so 
    we save HashMap object in each dp[]
    
*/
class Solution {
    public int longestArithSeqLength(int[] nums) {
        if(nums==null || nums.length==0)
            return 0;
        // Look there is no <> while declair the array new HashMap[nums.length]
        HashMap<Integer,Integer> map[] = new HashMap[nums.length];
        
        for(int i=0;i<nums.length;i++)
            map[i] = new HashMap<Integer,Integer>();
        
        int ans=1; // at least one number should be there 
        
        for(int i=0; i<nums.length; i++){
                
            for(int j=i-1; j>=0; j--){
                int diff = nums[j]-nums[i];
                
                int sumj = map[j].getOrDefault(diff, 1); // default 1 bcz we want to consider nums[j]
                int sumi = map[i].getOrDefault(diff, 0);
                int max = Math.max(sumi, sumj+1);
                
                map[i].put(diff, max);
                ans = Math.max(ans, max);
            }
            
        }

        return ans;
    }
}
