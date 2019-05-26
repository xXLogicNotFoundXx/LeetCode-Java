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


/*
https://leetcode.com/problems/serialize-and-deserialize-n-ary-tree/
Serialize and Deserialize N-ary Tree
class Node {
    public int val;
    public List<Node> children;
    public Node() {}
    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/
class Codec {

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        StringBuffer sb = new StringBuffer();
        if(root==null) 
            return "";
        serialize(root,sb);
        return sb.toString();
    }
    void serialize(Node root,StringBuffer sb){
        sb.append(root.val);
        sb.append(",");
        sb.append(root.children.size());
        for(int i=0;i<root.children.size();i++){
            sb.append(",");
            serialize(root.children.get(i),sb);
        }
    }
    
    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if(data.isEmpty()) 
            return null;
        
        String[] ss = data.split(",");
        Queue<String> q = new LinkedList<String>();
        q.addAll(Arrays.asList(ss));
        return deserialize(q);
    }
    
    Node deserialize(Queue<String> q){
        int val = Integer.valueOf(q.poll());
        int size = Integer.valueOf(q.poll());
        Node root = new Node(val);
        root.children = new ArrayList<Node>();
        for(int i=0;i<size;i++){
            root.children.add(deserialize(q));
        }
        return root;
    }
}
