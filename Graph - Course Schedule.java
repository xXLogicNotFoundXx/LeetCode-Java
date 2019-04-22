/*
https://leetcode.com/problems/course-schedule/
it is essentially building a graph and checking if there is a cycle 
*/
class Solution {
    public boolean canFinish(int numCourses, int[][] pre) {
        Map<Integer,Set<Integer>> map = new HashMap<>();
        for(int i=0;i<pre.length;i++){
            Set<Integer> set = map.getOrDefault(pre[i][1], new HashSet<Integer>());
            set.add(pre[i][0]);
            map.put(pre[i][1],set);
            if(pre[i][1]>numCourses) // Important 
                return false;
            
        }
        //for(Map.Entry<Integer,Set<Integer>> e : map.entrySet()){
        //    System.out.println(e.getKey() + " " +e.getValue());
        //}
        Set<Integer> visited = new HashSet<>();
        boolean[] cycle = new boolean[numCourses];
        for(Map.Entry<Integer,Set<Integer>> e : map.entrySet()){
           
            if(isCycle(e.getKey(),map,visited,cycle))
                return false;
        }
        return true;
    }
    
    boolean isCycle(int i, Map<Integer,Set<Integer>> map, Set<Integer> visited, boolean []cycle){
        
        visited.add(i);
        cycle[i] = true;
        
        if(map.containsKey(i)){ 
            for(int p : map.get(i)){  
                if(cycle[p] == true ||  // This condiion is very important  
                   !visited.contains(p) && isCycle(p,map,visited,cycle))
                    return true; 
            }
        }
        
        cycle[i] = false;
        return false;
    }
}
