
/*
https://leetcode.com/problems/diameter-of-binary-tree/
The diameter of a tree is the number of nodes on the longest path between two leaves in the tree.
Diameter of Binary Tree
          1
         / \
        2   3
       / \     
      4   5    
Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

Note : Diameter path may or may not pass through the root of the tree.
Note : Root element (of left and right tree) does not count thats why it is 3.

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
        
        return Math.max(left,right)+1;
    }
}

/*
Height VS Depth 

The depth of a NODE is the number of edges from the node to the tree's root node.
A root node will have a depth of 0.

The height of a NODE is the number of edges on the longest path from the node to a leaf.
A leaf node will have a height of 0.

https://stackoverflow.com/questions/2603692/what-is-the-difference-between-tree-depth-and-height

https://leetcode.com/problems/maximum-depth-of-binary-tree/submissions/
Retrun max depth of the BT OR Height of the BT
*/
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
        return maxSoFar+1;     
    }
}

// level order 
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        Map<Integer,List<Integer>> map = new HashMap<Integer,List<Integer>>();
        
        traverse(root,0,map);
        for(int i=0;map.containsKey(i);i++)
            ans.add(map.get(i));
            
        return ans;
    }
    // creates a map of <level, nodes>
    public void traverse(TreeNode root, int level, Map<Integer,List<Integer>> map){
        if(root==null) return;
        
        List<Integer> subAns = map.getOrDefault(level,new ArrayList<Integer>());
        subAns.add(root.val);
        map.put(level,subAns);
        traverse(root.left,level+1,map);
        traverse(root.right,level+1,map);
    }
}

//https://leetcode.com/problems/find-bottom-left-tree-value/
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
class SolutionIterative {
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

/*
https://leetcode.com/problems/merge-two-binary-trees/
Given two binary trees and imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped
while the others are not. You need to merge them into a new binary tree. 
The merge rule is that if two nodes overlap, then sum node values up as the new
value of the merged node. Otherwise, the NOT null node will be used as the node of new tree.

Example 1:

Input: 
	Tree 1                     Tree 2                  
          1                         2                             
         / \                       / \                            
        3   2                     1   3                        
       /                           \   \                      
      5                             4   7                  
Output: 
Merged tree:
	     3
	    / \
	   4   5
	  / \   \ 
	 5   4   7
 */ 
class Solution {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t2==null)
            return t1;
        if(t1==null)
            return t2;
        t1.val = t1.val+t2.val;
        t1.left = mergeTrees(t1.left,t2.left);
        t1.right = mergeTrees(t1.right,t2.right);
        return t1;
    }
}

// IsBinarySeacrhTree?
// https://leetcode.com/problems/validate-binary-search-tree/
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
}

class Solution2 {   
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
