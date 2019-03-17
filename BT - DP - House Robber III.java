/*
https://leetcode.com/problems/house-robber-iii/
Rober cannot rob two directly-linked houses. Alternate level wise.
Determine the maximum amount of money the thief can rob tonight without alerting the police.
Example 1:
     3
    / \
   2   3
    \   \ 
     3   1

Output: 7 
Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
*/
// Recursive : Without map will visits every problem number of times bcz rob gets called 6 times 6^N
// 800+ mili seconds 
// we can store the results in map 
// O(N) space O(N) runtime - 4ms
class Solution {
    Map<TreeNode,Integer> map = new HashMap();
    
    public int rob(TreeNode root) {    
        if(root==null)   return 0;
        if(map.containsKey(root))  
            return map.get(root);
        
        int val =0;
        if(root.left!=null){
            val +=  rob(root.left.left) + rob(root.left.right);
        }
        if(root.right!=null){
            val +=  rob(root.right.left) + rob(root.right.right);
        }
        
        int max = Math.max(root.val+val, rob(root.left) + rob(root.right));
        map.put(root,max);
        
        return max;
    }
}
