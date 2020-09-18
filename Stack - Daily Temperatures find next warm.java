/*
https://leetcode.com/articles/daily-temperatures/
Given a list of daily temperatures T, return a list such that, for each day in the input, 
tells you how many days you would have to wait until a warmer temperature. 
If there is no future day for which this is possible, put 0 instead.

For example, given the list of temperatures 
T  =  [73, 74, 75, 71, 69, 72, 76, 73], your output should be 
O/p = [1, 1, 4, 2, 1, 1, 0, 0].
*/
// think [3,2,1,7]
public int[] dailyTemperatures(int[] temperatures) {
    public int[] dailyTemperatures(int[] T) {
        Deque<Integer> stack = new ArrayDeque<>();
        int[] ans = new int[T.length];
        for(int i=0;i<T.length;i++){
            int currentTemp = T[i];
            while(!stack.isEmpty() && T[stack.peek()]<currentTemp){
                ans[stack.peek()] = i-stack.peek();
                stack.pop();
            }
            stack.push(i);
        }
        return ans;
    }
}
