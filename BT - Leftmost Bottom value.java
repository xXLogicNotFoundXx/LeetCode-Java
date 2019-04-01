https://leetcode.com/problems/find-bottom-left-tree-value/

class Solution {
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            root = queue.remove();
            if (root.right != null)
                queue.add(root.right);
            if (root.left != null)
                queue.add(root.left);
        }
        return root.val;
    }
}

class Solution {
    int val = -1;
    int level = 0;
    public int findBottomLeftValue(TreeNode root) {
        findBottomLeftValueHelper(root,0);
        return val;
    }
    public void findBottomLeftValueHelper(TreeNode root, int l){
        if(root==null) return;
        if(l>=level){
            level = l;
            val = root.val;
        }
        findBottomLeftValueHelper(root.right,l+1);
        findBottomLeftValueHelper(root.left,l+1);
    }
}
