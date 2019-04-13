/*
763. Partition Labels
https://leetcode.com/problems/partition-labels/
A string S of lowercase letters is given. We want to partition this string into as many parts as possible
so that each letter appears in at most one part, and return a list of integers representing the size of these parts.
Input: S = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.

2 PASS
*/
class Solution {
    public List<Integer> partitionLabels(String S) {
        int []lastPos = new int[26];
        List<Integer> ans = new ArrayList<>();
        
        for(int i=0;i<S.length();i++)
            lastPos[S.charAt(i)-'a']=i;
        
        int start=0;
        int partition = 0;
        for(int i=0;i<S.length();i++){
            partition = Math.max(partition,lastPos[S.charAt(i)-'a']);
            if(partition==i){
                ans.add(partition-start+1);
                start = partition+1;
            }
        }
        return ans;
    }
}
