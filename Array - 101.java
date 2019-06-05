/*
https://leetcode.com/problems/squares-of-a-sorted-array/
977. Squares of a Sorted Array
Given an array of integers A sorted in non-decreasing order, return an array of the squares of each number, also in sorted non-decreasing order.
Input: [-4,-1,0,3,10]
Output: [0,1,9,16,100]
Input: [-7,-3,2,3,11]
Output: [4,9,9,49,121]
*/
class Solution {
    public int[] sortedSquares(int[] A) {
        if(A==null || A.length ==0)
            return A;
        
        int[] result = new int[A.length];
        int i = 0, j = A.length - 1;
        for (int p = j; p >= 0; p--) {
            if (A[i]*A[i] > A[j]*A[j]) 
                result[p] = A[i] * A[i++];
            else 
                result[p] = A[j] * A[j--];
        }
        return result;
    }
}

/*
https://leetcode.com/problems/sort-colors/
Sort an array in 1 pass with values 0,1,2 only 
Input: [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
*/
class Solution {
    public void sortColors(int[] nums) {
        int left =0, right=nums.length-1;
        for(int i=0;i<=right;i++){
            if(nums[i]==0){
                nums[i] = nums[left];
                nums[left] = 0;
                left++;
            }
            if(nums[i]==2){
                nums[i] = nums[right];
                nums[right] = 2;
                right--;
                i--;            // This is one important bcz switchin 2 with right may give 0 on ith index
            }
        }
    }
}

https://leetcode.com/problems/summary-ranges/
Input:  [0,2,3,4,6,8,9]
Output: ["0","2->4","6","8->9"]
Explanation: 2,3,4 form a continuous range; 8,9 form a continuous range.
    
class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> ans = new ArrayList<>();
        if(nums==null || nums.length==0)
            return ans;
        
        for(int i=0;i<nums.length;){ // IMP no i++
            String str = new String() + nums[i];
            int j=i+1;
            for(;j<nums.length;j++){
                if(nums[j]-1!=nums[j-1])
                    break;
            }
            if(nums[j-1] == nums[i]){
                ans.add(str);
            } else {
                str = str+"->"+nums[j-1];
                ans.add(str);
            }
            i=j;            
        }
        return ans;
    }
}
