https://leetcode.com/problems/generate-parentheses/
For example, given n = 3, a solution set is:
[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]

class Solution {
  public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<String>();
        DFS_Paranthesis(list, "(", 1, 0, n);
        return list;
 }
 
 public void DFS_Paranthesis(List<String> list, String str, 
				             int open, int close, int max){
 
        if(str.length() == max*2){
            list.add(str);
            return;
        }
 
        if(open < max)
            DFS_Paranthesis(list, str+"(", open+1, close, max);
        if(close < open)
            DFS_Paranthesis(list, str+")", open, close+1, max);
 }
}
