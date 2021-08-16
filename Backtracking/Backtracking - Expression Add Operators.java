/*
https://leetcode.com/problems/expression-add-operators/

FB13 Uber3 Square2

Input: num = "123", target = 6            Input: num = "232", target = 8
Output: ["1+2+3", "1*2*3"]                Output: ["2*3+2", "2+3*2"]
                                                                            
Input: num = "105", target = 5            Input: num = "00", target = 0
Output: ["1*0+5","10-5"]                  Output: ["0+0", "0-0", "0*0"]
                                                                               
Input: num = "3456237490", target = 9191
Output: []

"123456" and target 30.
1 * 23 - 4 + 5 + 6 = 30
12 - 3 * 4 + 5 * 6 = 30
1 - 23 - 4 + 56 = 30

Think carefully about the multiply operator. 
It has a higher precedence than the addition and subtraction operators. 

Note that a number can contain multiple digits.
Since the question asks us to find all of the valid expressions, we need a way to iterate over all of them.

We can keep track of the expression string and evaluate it at the very end, but that would take a lot of time.
Plus you would need an algorith to calculate that expression. 
Can we keep track of the expression's value as well so as to avoid the evaluation at the very end of recursion?


We simply need to keep track of the last operand and number eg (123-34*89)  
we can do +34 on the calculated_value and then  do  
(calculated_Value+34) - (34*89)
reverse it's effect on the expression's value while considering the multiply operator.

At every step along the way, we consider exactly 4 different choices or 4 different recursive paths. 
The base case is when the value of index reaches NN i.e. the length of the nums array. Hence, our complexity would be O(4^N).
O(N) max for substring. 
Overall time complexity = O(N x 4^N)

We don't consider the space occupied by the answers array since that is a part of the question's requirement and we can't reduce that in any way
Space = O(N) (recursive stack)

*/
public class Solution {
  
    public List<String> addOperators(String num, int target) {
        List<String> rst = new ArrayList<String>();
        if(num == null || num.length() == 0) 
          return rst;
        helper(rst, "", num, target, 0, 0, 0);
        return rst;
    }
  
    // adding String is extremely expensive. Speed can be increased by 20% if you use StringBuilderinstead.
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
                
                helper(rst, path + "-" + cur, num, target, i + 1, eval - cur, -cur);
                
                helper(rst, path + "*" + cur, num, target, i + 1, (eval - mult) + (mult * cur), mult * cur );
            }
            
            if(num.charAt(pos) == '0') // we cant take a 2 digit number stating with 0;
                break;
        }
    }
}





public class Solution {
    List<String> res = new LinkedList<>();
  
    public List<String> addOperators(String num, int target) {
        if (num == null || num.length() == 0) return res;
        search(0, 0, num, target, "");
        return res;
    }
  
    void search(long add, long pre, String num, long target, String exp) {
      
        if (num.length() == 0 && add+pre == target) {
            res.add(exp);
            return;
        }
      
        for (int i = 0; i < num.length(); i++) {
            String next = num.substring(0, i+1);
          
            if (next.charAt(0) == '0' && next.length() > 1) 
              break;
          
            Long cur = Long.parseLong(next);
          
            if (exp.length() == 0) {
                search(0, cur, num.substring(i+1), target, exp+cur);
            } else {
                // add + pre + cur ...
                search(add+pre, cur, num.substring(i+1), target, exp + "+" + cur);
                
                // add + pre - cur ...
                search(add+pre, -cur, num.substring(i+1), target, exp + "-" + cur);
                
                // add + pre*cur ...
                search(add, pre*cur, num.substring(i+1), target, exp + "*" + cur);
            }
        }
    }
}
