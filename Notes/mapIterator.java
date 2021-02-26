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
