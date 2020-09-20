/*
https://leetcode.com/problems/min-stack/
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
*/
class MinStack {

    /** initialize your data structure here. */
    int min = Integer.MAX_VALUE;
    ArrayDeque<Integer> stack = new ArrayDeque<>();
    public MinStack() {
        
    }
    
    public void push(int x) {
        if(x<=min){  // '=' is required test case:  push -[0,1,0] pop, getMin()
            stack.push(min);
            min=x;
        }
        stack.push(x);
    }
    
    public void pop() {
        int x = stack.pop();
        if(x==min){
            min = stack.pop();
        }
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return min;
    }
}
