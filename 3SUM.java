/*
https://leetcode.com/problems/3sum/
Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? 
Find all unique triplets in the array which gives the sum of zero.
The solution set must not contain duplicate triplets.
*/
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        if(nums==null) return ans;
        Arrays.sort(nums);
        for(int i=0;i<nums.length-2;i++){
            while(i!=0 && i<nums.length-2 && nums[i] == nums[i-1])
                i++;
            
            int left=i+1,right=nums.length-1;
            while(left<right){
                int sum = nums[i] + nums[left] + nums[right];
                if(sum==0){
                    List<Integer> subAns =  new ArrayList<>();
                    subAns.add(nums[i]);
                    subAns.add(nums[left]);
                    subAns.add(nums[right]);
                    ans.add(subAns);
                    left++;
                    while(left<right && nums[left] == nums[left-1]) 
                        left++;
                    
                }else if(sum<0)
                    left++;
                else 
                    right--;
            }
            
        }
        return ans;
    }
}

/*
3 SUM closest 
https://leetcode.com/problems/3sum-closest/
Given array nums = [-1, 2, 1, -4], and target = 1.
The sum that is closest to the target is 2. 
(-1 + 2 + 1 = 2).

*/

class Solution {
     public int threeSumClosest(int[] nums, int target) {
        if(nums==null) return 0;
        Arrays.sort(nums);
        int diff = Integer.MAX_VALUE;
        int ansSum = 0;
        for(int i=0;i<nums.length-2;i++){
            while(i!=0 && i<nums.length-2 && nums[i] == nums[i-1])
                i++;
            
            int left=i+1,right=nums.length-1;
            while(left<right){
                int sum = nums[i] + nums[left] + nums[right];
                
                if(diff > Math.abs(sum-target)){
                    diff = Math.abs(sum-target);
                    ansSum = sum;
                } 
                
                if(sum==target){
                    return sum;
                }else if(sum<target)
                    left++;
                else 
                    right--;
            }  
        }
        return ansSum;
    }
}

/*
This is interesting problem where you are supposed to return how many triplets are there a + b + c = target.
Array has duplicate elements return the total number of triplets including DUPLICATES.

NOTE : 
To calculate combinations, we will use the formula C(n,r)   nCr = n! / (r! * (n - r)!), Also noted as C(n,r).
where n represents the total number of items, and r represents the number of items being chosen at a time.
*/ 
public int threeSumCombination(int[] A, int target) {
    // This one is worst N^2  but takes  60ms 
    int mod = 1000000007;
    int ans = 0;
    Arrays.sort(A);
    for(int i = 0; i < A.length-2; i++) {
        long cnt = 0;
        int l = i+1, r = A.length-1;
        while(l < r) {
            if(A[i] + A[l] + A[r] > target) 
                r--;
            else if(A[i] + A[l] + A[r] < target) 
                l++;
            else {
                if(A[l] != A[r]) {
                    long cntL = 1, cntR = 1;
                    while(l+1 < r && A[l] == A[l+1]) { 
                        cntL++; l++; 
                    }
                    while(l < r-1 && A[r] == A[r-1]) { 
                        cntR++; r--; 
                    }
                    cnt += (cntL * cntR) % mod;
                    l++;
                    r--;
                } else {
                    long n = r-l+1;
                    // If A[l...r] are all equal, then there are combinations of C(n, 2) combinations that meet the requirement.
                    // let this sink in so we did n*n-1/2   ... n-2! is cancelled out look at the formula.
                    // nCr = n!/(r!*(n-r)!) 
                    cnt += (n * (n-1) / 2) % mod;
                    break;
                }
            }
        }
        ans = (int) (ans + cnt) % mod;
    }
    return ans;
}
