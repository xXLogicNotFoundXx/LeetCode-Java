/*
Hard -
Airbnb8 Uber4 Google2
https://leetcode.com/problems/sliding-puzzle/
On an 2 x 3 board, there are five tiles labeled from 1 to 5, and an empty square represented by 0.
A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.

The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].

Given the puzzle board board, return the least number of moves required so that
the state of the board is solved. If it is impossible for the state of the board to be solved, return -1.

Complexity Analysis
Permuation of n items is n!
so we have 6! distinct states.
String.indexOf() and String.equals() cost time O(6)

Time Complexity: (6*6!) -> Replace the above 6 with m * n, we have:
Time & space: O(m * n * (m * n)!)
*/
class Solution {

    String ans = "123450";
    public int slidingPuzzle(int[][] board) {

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<board.length;i++){
           for(int j=0;j<board[0].length;j++){
               sb.append(board[i][j]);
           }
        }

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        queue.add(sb.toString());
        int steps=0;

        while(!queue.isEmpty()){

            int size = queue.size();
            for(int i=0;i<size;i++){
                String state = queue.poll();

                if(ans.equals(state))
                    return steps;


                /*  Checking visited here and adding here is also fine
                    But if we add it while generating state.. we can reduce the search space
                    Let this sink in.
                    012345 will generate 102345 & 312045
                    now, 102345 can genrate again 012345 ..
                    so adding while generating state will prevent 012345 adding into queue again.
                    With large data set we could end up a lot of duplicates in a queue.

                */
                // if(visited.contains(state)) // we moved adding to visted when we generated the state
                //    continue;

                // visited.add(state); // we should not add it here ....when we generate then we can add ... that will reduce data set

                int zeroPos = state.indexOf("0");
                List<Integer> nextPos = getNextIndices(zeroPos);

                for(int index : nextPos){
                    String str = swap(state, zeroPos,index);
                    if(!visited.contains(str)){
                        visited.add(str);  // adding here prevent the search scope duplicates in queue
                        queue.add(str);
                    }
                }
            }
            steps++;
        }
        return -1;
    }

    List<Integer> getNextIndices(int i){
        List<Integer> nextPos = new ArrayList<>();
        if(i!=0 && i!=3)
            nextPos.add(i-1); // left
        if(i!=2 && i!=5)
            nextPos.add(i+1); // rigth
        if(i>=3)
            nextPos.add(i-3); // up
        if(i<=2)
            nextPos.add(i+3); // down

        return nextPos;
    }

    String swap(String str, int i, int j){
        StringBuffer sb = new StringBuffer(str);
        char ch = sb.charAt(i);
        sb.setCharAt(i,sb.charAt(j));
        sb.setCharAt(j,ch);
        return sb.toString();
    }
}
