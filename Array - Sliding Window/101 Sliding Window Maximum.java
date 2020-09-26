/*
https://leetcode.com/problems/sliding-window-maximum/
You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. 
You can only see the k numbers in the window. Each time the sliding window moves right by one position.
Return the max sliding window.

Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [3,3,5,5,6,7]
 
Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
*/
Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [3,3,5,5,6,7]

Using Deque: 
Window position				 Max   Deque
---------------				 ---   ——————
i=0
[1] 3  -1  -3  5  3  6  7		-	        	1. if first index in Q(deque) < i-k+1 ? no-> so no poll()
					-			2. loop: nums[i] > last element of the Q(Deque)? no -> so no pollLast()
						1(0)		3. offer(i)
					-	1(0)		4. (i >= k - 1) no -> [window is incomplete].
   i=1								
[1  3] -1  -3  5  3  6  7			1(0)		1. if first index in Q(deque) < i-k+1 ? no-> so no poll) 
	   				-			2. loop: nums[i] > last element of the Q(Deque)? yes->so pollLast() -> 1(0) polled
						3(1)    	3. offer(i)
					-	3(1)	    	4. (i >= k - 1) no -> [window is incomplete]. 
       i=2
[1  3  -1] -3  5  3  6  7			3(1)		1. if first index in Q(deque) < i-k+1 ? no-> so no poll() 
						3(1)		2. loop : nums[i] > last element of the Q(Deque)? no -> so no pollLast()
						3(1),-1(2)	3. offer (i)	
					3	3(1),-1(2)	4. (i >= k - 1) yes -> output q.peek();
	  i=3					    	
 1 [3  -1  -3] 5  3  6  7 			3(1),-1(2)  	1. if first index in Q(deque) < i-k+1 ? no-> so no poll() 
						3(1)		2. loop: nums[i] > last element of the Q(Deque)? yes -> so pollLast() -> -1(2) polled
						3(1),-3(3)	3. offer(i)
					3	3(1),-3(3)	4. (i >= k - 1) yes -> output q.peek();
	      i=4
 1  3 [-1  -3  5] 3  6  7			-3(3)   	1. if first index in Q(deque) < i-k+1 ? yes-> so poll() -> 3(1) polled 
						-		2. loop: nums[i] > last element of the Q(Deque)? yes -> so pollLast() -> -3(3) polled
						5(4)		3. offer(i)
					5	5(4)		4. (i >= k - 1) yes -> output q.peek();
		 i=5	
 1  3  -1 [-3  5  3] 6  7			5(4)		1. if first index in Q(deque) < i-k+1 ? no-> so no poll() 
						5(4)		2. loop: nums[i] > last element of the Q(Deque)? no -> so no pollLast()
						5(4),3(5)	3. offer(i)
					5	5(4),3(5)	4. (i >= k - 1) yes -> output q.peek();
		    i=6		
 1  3  -1  -3 [5  3  6] 7			5(4),3(5)	1. if first index in Q(deque) < i-k+1 ? no-> so no poll() 
						—		2. loop: nums[i] > last element of the Q(Deque)? yes -> so pollLast() -> 3(5) & 5(4) polled
						6(6)		3. offer(i)
					6	6(6)		4. (i >= k - 1) yes -> output q.peek();

 1  3  -1  -3  5 [3  6  7]    		7	...


class Solution {
    
    public int[] maxSlidingWindow(int[] nums, int k) {
        
        if(nums==null||nums.length==0 ) 
            return new int[0];
        
        Deque<Integer> queue = new ArrayDeque<>();
    
        int[] ans = new int[nums.length-k+1];
        int ians=0;
        
        for(int i=0;i<nums.length;i++){
            // 1. if first index in  Q(Deque) < i-k+1
            if(!queue.isEmpty() && queue.peek() < i-k+1){
                queue.poll();
            }    
            
            // 2. loop: nums[i] > last element of the Q(Deque)?
            while(!queue.isEmpty() && nums[i] > nums[queue.peekLast()]){
                queue.pollLast();
            }
            
            // 3. offer(i)
            queue.offer(i);
            
            // 4. if window is complete then ouput nums[peek()]
            if(i>= k-1){ // OR   i-k+1 >= 0
                ans[ians++] = nums[queue.peek()];
            }
        }
        
        return ans;
    }
}
