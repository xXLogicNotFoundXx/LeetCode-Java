https://leetcode.com/problems/validate-binary-search-tree/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root,Long.MAX_VALUE,Long.MIN_VALUE);
    }
    public boolean isValidBST(TreeNode root, long max, long min) {
        if(root==null) return true;
        if(root.val<max && root.val>min){
            return isValidBST(root.left,root.val,min) && isValidBST(root.right,max,root.val);
        }
        return false;
    }
    
    public boolean isValidBST2(TreeNode root) {
        List<Integer> numbers = new ArrayList<Integer>();
        isValidBST22(root,numbers);
        for(int i=0;i<numbers.size()-1;i++){
            if(numbers.get(i)>=numbers.get(i+1))
                return false;
        }
        return true;
    }
    void isValidBST22(TreeNode root,List<Integer> numbers){
        if(root==null) return;
        
        isValidBST22(root.left,numbers);
        numbers.add(root.val);
        isValidBST22(root.right,numbers);
    }
}
