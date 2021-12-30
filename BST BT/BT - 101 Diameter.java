/*
Easy - IMP
Facebook61 Amazon8 Google4 Bloomberg4 Apple3

https://leetcode.com/problems/diameter-of-binary-tree/
Diameter of Binary Tree
          1
         / \
        2   3
       / \
      4   5
Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

Note : Root element does not count
Note : Diameter path may or may not pass through the root of the tree.
*/
class Solution {
    int ans;
    public int diameterOfBinaryTree(TreeNode root) {
        if(root==null) return 0;
        maxHeight(root);
        return ans;
    }

    int maxHeight(TreeNode root){
        if(root==null) return 0;

        int left = maxHeight(root.left);
        int right = maxHeight(root.right);

        ans = Math.max(ans,left+right);

        return Math.max(left,right) + 1;
    }
}

/*
 Medium - Facebook8
 https://leetcode.com/problems/diameter-of-n-ary-tree/
 N-ary tree
 */
class Solution {
    int res = 0;
    public int diameter(Node root) {
        getHeight(root);
        return res;
    }

    public int getHeight(Node root) {
        if(root == null)
            return 0;

        int max1 = 0;
        int max2 = 0;

        for(Node child : root.children) {
            int height = getHeight(child);
            if(height > max1) {
                max2 = max1;
                max1 = height;
            }
            else if(height > max2) {
                max2 = height;
            }
        }

        res = Math.max(res, max1+max2);
        return max1+1;
    }
}
