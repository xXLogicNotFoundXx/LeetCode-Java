/*
https://leetcode.com/problems/edit-distance/
Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.
You have the following 3 operations permitted on a word:
Insert a character
Delete a character
Replace a character
Example 1:
Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation: 
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')

Note that the problem is symmetric. The insert operation in one direction (i.e. from word1 to word2) is same as delete operation in other. So, we could choose any direction.
If you see diagonal where both strings are same then matrix gonna look like 
0 1 2 3 4
1 0 n n n
2 n 0 n n
3 n n 0 n
4 n n n 0
        ^
        This is what the ans is. 
        
https://www.youtube.com/watch?v=MiqoA-yF-0M
*/

public class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        
        int[][] cost = new int[m + 1][n + 1];
        for(int i = 0; i <= m; i++)
            cost[i][0] = i;
        for(int i = 1; i <= n; i++)
            cost[0][i] = i;
        
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    cost[i][j] = cost[i-1][j-1];
                } else {
                    int a = cost[i-1][j-1]; // top_left 
                    int b = cost[i-1][j];   // top
                    int c = cost[i][j-1];   // left 
                    cost[i][j] = Math.min(Math.min(a,b),c);
                    cost[i][j]++;
                }
            }
        }
        return cost[m][n];
    }
}
