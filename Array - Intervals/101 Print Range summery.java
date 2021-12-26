/*
Easy
https://leetcode.com/problems/summary-ranges/
Input: nums = [0,1,2,4,5,7]
Output: ["0->2","4->5","7"]
*/
public class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> summary = new ArrayList<>();

        int i=0;
        for (int j = 0; j < nums.length; j++){

            i=j;

            // try to extend the range of j [nums[i], nums[j]]
            while (j+1 < nums.length && nums[j] + 1 == nums[j+1] )
                j++;

            // put the range into the list
            if (i == j)
                summary.add(nums[i] + "");
            else
                summary.add(nums[i] + "->" + nums[j]);


        }
        return summary;
    }
}
