/*
https://leetcode.com/discuss/interview-question/297202/Uber-interview-question
You have two arrays of strings, words and parts. Write a function findSubstrings(words, parts) that returns the array that contains the strings from words,
modified so that any occurrences of the substrings from parts are surrounded by square brackets [], following these guidelines:

If several parts substrings occur in one string in words, choose the longest one.
If there is still more than one such part, then choose the one that appears first in the string.
Example 1:

Input: words = ["Apple", "Melon", "Orange", "Watermelon"], parts = ["a", "mel", "lon", "el", "An"]
Output: ["Apple", "Me[lon]", "Or[a]nge", "Water[mel]on"].
Explanation: While "Watermelon" contains three substrings from the parts array ("a", "mel", and "lon") "mel" is the longest substring
that appears first in the string.
*/

class Solution {

    public List<String> transform(List<String> words, List<String> parts){

        List<String> result = new ArrayList<>();
        for(String word : words){
            int index = -1, size =0;
            for(String p : parts){
                if(word.indexOf(p)>=0 && p.length() > size){
                    index = word.indexOf(p);
                    size = p.length();
                }
            }
            if(index >=0){
                    StringBuilder sb = new StringBuilder(word);
                    sb.insert(index,'[');
                    if(index+size+1 >= word.length()){
                        sb.append(']');
                    }else {
                         sb.insert(index+size+1,']');
                    }
                    result.add(sb.toString());
            }else {
                result.add(word);
            }
        }
        return result;
    }
}
public class Main {
    public static void main(String[] args) {
        String[] words = {"apple", "Melon", "Orange", "Watermelon"};
        String[] parts = {"a", "mel", "lon", "el", "An"};

        Solution s = new Solution();

        List<String> result = s.transform(Arrays.asList(words),Arrays.asList(parts));

        for(String r : result){
            System.out.println(r);
        }
    }
}
