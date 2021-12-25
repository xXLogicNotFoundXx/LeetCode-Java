public class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> summary = new ArrayList<>();

        int i=0;
        for (int j = 0; j < nums.length; ){

            // try to extend the range [nums[i], nums[j]]
            while (j+1 < nums.length && nums[j+1] == nums[j] + 1)
                ++j;

            // put the range into the list
            if (i == j)
                summary.add(nums[i] + "");
            else
                summary.add(nums[i] + "->" + nums[j]);

            j++;
            i = j;
        }
        return summary;
    }
}
