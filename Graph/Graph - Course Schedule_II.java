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
    // what if there are no prereqs or circles in preReqs 
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if(numCourses<=0)
            return new int[0];
        int []ans = new int[numCourses];
        Map<Integer,Set<Integer>> map = new HashMap<>(); // root , child Courses
        Map<Integer,Integer> deg = new HashMap<>(); // course , numbers of parents
        
        // this is necessary bcz if we dont have any prerequisites 
        // it should be in the ans 
        for(int i=0;i<numCourses;i++) 
            deg.put(i,0);
        
        if(prerequisites!=null){
            for(int []pair : prerequisites){
                map.putIfAbsent(pair[1], new HashSet<Integer>());
                map.get(pair[1]).add(pair[0]); // root course -> child course 
                deg.put(pair[0],deg.get(pair[0])+1); // child parents
            }
        }
        
        Queue<Integer> q = new LinkedList<>();
        for(Map.Entry<Integer,Integer> e : deg.entrySet()){
            if(e.getValue()==0)
                q.add(e.getKey());
        }
        int i=0;
        while(!q.isEmpty()){
            int c = q.poll();
            ans[i++] = c;
            if(map.containsKey(c)){ // child leaf courses may not be in the map 
                for(int next : map.get(c)){
                    int d = deg.get(next)-1;
                    if(d==0){ // we can take this course 
                        q.add(next);
                    }
                    deg.put(next,d);
                }
            }
        }
        // this bcz there could be cirles in preReqs and we dont 
        // want to return half result 
        return i<numCourses ? new int[0] : ans; 
    }
}
