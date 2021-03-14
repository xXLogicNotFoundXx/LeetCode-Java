/*
https://leetcode.com/problems/course-schedule/
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. 
For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return true if you can finish all courses. Otherwise, return false.

it is essentially building a directional graph and checking if there is a cycle 
*/
// In directed graph :
// It helps to alwasy imagine Diamond like graph structure and imagine no cycle  
// Then cosider diamond like structure but as cycle
class Solution {
    
    /*  TLE Backtracking/DFS wihtout memoization 
        Without memoization .. because we can end up traversing whole graph over and over.. 
        imagine there is no cycle and we keep processing same node multiple times
        O(Edges (Bild a Graph) + Nodes*Nodes (Backtracking) ) 
            in the worst case we keep visiting every node from each node. 
        O(N^2+E)
        Space O(N+E)
    */

    /*
        In Memoization we need to mark down the result of a node once it is processed.
        Processed node should just return the value. 
        
        Time -> O( E(Build a graph) +  N+E(Traverse Graph)) 
        so O(N+E)
        Space -> (N+E)
    */
    //  the pair [0, 1], indicates that to take course 0 you have to first take course 1.
    public boolean canFinish(int numCourses, int[][] pre) {
        Map<Integer,Set<Integer>> map = new HashMap<>();
        for(int i=0;i<pre.length;i++){
            Set<Integer> set = map.getOrDefault(pre[i][1], new HashSet<Integer>());
            set.add(pre[i][0]);    
            map.put(pre[i][1], set); // after taking this course,  i can take courses in set.
        }
        
        // we need to store result for each node once it is processed. 
        // this is good. Imagine graph is Huge and no cyce we dont want to process same node multiple times  
        Boolean memo[] = new Boolean[numCourses];
        
        // now we will try taking each course and see we can take all the course in set 
        // while doing this we should not visit same node ... 
        // what we are gonna essentially do is, traverse all the paths and see if there is a cycle
        // and for each path we maintian visited list and we should not visit that node
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
        
        visited.add(i); // mark that node as visited so next time we see that, it is a cycle
        if(map.containsKey(i)){ 
            
            // we took i'th course now take courses in set 
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

