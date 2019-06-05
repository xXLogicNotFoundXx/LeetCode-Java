/*
https://leetcode.com/problems/construct-quad-tree/
427. Construct Quad Tree 
// Definition for a QuadTree node.
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;

    public Node() {}

    public Node(boolean _val,boolean _isLeaf,Node _topLeft,Node _topRight,Node _bottomLeft,Node _bottomRight) {
        val = _val;
        isLeaf = _isLeaf;
        topLeft = _topLeft;
        topRight = _topRight;
        bottomLeft = _bottomLeft;
        bottomRight = _bottomRight;
    }
};
*/
class Solution {
    public Node construct(int[][] grid) {
        if(grid==null || grid.length==0 || grid.length!=grid[0].length)
            return null;
        
        return buildTree(grid,0,0,grid.length-1,grid[0].length-1);
    }
    
    Node buildTree(int[][]grid, int r, int c, int mr, int mc){
        if(r>mr||c>mc)
            return null;
        boolean leaf = true;
        int val = grid[r][c];
        for(int i=r;i<=mr;i++){
            for(int j=c;j<=mc;j++){
                if(grid[i][j]!=val){
                    leaf = false;
                    break;
                }
            }
        }
        
        if(leaf==true){
            return new Node(val == 1,leaf,null,null,null,null);
        }
        
        Node root =  new Node(false,false,null,null,null,null);
        int rmid = (r + mr)/2;
        int cmid = (c + mc)/2;
        
        root.topLeft = buildTree(grid,r,c,rmid,cmid);
        root.topRight = buildTree(grid,r,cmid+1,rmid,mc);
        root.bottomLeft = buildTree(grid,rmid+1,c,mr,cmid);
        root.bottomRight = buildTree(grid,rmid+1,cmid+1,mr,mc);
        
        return root;
    }
}
