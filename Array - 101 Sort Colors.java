
/*
https://leetcode.com/problems/sort-colors/
Sort an array in 1 pass with values 0,1,2 only 
Input: [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
*/
class Solution {
    // 2 pass 
    public void sortColors1(int[] nums) {
        if(nums==null||nums.length==0)
            return; 
        
        int zeroCount=0,twoCount=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]==0)
                zeroCount++;
            if(nums[i]==2)
                twoCount++;
            nums[i]=1;
        }
        
        int i=0;
        while(zeroCount>0){
            nums[i++]=0;
            zeroCount--;
        }
        
        i=nums.length-1;
        while(twoCount>0){
            nums[i--]=2;
            twoCount--;
        }      
    }
    
    // 1 pass - 3 pointers 
    // idea is to 
    // move left++  after swap with current when we find 0 on current 
    // move right-- after swap with current when we find 2 on current 
    // we move current++ if we find 1 on current
    // current always move forward - except it stays put when we find 2 on current
    // this is because we may be swapping [0 or 1 or 2] from right pointer 
    // and we have to make decision on this number again so current should not move 
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
                i--;
            }
        }
    }
}
