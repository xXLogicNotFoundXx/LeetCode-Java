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
        if(nums ==null || nums.length == 0) 
            return 0;
        
        int n =nums.length;
        int []memo = new int[n];
        int max=0;
    
        for(int i=n-1;i>=0;i--){
            memo[i] = 1;  // Initiate the longest subsequence.. it is 1, if you just include me
            for(int j=i+1;j<n;j++){
                if(nums[i]<nums[j])
                    memo[i] = Math.max(memo[i],memo[j]+1);
            }
            max= Math.max(memo[i],max);
        }   
        return max;
    }
}
/* nlogK  K is the ans.
Solution 2 - Binary Search
We can put the increasing sequence in a list.

for each num in nums
     if(list.size()==0)
          add num to list
     else if(num > last element in list)
          add num to list
     else 
          replace the element in the list which is the smallest but bigger than num
*/          
public int lengthOfLIS(int[] nums) {
    if(nums==null || nums.length==0)
        return 0;
 
    ArrayList<Integer> list = new ArrayList<Integer>(); 
 
    for(int num: nums){
        if(list.size()==0 || num>list.get(list.size()-1)){
            list.add(num);
        }else{
            int i=0; 
            int j=list.size()-1;
 
            while(i<j){
                int mid = (i+j)/2;
                if(list.get(mid) < num){
                    i=mid+1;
                }else{
                    j=mid;
                }
            }
 
            list.set(j, num);
        }
    }
 
    return list.size();
}
