/*
https://leetcode.com/problems/clone-graph/
Given a reference of a node in a connected undirected graph, return a deep copy (clone) of the graph. 
Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.
1--2
|  |
3--4
Node 1 has two neighbors: Node 2 and 4.           class Node {
Node 2  has two neighbors: Node 1 and 3.            public int val;
Node 3 has two neighbors: Node 2 and 4.             public List<Node> neighbors;
Node 3 has two neighbors: Node 2 and 4              public Node(int _val,List<Node> _neighbors) {
Node 4 has two neighbors: Node 1 and 3.               val = _val;
                                                      neighbors = _neighbors;
                                                    }
                                                  }
*/
class Solution {
    public Node cloneGraph(Node node) {
        if(node == null) return null;
        Map<Integer,Node> map = new HashMap<>();
        cloneGraphHelper(node,map);
        return map.get(node.val);
    }
    
    void cloneGraphHelper(Node n, Map<Integer,Node> map) {
        if(n==null) return; 
        if(map.containsKey(n.val)) return; 
        
        Node n1 = new Node(n.val, new ArrayList<Node>());
        map.put(n.val,n1);
        for(Node ch : n.neighbors){
            cloneGraphHelper(ch,map);
            n1.neighbors.add(map.get(ch.val));
        }
    }
}
