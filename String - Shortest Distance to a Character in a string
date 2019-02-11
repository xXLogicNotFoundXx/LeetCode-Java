// https://leetcode.com/problems/shortest-distance-to-a-character/
class Solution {
    public int[] shortestToChar(String S, char C) {
        int[] res = new int[S.length()];
        Arrays.fill(res, S.length()); //  at this point we have Output: [12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12]
        int count = -1;
        for(int i=0; i < S.length(); i++){
            if(S.charAt(i) == C) {
                count = 0;
            }   
            else if(count == -1) continue;
            else count++;
            res[i] = count;
        }                         //  at this point we have Output: [12, 12, 12, 0, 1, 0, 0, 1, 2, 2, 1, 0]
        count = -1;
        for(int i=S.length()-1; i>=0; i--){
            if(S.charAt(i) == C) count = 0;
            else if(count == -1) continue;
            else count = Math.min(res[i], count+1);
            res[i] = count;
        }                       //  at this point we have Output: [3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0]
        return res;
    }
    
    
/* Another Algorithm 
    1st pass - you use queue and add only char C indexes 
    then for each char you use POP and PEEK so only two indexes of the the char C 
        Then youe compair POP PEEK indexes which one is closer and write that in an array 
    if POPed index is closer then you push the poped element 
    else you throw away poped element ( bcz going forward poped element wont be closer anymore)
    ovoiusly if the Queue is empty peek would return null and you push the poped index 
*/

class Solution {
    public int[] shortestToChar(String S, char C) {
        
    }
}

    
  /* This one works but very complicated 
    public int[] shortestToChar(String S, char C) {
        if(S == null || S.isEmpty())
            return new int[0];
        
        int []a = new int[S.length()];
        char []chars = S.toCharArray();
        boolean found=false;
        int count = -1;
        for(int i=0;i<chars.length;i++){
            if(chars[i]==C){
                count=0;
                found=true;
            }
            else if(found)
                count++;
            a[i] = count;    
        }
        count=-1;
        found=false;
        for(int i=chars.length-1;i>=0;i--){
            if(chars[i]==C){
               count=0; 
               found=true;
            } else if(found)
                count++;
            if(count==-1)
                continue;
            if(a[i]==-1)
                a[i]=count;
            if(count<a[i])
                    a[i] = count;
        }
        return a;
    }*/
}
