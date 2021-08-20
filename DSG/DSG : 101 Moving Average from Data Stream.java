/*
https://leetcode.com/problems/moving-average-from-data-stream/

Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

Example:
MovingAverage m = new MovingAverage(3);
m.next(1) =  return (1 / 1)
m.next(10) = return (1 + 10) / 2
m.next(3) = return (1 + 10 + 3) / 3
m.next(5) = return (10 + 3 + 5) / 3

The idea is very nice: 
We store number stream in the queue and maintain Qsize=given max number. 
We also keep totalSum and divide totalSum/Qsize;
as we poll we subtract that number from totalSum;
*/

class MovingAverage {

    /** Initialize your data structure here. */
    int minus = 0;
    int totalSum = 0;
    int maxSize =0;
    
    Queue<Integer> queue = new LinkedList<Integer>();
    public MovingAverage(int size) {
        maxSize=size;   
    }
    
    public double next(int val) {
        
        totalSum = totalSum + val; 
        queue.add(val);
        
        if(queue.size()>maxSize){
            totalSum -= queue.poll();
        }
        
        return totalSum/(double)queue.size();
    }
}
