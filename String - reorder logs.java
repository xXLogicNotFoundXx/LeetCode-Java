/*
https://leetcode.com/problems/reorder-log-files/
You have an array of logs.  Each log is a space delimited string of words.
For each log, the first word in each log is an alphanumeric identifier.  Then, either:
Each word after the identifier will consist only of lowercase letters, or;
Each word after the identifier will consist only of digits.
We will call these two varieties of logs letter-logs and digit-logs.  
It is guaranteed that each log has at least one word after its identifier.
Reorder the logs so that all of the letter-logs come before any digit-log. 
The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.  
The digit-logs should be put in their original order.
Return the final order of the logs.
Input: ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]
Output: ["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]
*/
class Solution {
    // M*NlogN ( m is avg length of string and N is the size of the array. 
    public String[] reorderLogFiles(String[] logs) {
        PriorityQueue<String> pq = new PriorityQueue<String>(new Comparator<String>(){
            public int compare(String A, String B){
                String [] a = A.split(" ",2);   // creates 2 arrays only
                String [] b = B.split(" ",2);   // creates 2 arrays only
                
                int comp = a[1].compareTo(b[1]); // compare logs
                if(comp==0)
                    return a[0].compareTo(b[0]); // compare identifier 
                
                return comp;
            }
        });
        
        List<String> list = new ArrayList<>();
        for(int i=0;i<logs.length;i++){
            int spaceIndex = logs[i].indexOf(" ");
            if(Character.isDigit(logs[i].charAt(spaceIndex+1)))
                list.add(logs[i]);
            else 
                pq.add(logs[i]);
        }
        
        String []ans = new String[logs.length];
        int i=0;
        while(!pq.isEmpty())
            ans[i++] =  pq.poll();
        
        for(int j=0;j<list.size();j++)
            ans[i++] = list.get(j);
        
        return ans;
    }
}
