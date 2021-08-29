/*
https://leetcode.com/problems/longest-increasing-subsequence/
Input: [10,9,2,5,3,7,101,18]
Output: 4 

The longest increasing subsequence is [2,3,7,101], OR [2,5,7,101] therefore the length is 4. 
// O(N^2)


[10,9,2,5,3,7,101,18]
[2, 2,4,3,3,2,1  ,1]
*/

class Solution {
    public int lengthOfLIS(int[] nums) {
        if(nums==null || nums.length==0)
            return 0;
        
        int dp[] = new int[nums.length];
        
        Arrays.fill(dp,1);
        int ans=0;
        
        for(int i=0; i<nums.length; i++){
            
            for(int j=i-1; j>=0; j--){
                
                if(nums[j]<nums[i]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            ans = Math.max(ans,dp[i]);
        }
        
        return ans;
    }
}

/* nlogK  K is the ans.
Solution 2 - Binary Search
We can put the increasing sequence in a TreeSet. 

For normal Binary search logic is :
    for each num in nums
         if(list.size()==0)
              add num to list
         else if(num > last element in list)
              add num to list
         else 
              replace the element in the list which is the smallest but bigger than num

For TreeSet logic is:
    If set.ceiling() -> doesnt return null then replace that number with the current number 
    else just add the number 
    
*/
// O(NxLogN)
public int lengthOfLIS(int[] nums) {
    if(nums==null || nums.length==0)
        return 0;

    TreeSet<Integer> set = new TreeSet<>();
    // provides guaranteed log(n) time cost for the basic operations (add, remove and contains).

    for(int i=0; i<nums.length; i++){

        Integer n = set.ceiling(nums[i]);
        if(n!=null)
            set.remove(n);

        set.add(nums[i]);
    }

    return set.size();
}

// normal binary search 
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
