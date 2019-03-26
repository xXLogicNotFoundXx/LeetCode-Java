https://leetcode.com/problems/maximum-depth-of-binary-tree/submissions/
Retrun max depth of the BT 
class Solution {
    public int maxDepth(TreeNode root) {
        if(root==null) return 0;
        return 1+ Math.max(maxDepth(root.left),maxDepth(root.right));
    }
}

// MAX depth of n-ary tree 
class Solution {
    public int maxDepth(Node root) {
        if(root==null)
            return 0;
        int maxSoFar = 0;
        for(Node n: root.children){
            int depth = maxDepth(n);
            if(maxSoFar<depth)
                maxSoFar = depth; 
        }
        return maxSoFar+1;     // forgot to add 1 here 
    }
}

/**
Sum Root to Leaf Numbers
https://leetcode.com/problems/sum-root-to-leaf-numbers/
Input: [1,2,3]
    1
   / \
  2   3
Output: 25
Explanation:
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.
Therefore, sum = 12 + 13 = 25.
 */
class Solution {
    int totalSum =0;
    public int sumNumbers(TreeNode root) {
        sumNumbersHelper(root,0);
        return totalSum;
    }
    void sumNumbersHelper(TreeNode root, int leafSum) {
        if(root==null)
            return;
        
        leafSum+= root.val;
        if(root.right==null && root.left ==null)
            totalSum+= leafSum;
        
        sumNumbersHelper(root.left,  leafSum*10);
        sumNumbersHelper(root.right, leafSum*10);
    }
}

/**
https://leetcode.com/problems/subtree-of-another-tree/
Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. 
A subtree of s is a tree consists of a node in s and all of this node's descendants. 
The tree s could also be considered as a subtree of itself.
 */
class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        List<TreeNode> sub = new ArrayList();
        findNode(s,t,sub);
        for(TreeNode root : sub){
            if(isSame(root,t))
                return true;
        }
        return false;
    }
    
    boolean isSame(TreeNode s, TreeNode t){
        if(s==null && t==null)
            return true;
        if(s==null || t == null)
            return false;
        if(s.val==t.val){
            return isSame(s.left,t.left) && isSame(s.right,t.right) ;
        }
        return false;
    }
    
    void findNode(TreeNode s, TreeNode t, List<TreeNode> sub ) {
        if(s==null)
            return;
        if(s.val == t.val)
            sub.add(s);
        
        findNode(s.left,t,sub);
        findNode(s.right,t,sub);
    }
}
