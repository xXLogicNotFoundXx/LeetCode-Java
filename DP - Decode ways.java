https://leetcode.com/problems/decode-ways/
Input:  "112"    -> AAB, FB,AE
output : 3 
class Solution {
    public int numDecodings(String str) {
         if(str == null || str.isEmpty()) return 0;
        int n = str.length();
        int [] dp = new int[n+1];
        dp[n]=1; 
        dp[n-1] = str.charAt(n-1) == '0' ? 0 : 1; 
        for(int i=n-2;i>=0;i--){
            char ch = str.charAt(i);
            if(ch == '0'){
                dp[i] = 0;
            } else {
                int j = Integer.valueOf(ch); // assuming it will always be 1-9 
                dp[i] = dp[i+1];
                int k = Integer.valueOf(str.substring(i, i + 2));
                if (k >= 10 && k <= 26) {
                    dp[i] += dp[i + 2];
                }
            }
        }
        return dp[0];
    }
}
