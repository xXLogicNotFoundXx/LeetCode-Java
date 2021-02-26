/*

https://leetcode.com/problems/connecting-cities-with-minimum-cost/

There are N cities numbered from 1 to N.

You are given connections, where each connections[i] = [city1, city2, cost] represents the cost to connect city1 and city2 together. 
(A connection is bidirectional: connecting city1 and city2 is the same as connecting city2 and city1.)

Return the minimum cost so that for every pair of cities, there exists a path of connections (possibly of length 1) that connects those two cities together. 
The cost is the sum of the connection costs used. If the task is impossible, return -1.

1 <= N <= 10000
1 <= connections.length <= 10000
1 <= connections[i][0], connections[i][1] <= N
0 <= connections[i][2] <= 10^5
connections[i][0] != connections[i][1]


Some Background to Share:

Minimum Spanning Tree: In an Bidirectiona(OR undirected) weighted graph, there is a tree (N nodes, N - 1 edges so no circle) 
that connects all nodes in the graph, and the sum of path weights are minimum.

Kruskal's Algorithm: The approach to find the Minimum Spanning Tree in the Graph. 
We sort the edges by weight in increasing order 
Loop sorted edges, pick the edge as long as there are no connectivity already set up between two nodes 
Add this edge weight to the total weight.

Disjoint Set: The data structure used to check the connectivity of graph efficiently in dynamic by union the nodes into one set, and find the number of disconnected sets.

We use Kruskal’s algorithm to generate a minimum spanning tree for the graph. 
Use Union-Find to detect cycle.

Idea is simple:
Sort edges to increasing cost order.
Pick the smallest edge that does not form a cycle.
Repeat until MST is formed and every node is connected.

*/
class Solution {
    
    int[] parent; 
    
    int find(int x){
        if(parent[x]==x)
            return x;
        
        parent[x] = find(parent[x]); // path compression 
        return parent[x];
    }
    
    void union(int x, int y){
        int pX = find(x);
        int pY = find(y);
        
        if( pX != pY){
            // at this point you can connect whichever parent node 
            parent[pX] = pY; // parent[pY] = pX; 
        }
    }
    
    boolean isDisjoint(int x, int y){
        return find(x)!=find(y);
    }
    
    /*
    Time : O(M*Log(M)) - N number of nodes , M number edges 
           M*Log(M) for sort 
           Union Find Operation, amortized O(1)  bcz of path compression
    
    Space: O(N), space required by parents
    */
    public int minimumCost(int N, int[][] connections) {
        parent = new int[N+1];
        int edges = N-1;  // these many edges we need to form a spanning tree for N nodes; 
        
        for(int i=0;i<=N;i++){
            parent[i]=i;
        }
        
        Arrays.sort(connections, (a,b) -> (a[2]-b[2]));
        int cost = 0;
        
        for(int i=0; i<connections.length; i++){
            int x = connections[i][0];
            int y = connections[i][1];
            int cst = connections[i][2];
            
            if(isDisjoint(x,y)){
                union(x,y);
                cost += cst;
                edges--; // we just added an edge 
            }
            
            if(edges==0) // we found all edges to get minimum spanning tree MST
                break;
        }
        
        return edges==0 ? cost: -1; 
    }
}
