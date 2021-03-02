/*
https://leetcode.com/problems/minimum-increment-to-make-array-unique/submissions/

Given an array of integers A, a move consists of choosing any A[i], and incrementing it by 1.
Return the least number of moves to make every value in A unique.

Input: [1,2,2]
Output: 1
Explanation:  After 1 move, the array could be [1, 2, 3].

Input: [3,2,1,2,1,7]
Output: 6
Explanation:  After 6 moves, the array could be [3, 4, 1, 2, 5, 7].
It can be shown with 5 or less moves that it is impossible for the array to have all unique values.

*/
class Solution {
    
    public int minIncrementForUnique(int[] A) {
        Arrays.sort(A);
        
        int count = 0;
        for(int i=1;i<A.length;i++){
            
            if(A[i-1]<A[i])
                continue; 
            
            if(A[i-1]==A[i]){
                count++;
                A[i] = A[i] + 1;
            } else {
                int inc = A[i-1] - A[i] + 1;
                count+= inc;
                A[i] = A[i] + inc;
            }
            
        }
        return count;
    }
    
    /*   
    public int minIncrementForUnique(int[] A) {
        Arrays.sort(A);
        
        Deque<Integer> deque = new ArrayDeque<>();
        
        int count =0;
        for(int num : A){
            if(deque.isEmpty() || !deque.isEmpty() && deque.peekLast() < num){
                deque.clear(); 
                deque.add(num);
            }
            else  if(!deque.isEmpty() && deque.peekLast() == num){
                count++;
                deque.pollFirst();
                deque.add(num+1);
            } 
            else if(!deque.isEmpty() && deque.peekLast() > num){
                int increment =  deque.peekLast() - num + 1;
                count += increment;
                deque.pollFirst();
                deque.add(num+increment);
            } 
                
        }
        
        return count;
    }*/
}
