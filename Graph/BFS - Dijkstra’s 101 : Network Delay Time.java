/*
Medium - 
Microsoft3 Amazon2 Google2
743. Network Delay Time
https://leetcode.com/problems/network-delay-time/


There are N network nodes, labelled 1 to N.
Given times, a list of travel times as directed edges times[i] = (u, v, w),
where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.
Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.
Input: times = [[2,1,1],[2,3,1],[3,4,1]], N = 4, K = 2
Output: 2

Dijkstra's original algorithm found the shortest path between two given nodes.
but a more common variant fixes a single node as the "source" node and finds shortest paths from the source to all other nodes in the graph, producing a shortest-path tree.

Time Complexity: O(E log E) E is number of edges (times.length).  as potentially every edge gets added to the heap.
Space Complexity: O(N+E)  the size of the graph (O(E)), plus the size of the other objects used (O(N)).
*/
class Solution {

    class Node {
        int des;
        int wgt;
        Node(int d, int w){
            des = d;
            wgt = w;
        }
    }

    public int networkDelayTime(int[][] times, int n, int k) {

        if(n<0 || times==null) return -1;
        if(k<1 || k>n) return -1;

        Map<Integer, List<Node>> adjMap = new HashMap<>();
        for(int i=1; i<=n; i++)
            adjMap.put(i, new ArrayList<Node>());

        for(int i=0;i<times.length;i++)
            adjMap.get(times[i][0]).add( new Node(times[i][1], times[i][2]) );

        Set<Integer> visited = new HashSet<>();
        PriorityQueue<Node> pq = new PriorityQueue<Node>((a,b)-> a.wgt - b.wgt);
        /*
        PriorityQueue<NextNode> pq = new  PriorityQueue<NextNode>(new Comparator<NextNode>(){
            public int compare(NextNode a, NextNode b){
                return a.weight - b.weight;
            }
        });
        */
        pq.add(new Node(k,0));
        int maxWeight =0;

        while(!pq.isEmpty()){
            Node node = pq.poll();

            if(visited.contains(node.des))
                continue;

            visited.add(node.des);

            maxWeight = Math.max(maxWeight, node.wgt);
            for(Node next: adjMap.get(node.des)){
                next.wgt += node.wgt;

                if(!visited.contains(next.des)) // improves the performace..
                    pq.add(next);
            }

            if(visited.size() == n)
                break;
        }

        return visited.size() == n ? maxWeight : -1;
    }
}
