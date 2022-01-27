/*
Medium - Vimp
All big ones 5-6 times

https://leetcode.com/problems/open-the-lock/
Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
Output: 6
Explanation:

A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
because the wheels of the lock become stuck after the display becomes the dead end "0102".

// The equals method of StringBuffer is not overridden from Object, so it is just reference equality, i.e., the same as using ==.
// So you cannot have Set as StringBuffer and sb.eqals(sb1) is checking refrence only .
// That is why Set<String>

Yeah, where N is the length of lock (4 in our case):
O(N) for enumerating neighbors,
O(digits^N) (10^4 in this case neighbors for each node)
O(D) for initializing deadends set
So time complexity is O(N*digits^N + D)

This is also good probel. Google asked this as you have 2D 4x4 array and you want the end state
1,2,3,4
5,6,7,8
Now you need to realise that this problem is also like open locks. and you have to make String out of 2d array.

Complexity: O(N^2 * A^N + D)

N is Number of dials (4 in our case)
A is number of alphabets (10 in our case -> 0 to 9)
D is the size of deadends.

There are 10 x 10 x 10 x 10 possible combinations => 10^4 => A^N
For each combination, we are looping 4 times (which is N) and in each iteration,
there are toString() operations ( which is O(N) again ) => O(4N*N) => O(N^2)

Total complexity => A^N * N^2, plus D to create the hashset => N^2 * A^N + D

Space : O(A^N + D)
*/
class Solution {
    public int openLock(String[] deadends, String target) {

        if(target==null)
            return -1;

        Set<String> deadLocks = getDeadendSet(deadends);

        String start = "0000";
        int level = 0;

        if(deadLocks.contains(start) || deadLocks.contains(target))
            return -1;

        Queue<String> queue = new LinkedList<>();
        queue.add(start);

        while(!queue.isEmpty()){
            int size = queue.size();

            for(int i=0; i<size; i++){
                String nextState = queue.poll();

                if(nextState.equals(target))
                    return level;

                for(int s=0; s<nextState.length(); s++){
                    List<String> states = getNextStates(nextState, s);

                    for(String state : states){
                        if(!deadLocks.contains(state)){ // deadlock acting like visited
                            deadLocks.add(state);
                            queue.add(state);
                        }
                    }
                }
            }

            level++;
        }
        return -1;
    }

    Set<String> getDeadendSet(String[] deadends){
        Set<String> deadLocks = new HashSet<>();

        if(deadends==null)
            return deadLocks;

        for(String dead : deadends){
            if(dead!=null){
                deadLocks.add(dead);
            }
        }

        return deadLocks;
    }

    List<String> getNextStates(String state, int i){

        List<String> states = new ArrayList<>();
        StringBuilder sb = new StringBuilder(state);

        char ch = state.charAt(i) == '9' ? '0' : (char) (state.charAt(i) + 1);
        sb.setCharAt(i,ch);
        states.add(sb.toString());

        ch = state.charAt(i) == '0' ? '9' : (char) (state.charAt(i) - 1);
        sb.setCharAt(i,ch);
        states.add(sb.toString());

        return states;
    }
}
