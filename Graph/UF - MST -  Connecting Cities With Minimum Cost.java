/*
Medium -
Amazon16

1135. Connecting Cities With Minimum Cost
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

    public int minimumCost(int n, int[][] connections) {

        if(n<=0)
            return -1;

        parent = new int[n+1];
        for(int i=0;i<=n; i++)
            parent[i] = i;

        Arrays.sort(connections, (a,b) -> a[2]-b[2]);

        int count=0; // this to count n-1 edges that we gonna add to connect graph
        int weight=0;

        for(int i=0; i<connections.length; i++){
            int[] edge = connections[i];

            if(isDisjointSet(edge[0], edge[1])){
                count++;
                weight+= edge[2];
                union(edge[0],edge[1]);
            }

            if(count==n-1)
                break;
        }
        System.out.println(count+"    "+weight);
        return count==n-1 ? weight : -1;  // if we didnt add n-1 edges then graph is not fully connected tree
    }

    int find(int node){
        if(parent[node]==node)
            return node;

        parent[node] = find(parent[node]);
        return parent[node];
    }

    boolean isDisjointSet(int node1, int node2){
        return find(node1) != find(node2);
    }

    void union(int node1, int node2){
        int parent1 = find(node1);
        int parent2 = find(node2);

        parent[parent1] = parent2;
    }

}
