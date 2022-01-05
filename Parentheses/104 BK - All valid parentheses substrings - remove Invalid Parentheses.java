/*
Medium - IMP
Facebook27 Amazon3 Apple2

https://leetcode.com/problems/remove-invalid-parentheses/
Given a string s that contains parentheses and letters, remove the minimum number of invalid parentheses to make the input string valid.
Return all the possible results. You may return the answer in any order.

Input: "()())()"
Output: ["()()()", "(())()"]

for First pass count which ones ( , ) to remove and how many
then we do dfs with use it or dont use it. In the end we add result to set to avoid duplicate.

Time Complexity : O(2^N)
 since in the worst case we will have only left parentheses in the expression and for every bracket
 we will have two options i.e. whether to remove it or consider it.
 Considering that the expression has N parentheses, the time complexity will be O(2^N).
 
Space Complexity : O(N)
 depth of the tree is gonna be N.
*/
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        int rmL = 0, rmR = 0;
        //First pass count which ones ( , ) to remove and how many
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                rmL++;
            } else if (s.charAt(i) == ')') {
                if (rmL != 0)
                    rmL--;
                else
                    rmR++;
            }
        }
        Set<String> res = new HashSet<>();                  // Set is very Important bcz we gonna get duplicates in the ans.
        dfs(s, 0, res, new StringBuilder(), rmL, rmR, 0);
        return new ArrayList<String>(res);   // set to arrayList
    }

    public void dfs(String s, int i, Set<String> res, StringBuilder sb, int rmL, int rmR, int open) {
        if (rmL < 0 || rmR < 0 || open < 0) {
            return;
        }
        if (i == s.length()) {
            // open is very important  for example for ip )() we dont want )( as the ans open=1 will avoid that.
            if (rmL == 0 && rmR == 0 && open == 0)
                res.add(sb.toString());
            return;
        }

        char c = s.charAt(i);
        int len = sb.length();

        if (c == '(') {
            dfs(s, i + 1, res, sb, rmL - 1, rmR, open);		    // not use (
            dfs(s, i + 1, res, sb.append(c), rmL, rmR, open + 1);       // use (

        } else if (c == ')') {
            dfs(s, i + 1, res, sb, rmL, rmR - 1, open);	            // not use  )
            dfs(s, i + 1, res, sb.append(c), rmL, rmR, open - 1);  	    // use )

        } else {
            dfs(s, i + 1, res, sb.append(c), rmL, rmR, open);
        }

        sb.setLength(len);
    }
}
