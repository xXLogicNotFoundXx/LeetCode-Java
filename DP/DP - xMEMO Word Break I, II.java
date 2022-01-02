/*
Medoum - VIMP
Facebook37 Amazon28 Microsoft8 Uber7 so many others


https://leetcode.com/problems/word-break/
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
determine if s can be segmented into a space-separated sequence of one or more dictionary words.
Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true

Approach 1 Backtracking :
The naive approach to solve this problem is to use recursion and backtracking.
Given an array of length n, there are n-1 ways/intervals to partition it into two parts.
Each interval has two choices - split or not.

In the worse case, we will have to check all possibilities, which becomes O(2^(n-1)) -> O(2^n). Let this sink in.
Time complexity : O(2^n) Consider the worst case where ss = "aaaaaaa" and every prefix of s is present in the dictionary of words
then the recursion tree can grow upto 2^n.
Space complexity : O(n). The depth of the recursion tree can go upto n.

Approach 2: Recursion with memoization :
We solve every substring only once bcz of memoization and there are n^2 possible substrings.
Time complexity : O(n^3). n^2 possible substrings and O(n) for each substring() operation.
Space complexity : O(n). The depth of the recursion tree can go upto n.

Total number of substrings of all lengths from 1 to n =
n + (n-1) + (n-2) + (n-3) + … 2 + 1
= n * (n + 1)/2
= n^2
*/
class Solution {

    public boolean wordBreak(String s, List<String> wordDict) {
        if(wordDict==null || wordDict.size() == 0)
            return false;
        if(s==null || s.isEmpty())
            return true;

        Boolean []memo = new Boolean[s.length()];
                                  // Good that we put it in set  <- this
        return wordBreakHelper(s, new HashSet(wordDict), 0, memo);
    }

    public boolean wordBreakHelper(String s, Set<String> wordDict, int start, Boolean[] memo) {

        if (start == s.length())
            return true;

        if (memo[start] != null)
            return memo[start];

        for (int j = start + 1; j <= s.length(); j++) { // Important  <= s.length()
            String subStr = s.substring(start, j); // substring second index is upperbound (non-including j's value)
            if(wordDict.contains(subStr)){
                if(wordBreakHelper(s, wordDict, j, memo)) {
                    return memo[start] = true;
                }
            }
        }
        return memo[start] = false;
    }
}
/*
Approach 3: Using Breadth-First-Search
Visualize the string as a tree where each node represents the prefix upto index endend.
     /\
  0-j  0-k... are the 1st level nodes.
   /    \
j-m     k-l . are the 2nd level nodes.
Time complexity : O(n^2) For every starting index, the search can continue till the end of the given string.
Space complexity : O(n). Queue of atmost n size is needed.

Approach 4: Using Dynamic Programming
Almost like MEMO but iterative
Time complexity : O(n^2)Two loops are their to fill dp array.
Space complexity : O(n). Length of pp array is n+1.
*/
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet=new HashSet(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

// PROBLEM - II
/*
https://leetcode.com/problems/word-break-II/
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
add spaces in s to construct a sentence where each word is a valid dictionary word.
Return all such possible sentences.
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
Output:
  ["cats and dog","cat sand dog"]

Time complexity  : Substring of a string = n^2. O(n) for each substring() operation. The creation of list from subans takes n time.
                  O(N^2*(N+N))
                  O(n^3)
Space complexity : O(n^3)The depth of the recursion tree can go up to n and
                    each activation record can contains a string list of size n^2.

Can we use same word from a dictionary multiple times?
*/
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        if(wordDict==null || wordDict.size() == 0)
            return wordDict;
        if(s==null || s.isEmpty())
            return wordDict;

        HashMap<Integer,List<String>> map = new HashMap<>();

        return wordBreakHelper(s, new HashSet(wordDict), 0, map);
    }

    public List<String> wordBreakHelper(String s, Set<String> wordDict, int start, HashMap<Integer,List<String>> map) {

        List<String> res = new ArrayList<>();

        if (start == s.length()) {
            res.add("");
            return res;
        }

        if (map.containsKey(start))
            return map.get(start);

        for (int j = start + 1; j <= s.length(); j++) { // Important  <= 

            String subStr = s.substring(start, j); // substring second index is upperbound (non-including j's value)

            if(wordDict.contains(subStr)){

                List<String> subResults = wordBreakHelper(s, wordDict, j, map);

                for(String sr : subResults){
                    if(sr.isEmpty())   // if it is coming from end we dont need space at the end
                        res.add(subStr);
                    else
                        res.add(subStr + " " + sr);
                }
            }
        }

        map.put(start,res);
        return map.get(start);
    }
}
