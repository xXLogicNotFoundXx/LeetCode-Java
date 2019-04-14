/*
https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
Serialize and Deserialize Binary Tree

    1
   / \
  2   3
     / \
    4   5

We gonna do preorder traversal and with help of queue we well genrate BT.
*/

public class Codec {
    final String splitter = ",";
    final String nul = "N";
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuffer str = new StringBuffer();
        decode(str,root);
        // System.out.print(str); => 1,2,N,N,3,4,N,N,5,N,N,
        return str.toString();   
    }
    
    void decode(StringBuffer str,TreeNode root){
        if(root==null){
            str.append(nul).append(splitter);
            return;
        }
        str.append(root.val).append(splitter);
        decode(str,root.left);
        decode(str,root.right);
    }
    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<String> queue = new LinkedList<>();
        queue.addAll(Arrays.asList(data.split(splitter)));      // Arrays.asList(String[])
        return buildTree(queue);
    }
    
    TreeNode buildTree(Queue<String> queue){
        String str = queue.poll();
        if(str.equals(nul))
            return null;
        TreeNode node = new TreeNode(Integer.valueOf(str));    // Integer.valueOf(str)
        node.left = buildTree(queue);
        node.right = buildTree(queue);
        return node;
    }
}
