/*
https://leetcode.com/problems/decode-ways/

Input:  "112"    -> AAB, FB,AE
output : 3 

Algo is simple if you add prev number*10 + curr number and if it forms beetween 1-26. 
    dp[cur] = dp[cur-2] + dp[cur-1]
else
    dp[cur] = dp[cur-1];   e.g 999 pnly one way 

As we are going cur-2 max we have to think how are we gonna initialize DP. 
    - If we are gonna initialize the first two and then start a loop 
    - or we are gonna make if i-2 < 0 then what?
Once you realize this
    - then there are corner cases where prev is 0 ...
        eg -> 101 -> JA -> there is only one way. dp[cur] = dp[cur-2];
    - with '00' answer should be 0 
    - with '40' answer should be 0 
*/
class Solution {
    public int numDecodings(String s) {
        if(s==null || s.isEmpty() || s.charAt(0)=='0')
            return 0;
        
        int[] dp = new int[s.length()+1];
        dp[0] = 1;      // initialize it when i-2 will be calulated 
        dp[1] = 1;      // we know first character is 1-9 
        
        char prev = s.charAt(0);
        
        for(int i=1; i < s.length(); i++){
            
            int p = i+1; // index for dp[]
            char cur = s.charAt(i);
            
            // it is just good to calculate everything before
            StringBuffer sb = new StringBuffer();
            sb.append(prev).append(cur);
            int num = Integer.valueOf(sb.toString());
            
            
            if(num==0) // consecutive zeros in the number (corner case)
                return 0;
                
            if(cur =='0') {
                dp[p] = dp[p-2] ;
                
                if(num>26)                          // 40  (corner case)
                    return 0;  
            } else {
            
                dp[p] = dp[p-1]; // this is given at ths point 
                 
                if(num>=10 && num <=26 ) {
                    dp[p] += num >9 && num <27 ?  dp[p-2] : 0;
                }
            }
            
            prev = cur;
        }
        
        return dp[s.length()];
    }
}



/**************** Better way to code *****************/
class Solution {
    public int numDecodings(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = s.charAt(0) != '0' ? 1 : 0;
        for(int i = 2; i <= n; i++) {
            int first = Integer.valueOf(s.substring(i-1, i));
            int second = Integer.valueOf(s.substring(i-2, i));
            
            if(first >= 1 && first <= 9) {
               dp[i] = dp[i-1];  
            }
            
            if(second >= 10 && second <= 26) {
                dp[i] += dp[i-2];
            }
            
            // This handles the consecutive 00 case and all other cases ...
            
        }
        return dp[n];
    }
}
