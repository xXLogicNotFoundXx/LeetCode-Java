/*
https://leetcode.com/problems/letter-combinations-of-a-phone-number/
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
A mapping of digit to letters is just like on old telephone buttons.

Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
*/
class Solution {
    //  Time and space complexity = O(3^N * 4^M)
    public List<String> letterCombinations(String digits) {
        String[] mapping = new String[] {" ", "*", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"}; 
        // LeetCode needed mapping 0->" " and 1->"*" you can ask whats the mapping they want
        if(digits==null || digits.length()==0) 
            return new ArrayList();
        List<String> ans = new ArrayList();
        StringBuffer oneAns = new StringBuffer();
        letterCombinationsHelper(digits,mapping,0,oneAns,ans);
        return ans;
    }
    
    void letterCombinationsHelper(String digits,String[] mapping, int i, StringBuffer oneAns, List<String> ans){
        if(i==digits.length()){
            ans.add(oneAns.toString());
            return;
        } 
        int mapI = Integer.valueOf(digits.charAt(i)-'0'); // here minus of '0' is very important i forgot that one 
        for(char ch : mapping[mapI].toCharArray()){
            oneAns.append(ch);
            letterCombinationsHelper(digits,mapping,i+1,oneAns,ans);
            oneAns.setLength(oneAns.length()-1);
        }
    }
}
