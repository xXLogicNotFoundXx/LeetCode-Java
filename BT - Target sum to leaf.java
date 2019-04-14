https://leetcode.com/problems/path-sum-ii/
Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
Note: A leaf is a node with no children.
Example:
Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \    / \
7    2  5   1
Return:

[
   [5,4,11,2],
   [5,8,4,5]
]


class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> subAns = new ArrayList<Integer>();
        pathSum(root,sum,ans,subAns);
        return ans;
    }
    
    void  pathSum(TreeNode root, int sum, List<List<Integer>> ans, List<Integer> subAns){
        if(root==null) return;
        subAns.add(root.val);
        if((root.left==null && root.right==null) && 0==sum-root.val ){
            ans.add(new ArrayList<Integer>(subAns));
        } else{
            pathSum(root.left,sum-root.val,ans,subAns);
            pathSum(root.right,sum-root.val,ans,subAns);
        }
        subAns.remove(subAns.size()-1);
    }
}

