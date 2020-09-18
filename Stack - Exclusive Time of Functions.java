/*
https://leetcode.com/problems/exclusive-time-of-functions/

n = 2
logs = ["0:start:0","1:start:2","1:end:5","0:end:6"]
Output: [3, 4]

Input:
2
["0:start:0","0:start:2","0:end:5","1:start:7","1:end:7","0:end:8"]
Output:
[7,1]
Expected:
[8,1]

Wrong Test Case? https://leetcode.com/problems/exclusive-time-of-functions/discuss/852013/Wrong-Test-Case
*/

class Solution {
    public int[] exclusiveTime(int n, List<String> logs) {
        Deque<Pair<Integer,Integer>> startStack = new ArrayDeque<>();
        Deque<Pair<Integer,Integer>> endStack = new ArrayDeque<>();
        int[] ans = new int[n];
        for(String s : logs){
            String[] str = s.split(":");
            
            if(str[1].equals("start")){
            
                if(startStack.size()!=endStack.size()){ // put the missing entry in endStack 
                    endStack.push(new Pair<Integer,Integer>( startStack.peek().getKey(), Integer.valueOf(str[2])-1));
                }
                
                startStack.push(new Pair<Integer,Integer>( Integer.valueOf(str[0]), Integer.valueOf(str[2])));
            }else{
                
                if(startStack.size()==endStack.size()){ // put the missing entry in startStack
                    startStack.push(new Pair<Integer,Integer>( Integer.valueOf(str[0]), endStack.peek().getValue()+1));
                }
                
                endStack.push(new Pair<Integer,Integer>( Integer.valueOf(str[0]), Integer.valueOf(str[2])));
            }
        }
        
        while(!startStack.isEmpty()){
            ans[startStack.peek().getKey()] += endStack.peek().getValue() - startStack.peek().getValue() + 1;
            System.out.println(startStack.pop()+ " " +endStack.pop());
        }

        return ans;
    }
}
