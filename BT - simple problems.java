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


// https://leetcode.com/problems/cousins-in-binary-tree/
// Two nodes of a binary tree are cousins if they have the same depth, but have different parents.
class Solution {
    class Pair{
      TreeNode parent;
      int height;
      Pair(TreeNode parent, int height){
        this.parent=parent;
        this.height=height;
      }
    }
    
    public boolean isCousins(TreeNode root, int x, int y) {
        Pair num1 = find(root,null,x,0);
        Pair num2 = find(root,null,y,0);
        
        if( num1 == null || num2 == null) // couldnt find x OR y
            return false;
        
        return num1.parent != num2.parent && num1.height == num2.height;
    }
    
    Pair find(TreeNode node, TreeNode par, int num, int height){
        if(node==null) return null;
        
        if(node.val == num)
            return new Pair(par,height);
        
        Pair res1= find(node.left, node, num, height+1);
        Pair res2= find(node.right, node, num, height+1);
        
        return res1!=null ? res1 : res2;
    }
}
