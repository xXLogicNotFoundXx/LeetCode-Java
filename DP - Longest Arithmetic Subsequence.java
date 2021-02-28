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
    public int longestArithSeqLength(int[] A) {
        
        if(A==null || A.length==0) return 0;
        if(A.length==1) return 1;
        
        HashMap<Integer,Integer>[] mapDP = new HashMap[A.length];
        int n = A.length-1;
        for(int i=0; i<=n; i++)
            mapDP[i] = new HashMap<>();
        
        int ans=0;
        for(int i=n-1; i>=0; i--){
           
            for(int j=i+1; j<=n; j++){
                int diff = A[i] - A[j]; 
                
                int countj = mapDP[j].getOrDefault(diff,1);  // if diff not there, then we have to consider j as a number
                int counti = mapDP[i].getOrDefault(diff,0);  
                
                mapDP[i].put(diff,Math.max(counti,countj+1));
                
                ans = Math.max(ans, mapDP[i].get(diff));
            }
            
        }
        
        return ans;
    }
}
