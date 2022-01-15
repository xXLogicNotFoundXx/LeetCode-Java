/*
Medium -
Palantir Technologies7 Amazon4 Facebook3

https://leetcode.com/problems/number-of-operations-to-make-network-connected/

1319. Number of Operations to Make Network Connected

There are n computers numbered from 0 to n - 1 connected by ethernet cables connections forming a network where connections[i] = [ai, bi]
You are given an initial computer network connections. You can extract certain cables between two directly connected computers,
and place them between any pair of disconnected computers to make them directly connected.

Return the minimum number of times you need to do this in order to make all the computers connected. If it is not possible, return -1.
*/

class Solution {
    public int makeConnected(int n, int[][] connections) {

        int[] parent = new int[n];

        // if you dont realize this then .. it gets messy
        // i ended up coding to record degree for each node and see if we have extra edge available.
        // that added more complexity
        if(connections.length < n-1)         // very smart .. it tells enough edges alerady to pull and move around.
            return -1;

        for(int i=0;i<n; i++)
            parent[i] = i;


        for(int i=0;i<connections.length; i++){
            int p1 = find(parent, connections[i][0]);
            int p2 = find(parent, connections[i][1]);
            parent[p1] = p2;
        }

        int ans =0;
        for(int i=0;i<n; i++){
            if(parent[i]==i)
              ans++;
        }

        return ans-1;
    }

    int find(int[] parent, int node){
        if(parent[node] == node)
            return node;

        parent[node] = find(parent, parent[node]);
        return parent[node];
    }
}
