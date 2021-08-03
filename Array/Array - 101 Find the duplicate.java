/*
https://leetcode.com/problems/find-the-duplicate-number/
Input: [1,3,4,2,2]
Output: 2
Input: [3,1,3,4,2]
Output: 3
You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
*/
class Solution {
    
    // THIS IF WE ARE ALLOWED TO MODIFY AN ARRAY
    // idea is we jump to index 'nums[i]'
    // and keep jumping while making those numbers -ve  
    // if there is no duplicate jumping will happen for max nums.length 
    // if during jumps we find -ve number the index of that number is duplicate 
    public int findDuplicate1(int[] nums) {
        int jumpCount=0;
        int index1 = nums[0];
        
        while(jumpCount<nums.length){
            
            if(nums[index1]<0)
                return index1;
            
            int index2 = nums[index1];
            nums[index1] =  nums[index1]*-1;
            index1=index2;
            
            jumpCount++;
        }
        return -1;
    }
    
    // this if we are not allowed to modify the array 
    // N.Log(N)
    public int findDuplicate(int[] nums) {
        int low=1, high=nums.length;
        while(low<high){    
            
            int mid=(low+high)/2;
            
            int count=0;
            for(int i=0; i<nums.length; i++){
                if(nums[i]<=mid){
                    count++;
                }                                
            }
            
            if(count<=mid)
                low = mid+1;
            else 
                high = mid;                                    
        }
        
        return high;
    }
}
