/*
https://leetcode.com/problems/sort-colors/
Amazon12 Microsoft10 Adobe6 Facebook5 Apple5 Salesforce3 Bloomberg2

Given an array nums with n objects colored red, white, or blue, sort them in-place
so that objects of the same color are adjacent, with the colors in the order red, white, and blue.

We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.

You must solve this problem without using the library's sort function.
*/
class Solution {
    public void sortColors1(int[] nums) {
        if(nums==null||nums.length==0)
            return;

        int zeroCount=0,twoCount=0;
        for(int i=0;i<nums.length;i++){
            zeroCount += nums[i]==0 ? 1 : 0;
            twoCount  += nums[i]==2 ? 1 : 0;
            nums[i]=1;              // nice
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

    public void sortColors(int[] nums) {

        if(nums==null || nums.length<=1)
            return;

        int left=0, right=nums.length-1;

        for(int i=0; i<=right; i++){ // <= right beacuse there could be no 2's .

            if(nums[i]==1)
              continue;

            if (nums[i]==0){
                nums[i] = nums[left];
                nums[left] = 0;
                left++;
                continue;  // num[left] always will be pointing to 1  .. we got the one in the middle so we dont have to worry about being two
            }

            if(nums[i]==2){
                nums[i] = nums[right];
                nums[right] = 2;
                right--;
                i--; // we might have switched to another 2 .. we want to stay at the same position for next iterations
                // [2,1,2]
            }

            // i increments for everything .. we want to avoid i incrementing when we move right number.
            // why
            // well for 0 we are moving left and i together ...
            //    bcz we are ignoring one and left is alwasy pointing to 1 ..
            // for 1 we are ignoring it and moving only i++ ...
            // for 3 we are not moving right but not i bcz of .. i could get another 2 .. we might have switched it to two.
        }
    }
}
