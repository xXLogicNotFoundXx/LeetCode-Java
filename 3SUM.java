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
https://leetcode.com/problems/3sum-with-multiplicity/
Given an integer array A, and an integer target, return the number of tuples i, j, k  
such that i < j < k and A[i] + A[j] + A[k] == target.
As the answer can be very large, return it modulo 10^9 + 7.
Time : O(n^2)
Space : O(n^2)
To calculate combinations, we will use the formula C(n,r)   nCr = n!/(r!*(n-r)!), Also noted as C(n,r).
        
*/
class Solution {
    // This one is N^2 takes  599 ms 
    public int threeSumMulti(int[] A, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        
        int res = 0;
        for (int i = 0; i < A.length; i++) {
            // A[i] must be considered for result before we calculate all combination sum and we dont 
            // want to consider that sum bcz that includesA[i]
            res = (res + map.getOrDefault(target - A[i], 0)) % 1000000007;
            for (int j = 0; j < i; j++) {
                int temp = A[i] + A[j];
                map.put(temp, map.getOrDefault(temp, 0) + 1);
            }
        }
        return res;
    }
}
