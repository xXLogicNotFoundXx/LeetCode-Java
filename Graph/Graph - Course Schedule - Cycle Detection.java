/*
Medium -  VIMP
Amazon27 Microsoft14 Facebook8 Google6 ....

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

  // Topological Sort
  public boolean canFinish(int numCourses, int[][] prerequisites) {
      Map<Integer, Set<Integer>> preqMap = new HashMap<>();
      Map<Integer, Integer> degreeCount = new HashMap<>();

      // pair [0, 1] - Indicates that to take course 0 you have to first take course 1.
      for(int i=0; i<prerequisites.length; i++) {

          int preRequisit = prerequisites[i][1];
          int nextCourse = prerequisites[i][0];

          preqMap.putIfAbsent(preRequisit, new HashSet<Integer>());
          degreeCount.putIfAbsent(preRequisit, 0); // 0 degree for prerequisit

         if( !preqMap.get(preRequisit).contains(nextCourse) )  {
              preqMap.get(preRequisit).add(nextCourse); // Make a link
              degreeCount.put(nextCourse, degreeCount.getOrDefault(nextCourse, 0) + 1); // +1 degree for nextCourse
          }
      }

      // there could be some courses that had no prerequisites entries
      for(int i=0; i<numCourses; i++){
           degreeCount.putIfAbsent(i, 0);
      }

      // System.out.println(degreeCount);
      // Start with the course that doesnt have prerequisits
      Queue<Integer> queue = new LinkedList<>();
      for(Map.Entry<Integer, Integer> e : degreeCount.entrySet()){
          if(e.getValue() == 0){
             queue.add(e.getKey());
          }
      }

      Set<Integer> takenCourses = new HashSet<>();
      while(!queue.isEmpty()){
          int curCourse = queue.poll();
          takenCourses.add(curCourse);

          for( int nextCourse : preqMap.getOrDefault(curCourse, new HashSet<Integer>())){

              // decrease the degree of these courses
              degreeCount.put(nextCourse, degreeCount.get(nextCourse)-1);
              if(degreeCount.get(nextCourse)==0){
                  // we can take this course put this in the queue
                  degreeCount.remove(nextCourse);
                  queue.add(nextCourse);
              }
          }
      }

      return takenCourses.size()==numCourses;

  }

    /*  Solution 2 : Cycle detection.

        Time -> O( E(Build a graph) +  N+E(Traverse Graph)) -> so O(N+E)
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

                                                        // New visited set everytime
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
