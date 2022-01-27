https://leetcode.com/problems/design-circular-queue/
// This looks simple but if you dont consider extra variable len then it is very diff...
// the key to this problem is .. add extra variables and make life easier.
// head tail start at the same index -1. .. these values are gaurded by isEmpty() and isFull()
// head always points to the value ... while tail+1 points the front of the queue.

class MyCircularQueue {
    int [] queue;
    int head, tail = -1;
    int len=0; // this is the key with only head and tail that creates so many corner cases
    int size;
    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        queue = new int[k];
        head=-1;
        tail=-1;
        size=k;
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if(isFull()) return false;
        head = (head+1)%size;
        queue[head] = value;
        len++;
        return true;
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if(isEmpty()) return false;
        tail = (tail+1)%size;
        len--;
        return true;
    }

    /** Get the front item from the queue. */
    public int Front() {
        if(isEmpty()) return -1;
        return queue[(tail+1)%size];
    }

    /** Get the last item from the queue. */
    public int Rear() {
        if(isEmpty()) return -1;
        return queue[head];
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return len==0;
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return len==size;
    }
}
