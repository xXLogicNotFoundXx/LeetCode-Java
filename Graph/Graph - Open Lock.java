/*
Medium 
All big ones 5-6 times 

https://leetcode.com/problems/open-the-lock/
Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
Output: 6
Explanation:

A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
because the wheels of the lock become stuck after the display becomes the dead end "0102".

// The equals method of StringBuffer is not overridden from Object, so it is just reference equality, i.e., the same as using ==. 
// So you cannot have Set as StringBuffer and sb.eqals(sb1) is checking refrence only . 
// That is why Set<String>

Yeah, where N is the length of lock (4 in our case):
O(N) for enumerating neighbors,
O(digits^N) (10^4 in this case neighbors for each node)
O(D) for initializing deadends set
So time complexity is O(N*digits^N + D)

This is also good probel. Google asked this as you have 2D 4x4 array and you want the end state 
1,2,3,4
5,6,7,8 
Now you need to realise that this problem is also like open locks. and you have to make String out of 2d array. 

*/
class Solution {
    public int openLock(String[] deadends, String target) {
        
        Set<String> visited = new HashSet<>();
        visited.addAll(Arrays.asList(deadends));
        
        if(visited.contains("0000")) // if deadends is the starting point 
            return -1;
        
        Queue<String> q = new LinkedList<>();
        int steps =0;
    
        q.add("0000");
        visited.add("0000");
    
        while(!q.isEmpty()){
            
            int size = q.size();
            while(size-- > 0){
                
                String cState = q.poll(); 
                if(target.equals(cState))
                    return steps; 

                for(int i=0;i<cState.length();i++){
            
                    char ch = cState.charAt(i);
                    char ch1 = ch=='9' ? '0' : (char) (ch+1); 
                    char ch2 = ch=='0' ? '9' : (char) (ch-1); 
                    
                    StringBuffer sb =  new StringBuffer(cState);
                    
                    sb.setCharAt(i,ch1);
                    String str = sb.toString();
                    if(!visited.contains(str)){
                        q.add(str);
                        visited.add(str);
                    }
                    
                    sb.setCharAt(i,ch2);
                    str = sb.toString();
                    if(!visited.contains(str)){
                        q.add(str);
                        visited.add(str);
                    }
                        
                }
            }
            steps++;
        }
        
        return -1;
    }
}
