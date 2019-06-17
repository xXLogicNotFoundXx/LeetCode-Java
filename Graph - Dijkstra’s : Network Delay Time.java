/*
743. Network Delay Time
https://leetcode.com/problems/network-delay-time/
There are N network nodes, labelled 1 to N.
Given times, a list of travel times as directed edges times[i] = (u, v, w), 
where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.
Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.
Input: times = [[2,1,1],[2,3,1],[3,4,1]], N = 4, K = 2
Output: 2

Time Complexity: O(E log E) E is number of edges (times.length).  as potentially every edge gets added to the heap.
Space Complexity: O(N+E)  the size of the graph (O(E)), plus the size of the other objects used (O(N)).
*/
class Solution {
    class NextNode{
        int des;
        int weight;
        NextNode(int d, int w){
            des =d;
            weight = w;
        }
    }
    
    public int networkDelayTime(int[][] times, int N, int K) {
        
        Map<Integer,List<NextNode>> map = new HashMap<>();
        for(int[] edge : times){
            map.putIfAbsent(edge[0], new ArrayList<NextNode>());
            map.get(edge[0]).add(new NextNode(edge[1],edge[2]));
        }
        
        Map<Integer,Integer> minDis = new HashMap<>();
        PriorityQueue<NextNode> pq = new  PriorityQueue<NextNode>(new Comparator<NextNode>(){
            public int compare(NextNode a, NextNode b){
                return a.weight - b.weight;
            }                                                                          
        });
        
        pq.add(new NextNode(K,0));
        Set<Integer> visited = new HashSet<>();
        while(!pq.isEmpty()){
            NextNode n = pq.poll();
            if(visited.contains(n.des))
                continue;
            
            visited.add(n.des);
            minDis.put(n.des,n.weight);
            
            if(map.containsKey(n.des)){
                for(NextNode next : map.get(n.des)){
                    pq.offer(new NextNode(next.des,n.weight+next.weight));
                }
            }
        }
        
        if(minDis.size()<N)
            return -1;
        
        int max =-1;
        for(int e: minDis.values())
            max = e>max ? e : max;
        
        return max;
    }
}
