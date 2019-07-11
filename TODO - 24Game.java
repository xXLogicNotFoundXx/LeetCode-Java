/*
https://leetcode.com/problems/24-game/
You have 4 cards each containing a number from 1 to 9. You need to judge whether they could operated through *, /, +, -, (, ) to get the value of 24.
Note:
The division operator / represents real division, not integer division. For example, 4 / (1 - 2/3) = 12.
Every operation done is between two numbers. In particular, we cannot use - as a unary operator. 
For example, with [1, 1, 1, 1] as input, the expression -1 - 1 - 1 - 1 is not allowed.
You cannot concatenate numbers together. For example, if the input is [1, 2, 1, 2], we cannot write this as 12 + 12.

Example 1:
Input: [4, 1, 8, 7]
Output: True
Explanation: (8-4) * (7-1) = 24

Time Complexity: O(1). There is a hard limit of 9216 possibilities, and we do O(1) work for each of them.
Space Complexity: O(1). Our intermediate arrays are at most 4 elements, and the number made is bounded by an (1) factor.

??What would be the time complexity for arbitrarily sized number lists (n=4 here)? 

*/
class Solution {
    public boolean judgePoint24(int[] nums) {
        List<Double> list = new ArrayList<>();
    
        for (int i : nums) 
            list.add((double) i);
        
        return dfs(list);
    }

    private boolean dfs(List<Double> list) {
        if (list.size() == 1) {
            if (Math.abs(list.get(0)- 24.0) < 0.001) {
                return true;
            }
            return false;
        }

        for(int i = 0; i < list.size(); i++) {
            for(int j = i + 1; j < list.size(); j++) {
                for (double c : generatePossibleResults(list.get(i), list.get(j))) {
                    
                    List<Double> nextRound = new ArrayList<>();
                    nextRound.add(c);
                    
                    for(int k = 0; k < list.size(); k++) {
                        if(k == j || k == i) 
                            continue;
                        
                        nextRound.add(list.get(k));
                    }
                    
                    if(dfs(nextRound)) 
                        return true;
                }
            }
        }
        return false;

    }

    private List<Double> generatePossibleResults(double a, double b) {
        List<Double> res = new ArrayList<>();
        res.add(a + b);
        res.add(a - b);
        res.add(b - a);
        res.add(a * b);
        res.add(a / b);
        res.add(b / a);
        return res;
    }
}
