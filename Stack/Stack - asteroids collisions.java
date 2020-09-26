/*
https://leetcode.com/problems/asteroid-collision/
We are given an array asteroids of integers representing asteroids in a row.
For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). 
Each asteroid moves at the same speed. Find out the state of the asteroids after all collisions.
Find out the state of the asteroids after all collisions. 
If two asteroids meet, the smaller one will explode. 
If both are the same size, both will explode. 
Two asteroids moving in the same direction will never meet.
asteroids = [5, 10, -5]
Output: [5, 10]
Explanation:  The 10 and -5 collide resulting in 10.  The 5 and 10 never collide.

asteroids = [8, -8]
Output: []
Explanation:  The 8 and -8 collide exploding each other.

asteroids = [10, 2, -5]
Output: [10]
Explanation: The 2 and -5 collide resulting in -5.  The 10 and -5 collide resulting in 10.

 Note : Negavtive doesnt matter if there is no positive infront of them.  
Input :  [-5,-10,5] 
Output : [-5,-10,5]

*/
class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> st = new ArrayDeque<>();
        
        for(int i=0;i<asteroids.length;i++){
            if(asteroids[i]>0){
                st.push(asteroids[i]);
            } else {
                // if there are elements in the stack and peek is +ve && asteroids[i] is bigger 
                while(!st.isEmpty() && st.peek()>0 && st.peek()< asteroids[i]*-1){
                    st.pop();
                }
                
                // if stack is empty or has -ve values just add this -ve too 
                if(st.isEmpty() || st.peek()<0){
                    st.push(asteroids[i]);
                } else {
                    // st.peek() +ve and is bigger or equal 
                    if(st.peek()==asteroids[i]*-1)
                        st.pop();
                }
            }
        }
        
        int []ans = new int[st.size()];
        for(int i=st.size()-1;i>=0;i--)
            ans[i]=st.pop();
        return ans;
    }
}
