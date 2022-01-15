/*
Medium -  not that imp
Google2 Facebook2

1136 https://leetcode.com/problems/parallel-courses/

You are given an integer n which indicates that we have n courses, labeled from 1 to n.
You are also given an array relations where relations[i] = [a, b], representing a prerequisite
relationship between course a and course b: course a has to be studied before course b.

In one semester, you can study any number of courses as long as you have studied all the prerequisites for the course you are studying.
Return the minimum number of semesters needed to study all courses.
If there is no way to study all the courses, return -1.

*/
class Solution {

    /*
        Approach 1 - BSF .. start with zero dependant courses - Topological sort
        Time - O(N+E)  N to build a graph + BFS is every node and every Edge => N+E => O(N+E)
        Space - O(N+E)

        Approach 2 - To find a cycle OR keep track of the max height.
    */
    public int minimumSemesters1(int n, int[][] relations) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        Map<Integer, Integer> dependancy  = new HashMap<>();
        for(int[] course : relations){

            map.putIfAbsent(course[0], new ArrayList<Integer>());
            map.get(course[0]).add(course[1]);

            dependancy.put(course[1],dependancy.getOrDefault(course[1], 0) + 1);
            dependancy.putIfAbsent(course[0],0);
        }

        Deque<Integer> queue = getIndependantCourses(dependancy);

        if(queue.size()==0)
            return -1;

        int semisters = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                int course = queue.poll();
                // process Edges
                List<Integer> list = map.getOrDefault(course, new ArrayList<>());

                for(int depCourse : list){
                    int count = dependancy.getOrDefault(depCourse,0);
                    if(count!=0){
                        dependancy.put(depCourse, count-1);
                        if(count==1){
                           dependancy.remove(depCourse);
                           queue.add(depCourse);
                        }
                    }
                }
            }
            semisters++;
        }

        return dependancy.isEmpty() ? semisters : -1;
    }

    Deque<Integer> getIndependantCourses(Map<Integer, Integer> dependancy){
        Deque<Integer> courses = new ArrayDeque<>();

        // good to know how iterator are working
        // ofcourse we go through entryset() bcz it is a Set and we get iterator for Set
        Iterator it = dependancy.entrySet().iterator();
        while(it.hasNext()){
            // See it.next() needs casting
            Map.Entry<Integer, Integer> e = (Map.Entry<Integer, Integer>) it.next();
            if(e.getValue()==0){
               courses.add(e.getKey());
               it.remove();
            }
        }
        return courses;
    }


    /*
        Approach 2 - To find a cycle OR keep track of the max height.

        Build a graph and note downstarting nodes(indepedant courses)
        From each indepedant course do DFS
        track visited if(visited hit we found the cycle) and also build memoization bcz
        Graph could be Diamond shape and we dont want to calculate depth of a node multiple times.

        Time & Space : O(N+E)

    */
    public int minimumSemesters(int n, int[][] relations) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        Set<Integer> independantCourses = new HashSet<>();
        for(int[] course : relations){

            map.putIfAbsent(course[0], new ArrayList<Integer>());
            map.get(course[0]).add(course[1]);

            independantCourses.add(course[0]);
            independantCourses.remove(course[1]);
        }

        if(independantCourses.size()==0)
            return -1;

        int semister = 0;
        Map<Integer,Integer> memoization = new HashMap<Integer,Integer>();

        for( int course : independantCourses){
            int sem = traverseDFS(course, map, new HashSet<Integer>(), memoization);
            if(sem==-1)
                return -1;
            semister = Math.max(semister,sem);
        }

        return semister;
    }

    int traverseDFS(int course, Map<Integer, List<Integer>> map,
                    Set<Integer> visited, Map<Integer,Integer> memoization){

        if(!map.containsKey(course)) // leaf nodes wont be in map
            return 1;

        if(visited.contains(course))
            return -1;

        if(memoization.containsKey(course))
            return memoization.get(course);

        visited.add(course);
        List<Integer> depCourses = map.get(course);

        int semister = 0;
        for( int c : depCourses){
            int sem = traverseDFS(c, map, visited, memoization);
            if(sem==-1)
                return -1;
            semister = Math.max(semister,sem);
        }

        // graph could be diamond like structure
        // so we need to remove the end node for second path
        visited.remove(course);
        // remember to remove visited may be in some case you dont need to but always pay attention to this
        // may in bi-directional graph .. you would need not to for cycle detection
        // bcz diamond structure would form a cycle
        // i just realized with memoization removing visited would not matter bcz
        // mamoization will just return the value. But for that returning from memozation should be first
        // before we check visited.contains(course)
        memoization.put(course, semister+1);        // build mem here not in loop urrggg

        return semister+1;
    }
}
