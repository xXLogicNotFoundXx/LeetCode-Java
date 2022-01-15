/*
Medium - IMP
Facebook35 Microsoft5 Amazon5 ......

https://leetcode.com/problems/clone-graph/

Given a reference of a node in a connected undirected graph, return a deep copy (clone) of the graph.
Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.
1--2
|  |
4--3
Node 1 has two neighbors: Node 2 and 4.           class Node {
Node 2  has two neighbors: Node 1 and 3.            public int val;
Node 3 has two neighbors: Node 2 and 4.             public List<Node> neighbors;
Node 3 has two neighbors: Node 2 and 4              public Node(int _val, List<Node> _neighbors) {
Node 4 has two neighbors: Node 1 and 3.               val = _val;
                                                      neighbors = _neighbors;
                                                    }
                                                    public Node(int _val) {
                                                        val = _val;
                                                        neighbors = new ArrayList<Node>();
                                                    }
                                                  }
*/

class Solution {
    /*
        2Pass - 25ms
        O(E+V)
        Do we need the second loop though?
    */
    public Node cloneGraph1(Node node) {
        Map<Node,Node> map = new HashMap<>();

        traverse(node, map);
        for(Map.Entry<Node,Node> e: map.entrySet()){
            Node root = e.getKey();
            Node newRoot = e.getValue();

            for(Node child : root.neighbors){
                newRoot.neighbors.add(map.get(child));
            }
        }

        return map.get(node);
    }

    void traverse(Node root, Map<Node,Node> map){
        if(root==null || map.containsKey(root))
            return;

        Node newRoot = new Node(root.val);
        map.put(root,newRoot);

        for(Node child : root.neighbors){
            traverse(child, map);
        }
    }

    // 1 Pass we dont have to iterate over map.entries
    public Node cloneGraph(Node node) {
        Map<Node,Node> map = new HashMap<>();
        Node newRoot = traverse2(node,map);

        return newRoot;
    }

    Node traverse2(Node root, Map<Node,Node> map){
        if(root==null)
            return null; // should not happen

        if(map.containsKey(root))
            return map.get(root);

        Node newRoot = new Node(root.val);
        map.put(root,newRoot);

        for(Node child : root.neighbors){
            newRoot.neighbors.add(traverse2(child, map));
        }

        return newRoot;
    }
}
