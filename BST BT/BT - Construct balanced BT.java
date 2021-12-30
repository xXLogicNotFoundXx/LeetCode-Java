/*
Easy - good enough 
https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
*/
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums == null || nums.length==0) return null;
        return createBalancedBinary(nums,0,nums.length-1);
    }

    TreeNode createBalancedBinary(int[] nums,int i,int j){
        if(i > j)
            return null;

        int middle = (i+j)/2;
        TreeNode head = new TreeNode(nums[middle]);
        head.left  = createBalancedBinary(nums,i,middle-1);
        head.right = createBalancedBinary(nums,middle+1,j);
        return head;
    }
}
