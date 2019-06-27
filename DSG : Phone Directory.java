/*
https://leetcode.com/problems/design-phone-directory/
Design a Phone Directory which supports the following operations.
Phone Directory is initialized with maxNumbers it can contain.
get: Provide a number which is not assigned to anyone.
check: Check if a number is available or not.
release: Recycle or release a number.
*/
class PhoneDirectory {

    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    Queue<Integer> queue = new LinkedList<>();
    Set<Integer> set = new HashSet<>();
    int maxNumbers = 0;
    public PhoneDirectory(int maxNumbers) {
        queue = new LinkedList<>();
        set = new HashSet<>();
        this.maxNumbers = maxNumbers;
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if(set.size()==maxNumbers)
            return -1;
        
        if(queue.isEmpty()){
            set.add(set.size());
            return set.size()-1;
        }
        
        int nu = queue.poll();
        set.add(nu);
        return nu;
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        return !set.contains(number);
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        if(set.contains(number)){
            set.remove(number);
            queue.add(number);
        }
    }
}
