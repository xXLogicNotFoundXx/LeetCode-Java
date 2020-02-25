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
This is interesting problem where you are supposed to return how many tuples are there a + b + c = target.
Array has duplicate elements return the total number of tuples including DUPLICATES.

NOTE : 
To calculate combinations, we will use the formula C(n,r)   nCr = n! / (r! * (n - r)!), Also noted as C(n,r).
where n represents the total number of items, and r represents the number of items being chosen at a time.
*/ 
public int threeSumMulti(int[] A, int target) {
    // This one is worst N^2  but takes  60ms 
    Arrays.sort(A);
    int m = 1000000007;
    int res = 0;
    for (int i = 0; i < A.length - 2; ++i) {
        int j = i + 1;
        int k = A.length - 1;
        while (j < k) {
            if (A[j] + A[k] < target - A[i]) { ++j; }
            else if (A[j] + A[k] > target - A[i]) { --k; }
            else {
                if (A[j] == A[k]) {
                    // If A[j...k] are all equal, then there are combinations of C(k - j + 1, 2) 
                    // combinations that meet the requirement.
                    // let this sink in so we did n*n-1/2   ... n-2! is cancelled out look at the formula.
                    res = (res + (k - j + 1) * (k - j) / 2) % m; 
                    break;
                }
                int l = 1, r = 1;
                // count the number that are same as A[j] and A[k]
                while (j + l < k && A[j + l] == A[j]) 
                    l++;  // l: number of elements equal to A[j].
                while (j < k - r && A[k - r] == A[k]) 
                    r++; // r: number of elements equal to A[k].

                res = (res + l * r) % m; // found l * r cases that meet the requirement.
                j += l; // forward j by l steps.
                k -= r; // backward k by r steps.
            }
        }
    }
    return res;
}
