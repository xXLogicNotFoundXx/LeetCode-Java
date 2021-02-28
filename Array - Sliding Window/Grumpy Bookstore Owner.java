/*
https://leetcode.com/problems/grumpy-bookstore-owner/

Today, the bookstore owner has a store open for customers.length minutes.  
Every minute, some number of customers (customers[i]) enter the store, and all those customers leave after the end of that minute.

On some minutes, the bookstore owner is grumpy.  
If the bookstore owner is grumpy on the i-th minute, grumpy[i] = 1, otherwise grumpy[i] = 0. 
When the bookstore owner is grumpy, the customers of that minute are not satisfied, otherwise they are satisfied.

The bookstore owner knows a secret technique to keep themselves not grumpy for X minutes straight, but can only use it once.
Return the maximum number of customers that can be satisfied throughout the day.

Input: customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
Output: 16
Explanation: The bookstore owner keeps themselves not grumpy for the last 3 minutes. 
The maximum number of customers that can be satisfied = 1 + 1 + 1 + 1 + 7 + 5 = 16.

*/

class Solution {
    // time - 2 pass of N - O(N)  
    // space - O(X)  Queue 
    // 2PAss - 1 to calulate total customers satisfied without trick 
    //         2 to caluclate what is the max tricked user while maintaining window ... 
    public int maxSatisfied1(int[] customers, int[] grumpy, int X) {
        
        Deque<Integer> queue = new ArrayDeque<>();
        
        int totalWithoutTrick =0;
        for(int i=0; i<customers.length; i++){
            if(grumpy[i]!=1)
                totalWithoutTrick += customers[i];
        }
        
        int trickused=0, maxUsers=0;
        for(int i=0; i<customers.length; i++){
            
            if(grumpy[i]==1){
                trickused += customers[i];
                queue.add(i);
            }
            
            if(!queue.isEmpty() && queue.peekFirst() <= i-X){
                int ind = queue.pollFirst();
                trickused -= customers[ind];
            }
            
            maxUsers = Math.max(maxUsers, totalWithoutTrick+trickused);
        }
        
        return maxUsers;
    }
    
    // now looking back at it-  i dont think i need a queue 
    // 2PAss - 1 to calulate total customers satisfied without trick 
    //         2 to caluclate what is the max tricked user while maintaining window ... 
    // time - O(N) and space - O(1)
    public int maxSatisfied2(int[] customers, int[] grumpy, int X) {
        
        
        int totalWithoutTrick =0;
        for(int i=0; i<customers.length; i++){
            if(grumpy[i]!=1)
                totalWithoutTrick += customers[i];
        }
        
        int trickused=0, maxTrcikedUsers=0;
        for(int i=0; i<customers.length; i++){
            
            if(grumpy[i]==1){
                trickused += customers[i];
            }
            
            if(0 <= i-X){
                if(grumpy[i-X]==1)
                    trickused -= customers[i-X];
            }
            
            maxTrcikedUsers = Math.max(maxTrcikedUsers,  trickused);
        }
        
        return totalWithoutTrick + maxTrcikedUsers;
    }
    
    // can you do it in one pass ? 
    // Grredy Approach is 
    /*
    1 in one pass you can calculate total customer 
    2 in one pass you can total grumpy customers 
    3 and with sliding window we can calculate max of grumpyCustomers Saved 
    4 return totalCustomer - totalGrumpy + maxGrumpySave;
    */
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int n = customers.length;
        int saveGrumpy = 0; // currently how many customers change to be happy if using technique, sliding window
        int totalCustomer = 0; // sum of all customers
        int totalGrumpy = 0; // sum of all unsatisfied customers without using technique
        
        int maxGrumpySave = 0; 
        for(int i = 0; i < n; i++) {
            totalCustomer += customers[i];
            if(grumpy[i]==1){
                totalGrumpy += customers[i];
                saveGrumpy += customers[i];
            }
            
            // window maintain
            if(0 <= i-X && grumpy[i-X]==1) {
                saveGrumpy -= customers[i-X];
            }
            
            maxGrumpySave = Math.max(saveGrumpy, maxGrumpySave);
        }
        return totalCustomer - totalGrumpy + maxGrumpySave;
    }
}
