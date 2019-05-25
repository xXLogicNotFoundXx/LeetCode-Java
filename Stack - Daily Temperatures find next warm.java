/*
https://leetcode.com/articles/daily-temperatures/
Given a list of daily temperatures T, return a list such that, for each day in the input, 
tells you how many days you would have to wait until a warmer temperature. 
If there is no future day for which this is possible, put 0 instead.

For example, given the list of temperatures 
T  =  [73, 74, 75, 71, 69, 72, 76, 73], your output should be 
O/p = [1, 1, 4, 2, 1, 1, 0, 0].
*/
class Solution {
    public int[] dailyTemperatures(int[] T) {
        int []res = new int[T.length];
        Stack<Integer> temp = new Stack<Integer>();
        for(int i=T.length-1;i>=0;i--){
            if(temp.empty()){
                res[i]=0;
            } else { 
                while(!temp.isEmpty()){
                    if(T[temp.peek()] > T[i]){
                        res[i]=temp.peek()-i;
                        break;
                    }
                    temp.pop();
                }
                if(temp.isEmpty()){
                    res[i]=0;
                }
            }
            temp.push(i);
        }
        return res;
    }
}
