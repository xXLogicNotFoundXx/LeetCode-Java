/*
https://leetcode.com/problems/print-binary-tree/
Input:
     1
    /
   2
Output:
[["", "1", ""],
 ["2", "", ""]]
*/
class Solution {
    public List<List<String>> printTree(TreeNode root) {
        
        int height = treeHeight(root);
        
        List<List<String>> matrix = new ArrayList<List<String>>();
        List<String> row = new ArrayList<>();
        
        int maxCol = (int) Math.pow(2,height)-1; // total number of nodes in fully BT is (2^Height)-1
        
        for(int i=0; i<maxCol; i++)
            row.add("");
        
        for(int i=0; i<height; i++)
            matrix.add(new ArrayList<String>(row));
        
        
        traverse(root, 0/*H*/, 0, maxCol-1, matrix); // -1 because we want it inclusive of the indexes. 
        
        return matrix;
    }
    
    void traverse(TreeNode root, int height, int i, int j, List<List<String>> matrix){
        if(root==null)
            return; 
        
        int mid = (i+j)/2;
        List<String> row = matrix.get(height);
        
        row.set(mid,""+root.val); // int to string ?  there is no new String(int)
        
        traverse(root.left, height+1, i, mid-1,  matrix);
        traverse(root.right, height+1, mid+1, j,  matrix);
    }
    
    int treeHeight(TreeNode root){
        if(root==null)
            return 0;
        
        int Rhgt = treeHeight(root.right);
        int Lhgt = treeHeight(root.left);
        
        return Math.max(Rhgt,Lhgt) + 1;
    }
}
