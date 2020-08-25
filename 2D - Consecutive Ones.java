/* Premium: 
Longest Line of Consecutive One in Matrix
https://leetcode.com/problems/longest-line-of-consecutive-one-in-matrix/
Given a 01 matrix M, find the longest line of consecutive one in the matrix. The line could be horizontal, vertical, diagonal or anti-diagonal.
Example:
 [0,1,1,0]
 [0,1,1,0]
 [0,0,0,1]
Output: 3
*/
class Solution {
    public int longestLine(int[][] M) {
        if(M==null || M.length ==0)
            return 0;
        int []row = new int[M.length];
        int []col = new int[M[0].length];
        int []slash = new int[M.length + M[0].length]; // calculation of index will be i+j let that sink in 
        // i+j will be 0-M.length + M[0].length
        int []backslash = new int[M.length + M[0].length]; // this one is tough j-i+M.length let that sink in  
        // -i+M.length this will never cross 0-M.length and then j is alwasy plus so that adds up to M.length + M[0].length
        int max=0;
        for(int i=0;i<M.length;i++){
            for(int j=0;j<M[0].length;j++){
                if(M[i][j] == 1){
                    row[i]++;
                    col[j]++;
                    slash[i+j]++;
                    backslash[j-i+M.length]++;
                    max = Math.max(max,row[i]);
                    max = Math.max(max,col[j]);
                    max = Math.max(max,slash[i+j]);
                    max = Math.max(max,backslash[j-i+M.length]);
                }else{
                    row[i] = 0;
                    col[j] = 0;
                    slash[i+j]  = 0;
                    backslash[j-i+M.length]  = 0;
                }
            }
        }
        return max;
    }
}
