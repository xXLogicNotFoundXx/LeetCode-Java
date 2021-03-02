/*
https://leetcode.com/problems/course-schedule/
it is essentially building a directional graph and checking if there is a cycle 
*/
// in directed graph :
// It helps to alwasy imagine Diamond like graph no cycle and 
// same diamond as circle 
class Solution {
    
    /*  TLE Backtracking/DFS wihtout memoization 
        Without memoization .. because we can end up traversing whole graph over and over.. 
        imagine there is no cycle and we keep processing same node multiple times
        O(Edges (Bild a Graph) + Nodes*Nodes (Backtracking) ) 
            in the worst case we keep visiting every node from each node. 
        O(N^2+E)
        Space O(N+E)
    */
    public boolean canFinish1(int numCourses, int[][] pre) {
        Map<Integer,Set<Integer>> map = new HashMap<>();
        for(int i=0;i<pre.length;i++){
            Set<Integer> set = map.getOrDefault(pre[i][1], new HashSet<Integer>());
            set.add(pre[i][0]);    // do the indexes right 
            map.put(pre[i][1],set); // do the indexes right 
        }
           
        for(Map.Entry<Integer,Set<Integer>> e : map.entrySet()){
            if(isCycle(e.getKey(), map, new HashSet<>()))
                return false;
        }
        
        return true;
    }
    
    boolean isCycle(int i, Map<Integer,Set<Integer>> map, Set<Integer> visited){
        
        if(visited.contains(i))
            return true;
        
        visited.add(i); // mark that node as visited so next time we see that is a cycle
        if(map.containsKey(i)){ 
            
            // take these prerequisites 
            for(int p : map.get(i)){  

                if(isCycle(p,map,visited))
                    return true; 
            }
        }
        // we nned to remove bcz think of Diamond Shaped graph and no cycle .. for second path we gonna visit same node 
        // here you should realize we should have saved the result of the last node in a diamond ...
        // we would end up processing same node twice. 
        visited.remove(i);
        
        return false;
    }
    
    /*
        In Memoization we need to mark down the result of a node once it is processed.
        Processed node should just return the value. 
        
        Time -> O( E(Build a graph) +  N+E(Traverse Graph)) 
        so O(N+E)
        Space -> (N+E)
    */
    
    public boolean canFinish(int numCourses, int[][] pre) {
        Map<Integer,Set<Integer>> map = new HashMap<>();
        for(int i=0;i<pre.length;i++){
            Set<Integer> set = map.getOrDefault(pre[i][1], new HashSet<Integer>());
            set.add(pre[i][0]);    // do the indexes right 
            map.put(pre[i][1],set); // do the indexes right 
        }
        
        // we need to store result for each node once it is processed. 
        // this is good. Imagine graph is Huge and no cyce we dont want to process same node multiple times  
        Boolean memo[] = new Boolean[numCourses];
        
        for(Map.Entry<Integer,Set<Integer>> e : map.entrySet()){
            memo[e.getKey()] = isCycle(e.getKey(), map, new HashSet<>(), memo);
            if(memo[e.getKey()]==true) // found a cycle. 
                return false; 
        }
        
        return true;
    }
    
    boolean isCycle(int i, Map<Integer,Set<Integer>> map, Set<Integer> visited, Boolean memo[]){
        
        if(memo[i]!=null)
            return memo[i];
        
        if(visited.contains(i))
            return true;
        
        visited.add(i); // mark that node as visited so next time we see that is a cycle
        if(map.containsKey(i)){ 
            
            // take these prerequisites 
            for(int p : map.get(i)){  

                if(isCycle(p, map, visited, memo)){
                    memo[i] = true;
                    return memo[i];
                }
            }
        }
        visited.remove(i);
        
        memo[i] = false;
        return memo[i];
    }
}

