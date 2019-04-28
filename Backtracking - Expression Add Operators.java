https://leetcode.com/problems/expression-add-operators/
Input: num = "123", target = 6            Input: num = "232", target = 8
Output: ["1+2+3", "1*2*3"]                Output: ["2*3+2", "2+3*2"]
                                                                            
Input: num = "105", target = 5            Input: num = "00", target = 0
Output: ["1*0+5","10-5"]                  Output: ["0+0", "0-0", "0*0"]
                                                                               
Input: num = "3456237490", target = 9191
Output: []

Think carefully about the multiply operator. It has a higher precedence than the addition and subtraction operators. 
Note that a number can contain multiple digits.
Since the question asks us to find all of the valid expressions, we need a way to iterate over all of them.
We can keep track of the expression string and evaluate it at the very end. 
But that would take a lot of time. 
Can we keep track of the expression's value as well so as to avoid the evaluation at the very end of recursion?




We simply need to keep track of the last operand in our expression and reverse it's effect on the expression's value while considering the multiply operator.
public class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> rst = new ArrayList<String>();
        if(num == null || num.length() == 0) return rst;
        helper(rst, "", num, target, 0, 0, 0);
        return rst;
    }
    public void helper(List<String> rst, String path, String num, int target, int pos, long eval, long mult){
        if(pos == num.length()){
            if(target == eval)
                rst.add(path);
            return;
        }
        for(int i = pos; i < num.length(); i++){
            
            long cur = Long.parseLong(num.substring(pos, i + 1));
            if(pos == 0){
                helper(rst, path + cur, num, target, i + 1, cur, cur);
            }
            else{
                helper(rst, path + "+" + cur, num, target, i + 1, eval + cur , cur);
                
                helper(rst, path + "-" + cur, num, target, i + 1, eval -cur, -cur);
                
                helper(rst, path + "*" + cur, num, target, i + 1, (eval - mult) + (mult * cur), mult * cur );
            }
            
            if(num.charAt(pos) == '0') // we cant take a 2 digit number stating with 0;
                break;
        }
    }
}
