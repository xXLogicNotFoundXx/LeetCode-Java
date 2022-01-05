/*
Medium - All companies 2-3 times
https://leetcode.com/problems/valid-parenthesis-string/

Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.

The following rules define a valid string:
Any left parenthesis '(' must have a corresponding right parenthesis ')'.
Any right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.
'*' could be treated as a  ')'  OR  '(' OR an empty string "".

*/

class Solution {

    /*  NOT GOOD Time:O(N*3^N) -- this is what i would do.
        Backtracking too expensive ...
    */
    public boolean checkValidString1(String s) {
        return validate(s, 0, 0);
    }

    boolean validate(String s, int index, int openCount){

        for(int i=index; i<s.length(); i++){

            if(s.charAt(i)=='('){
                openCount++;
                continue;
            }

            if(s.charAt(i)==')' && openCount > 0){
                openCount--;
                continue;
            }

            if(s.charAt(i)==')'){
                return false;
            }


            boolean result = openCount > 0 ? validate(s, i+1, openCount-1) : false;   /* Consider Close bracket */
            result = result || validate(s, i+1, openCount)   ||                       /* Ignore */
                               validate(s, i+1, openCount+1);                         /* Consider Open */

            return result;
        }

        return openCount==0;
    }

    // This is more like answering
    // 1 once we match all of the () then what do we do with star ? // well we can just ignore all of those
    // 2 What if we end up having some open brackets and some reamining stars? <- THIS you have to solve.
    // 3 We can agree on if there is no ( or * to match current ) then it is false. 
    // Time:O(N) Space: O(N) <- This is good
    public boolean checkValidString(String s) {

        Stack<Integer> openStack = new Stack<>();
        Stack<Integer> starStrack = new Stack<>();
        for(int i=0; i<s.length();i++)
        {
            char ch = s.charAt(i);
            if(ch=='(')
                openStack.push(i);
            else if(ch==')')
            {
                if(!openStack.isEmpty())
                    openStack.pop();
                else if(!starStrack.isEmpty())
                    starStrack.pop();
                else
                    return false;
            }
             else
                starStrack.push(i);
        }

        // For remaining open brackets ...
        // we can consider all remaining stars as close brackets for
        // FOR remaining star stack we can just assume they are ignored

        while( !openStack.isEmpty() && !starStrack.isEmpty()){
            int openIndex = openStack.pop();
            int startIndex = starStrack.pop();
            if(openIndex > startIndex)
                return false;
        }

        return openStack.isEmpty();
    }

    // keep the count of the stars.
    // https://leetcode.com/problems/valid-parenthesis-string/discuss/598680/concise-java-algo-with-just-one-stack-one-pass-on-0ms/519337
    // However in following ... we are only pusing ( on the stack.
    // We can only use count for that so stack is not required.
    public boolean checkValidString3(String s) {
        Deque<Character> stk = new ArrayDeque<>();
        int scount = 0;
        for(char c : s.toCharArray()) {

            if(c == '(') {
                stk.push(c);
            }
            else if(c == ')') {
                if(!stk.isEmpty())
                    stk.pop();
                else if(scount > 0)  // nice!
                    scount--;
                else
                    return false;
            } else {
                scount++;

                if(!stk.isEmpty()) {
                    stk.pop();
                    // When we greedily use * complete one (,
                    // we might actually break up the existing valid pair in the sequence.
                    // And hence alienate one ')', which could have matched with current '('. So we need to account for that.
                    // this is why we added one more star
                    scount++;
                    // in the end how many starts are remaining doesnt matter. those can be considerd as empty.
                }
            }
        }

        return stk.isEmpty();
    }
}
