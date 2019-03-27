/**
https://leetcode.com/problems/closest-binary-search-tree-value/
Given a non-empty binary search tree and a target value, 
find the value in the BST that is closest to the target.
Given target value is a floating point.
target = 3.714286     target = 3.24
Output: 4             Output: 3
    4
   / \
  2   5
 / \
1   3
 */
class Solution {
    public int closestValue(TreeNode root, double target) {
        if(root==null) return -1;
        
        int ans = root.val;
        while(root!=null){
            //Math.abs return absolute value i.e. always positive
            if(Math.abs(target-ans) > Math.abs(target-root.val) ){
                ans = root.val;
            }
            root =   root.val > target ? root.left : root.right;
        }
        return ans;
    }
}
