/*
https://leetcode.com/problems/critical-connections-in-a-network/

*/
class Solution {
    /*  Subtle thing you have to observe is that if there is a cycle then all nodes and edges involved in it 
        are not part of the critical connection. 
        An edge is a critical connection, if and only if it is not in a cycle.
        
        Also you have to realise that this whole graph is connected. 
        So if you take any node in the system you can traverse the whole graph.
        
        Wrong Initution : 
        add all edges in a set.
        return true if cycle and then backtrackingly remove those edges
        But then we wont know when to stop removing edges. 1-2 then there is cycle connecting to 2......2 
        how owuld you know not to remove 1-2 edge?
        
        So this kind of tells you that we need to have a value associated with each node and that determines when to stop. 
        
        We record the timestamp/depth for each node that we visit.
        if we find visited node during dfs we return that nodes depth. 
        For each node, 
            we check every neighbor and return a smallest timestamp recursively. 
            If returned depth/timestamp is greater than current nodes depth 
                that means there was no cycle and it is critical connection. between node and a parent node. 
            If returned timestamp is less than current node's timestamp, we know that there was a cycle.
                i.e. the neighbor visited previously discovered node.
                and we dont add that edge into critical connection
                Also, we update current nodes depth as our new min depth.
                Why.. bcz recurively we need to reach till that node avoiding eadges to be added.  
    */
    
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        
        HashMap<Integer, List<Integer>> graphMap = buildAGraph(n, connections);
        HashMap<Integer, Integer> depthMap = new HashMap<Integer,Integer>();
        
        
        buildCriticalConnections(graphMap, 0/*depth*/, 0/*node*/, -1/*parent*/, depthMap, ans);
        
        // System.out.println(graphMap);
        // System.out.println(depthMap);
        return ans;
        
    }
    
    int buildCriticalConnections(HashMap<Integer, List<Integer>> graphMap, int depth, int node, int parent, HashMap<Integer, Integer> depthMap, List<List<Integer>> ans){
        
        if(depthMap.containsKey(node))
            return depthMap.get(node);
        
        int currentDepth = depth;
        depthMap.put(node, currentDepth);
        
        int minDepth = Integer.MAX_VALUE;
        // System.out.println(depthMap);
        for(int child : graphMap.get(node)){
            
            if(child==parent)
                continue; 
            
            int childDepth = buildCriticalConnections(graphMap, depth+1, child, node, depthMap, ans);
            minDepth = Math.min(minDepth, childDepth);
        }
        
        if(currentDepth <= minDepth && parent != -1) {  
            // this means even if there was a cycle the cycle ended at node ... not above node ( i.e nodes parents )
            // so connection beetween node and parent is critical
            List<Integer> subAns = new ArrayList<Integer>();
            subAns.add(parent);
            subAns.add(node);
            ans.add(subAns);
        }
        
        return Math.min(minDepth, depthMap.get(node)); // returning smallest depth among childrens
    }
    
    HashMap<Integer, List<Integer>> buildAGraph(int n, List<List<Integer>> connections){
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        
        for(int i=0;i<n;i++)
            map.put(i, new ArrayList<Integer>());
        
        for(List<Integer> conn : connections){
            map.get(conn.get(0)).add(conn.get(1));
            map.get(conn.get(1)).add(conn.get(0));
        }
        
        return map;
    }
    
    /*
        7
        [[0,1],[1,2],[2,3],[3,0],[3,4],[4,5],[5,6],[6,4]]
    
    o/p [[3,4]]
    */
}
