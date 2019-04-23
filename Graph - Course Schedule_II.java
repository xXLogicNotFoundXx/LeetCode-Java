/*
https://leetcode.com/problems/course-schedule-ii/
There are a total of n courses you have to take, labeled from 0 to n-1.
Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
Input: 2, [[1,0]] 
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished   
             course 0. So the correct course order is [0,1] 
             
Input: 4, [[1,0],[2,0],[3,1],[3,2]]
Output: [0,1,2,3] or [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both     
             courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. 
             So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .

We gonna build a graph through map<int,set> which will tells what courses need this prereq
another map which tells how many prereqs a course need.
We start with courses which do not have any prereq 
    then travers the course in map and remove the prereq degree of those courses we add a course in a queu when its degree is 0.
*/

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if(numCourses<=0 || prerequisites==null )
            return new int[0];
        Map<Integer,Set<Integer>> graph = new HashMap<>(); // list of courses this course is a prereq
        Map<Integer,Integer> preReqs = new HashMap<>(); // this one says how many course need this course as prereq 
        
        // all courses have no prereq yet
        for(int i=0;i<numCourses;i++)
            preReqs.put(i,0);
        
        for( int []p : prerequisites){
            // remember getOrDefault(key,defaultValue) there is no get(key) call inside getOrDefault urrgg
            Set<Integer> set = graph.getOrDefault(p[1], new HashSet<Integer>()); 
            set.add(p[0]);                         // Very important to build graph correctly 
            graph.put(p[1],set);
            preReqs.put(p[0],preReqs.get(p[0])+1); // Very important to build preReq degrees correctly 
            
        }
        
        Queue<Integer> q = new LinkedList<>();
        for(Map.Entry<Integer,Integer> e : preReqs.entrySet()){
            if(e.getValue().equals(0)){ // courses that dont have prereq  
                q.add(e.getKey());
            }
        }
        
        int []ans = new int[numCourses];
        int i=0;
        while(!q.isEmpty()){
            int c = q.poll();
            ans[i++] = c;
            if(graph.containsKey(c)){
                for(int nc : graph.get(c)){
                   int d = preReqs.get(nc)-1;
                   preReqs.put(nc,d);
                   if(d==0){
                       q.add(nc);
                   }
                }
            }
        }
        return i==numCourses ? ans : new int[0];
    }
}
