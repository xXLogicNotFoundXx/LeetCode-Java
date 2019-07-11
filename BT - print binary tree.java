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
        List<List<String>> res = new LinkedList<>();

        int totalRows = root == null ? 1 : getHeight(root);
        int totalcolumns = (int) (Math.pow(2, totalRows) - 1);

        List<String> row = new ArrayList<>();
        for(int i = 0; i < totalcolumns; i++)  
          row.add("");
        for(int i = 0; i < totalRows; i++)  
          res.add(new ArrayList<>(row));

        populateRes(root, res, 0, totalRows, 0, totalcolumns - 1);
        return res;
    }

    public void populateRes(TreeNode root, List<List<String>> res, int row, int totalRows, int i, int j) {
        if (row == totalRows || root == null) 
            return;

        res.get(row).set((i+j)/2, Integer.toString(root.val));

        populateRes(root.left, res, row+1, totalRows, i, (i+j)/2 - 1);
        populateRes(root.right, res, row+1, totalRows, (i+j)/2+1, j);
    }

    public int getHeight(TreeNode root) {
         if (root == null) return 0;
         return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }
}
