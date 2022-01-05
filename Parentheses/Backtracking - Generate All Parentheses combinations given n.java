/*
Medium - VVIMP
https://leetcode.com/problems/generate-parentheses/
Sooo many companie
For example, given n = 3, a solution set is:
[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]

Time Complexity: O(2^n) = n here is max*2
As in each position, there're 2 candidates to put: '(' or ')'. For n positions, there're 2^n possibilities.
Space Complexity: O(2^n)

However, the official LeetCode solutions there is a very difficult mathematical proof to get a tighter bound
on the time/space complexity, by using "Catalan" numbers.

*/

class Solution {
     public List<String> generateParenthesis(int n) {
           List<String> list = new ArrayList<String>();
           DFS_Paranthesis(list, new StringBuffer(""), 0, 0, n);
           return list;
     }

     public void DFS_Paranthesis(List<String> list,
                                 StringBuffer str,
                                 int open, int close, int max){

        if(str.length() == max*2){
            list.add(str.toString());
            return;
        }

        if(open < max){
            DFS_Paranthesis(list, str.append("("), open+1, close, max);
            str.setLength(str.length()-1);
        }

        if(close < open){
            DFS_Paranthesis(list, str.append(")"), open, close+1, max);
            str.setLength(str.length()-1);
        }
     }
}
