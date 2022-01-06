/*
MEDIUM
https://leetcode.com/problems/palindrome-permutation-ii/

Given a string s, return all the palindromic permutations (without duplicates) of it.
Return an empty list if no palindromic permutation could be form.

How would you know if the palindrom is possible?

We only need to generate the first part of palindrome string,
and the remaining part will be a middle character with the reverse of first part.


Time - O( (n/2)! )
Space - O(n) - depth of recursion is n/2.
*/
class Solution {
    public List<String> generatePalindromes(String s) {
        Map<Character,Integer>  map = new HashMap<>();
        Set<Character> set = new HashSet<>();
        List<String> list = new LinkedList<>();

        if(s==null || s.isEmpty())
            return list;
        for(char c : s.toCharArray()){
            map.put(c,map.getOrDefault(c,0)+1);
            if(map.get(c)%2==1)
                set.add(c);
            else
                set.remove(c);
        }

        if( s.length()%2==1 && set.size()!=1 ||         // IMP
            s.length()%2==0 && set.size()!=0)
            return list;

        int resLength = s.length()/2;
        String mid = "";
        if(set.size()==1){
            char c = set.iterator().next();     // <- THIS
            mid = ""+c;
            map.put(c,map.get(c)-1);
        }

        generatePalindromesHelper(map,mid,new StringBuffer(),resLength,list);
        return list;
    }

    void generatePalindromesHelper(Map<Character,Integer>  map, String mid, StringBuffer sb, int resLength, List<String> list){
        if(sb.length()==resLength){
            String ans = sb.toString()+ mid + sb.reverse().toString();
            list.add(ans);

            sb.reverse(); // undo the reverse   ^^ we reversed it here
            return;
        }

        for(char ch : map.keySet()){
            if(map.get(ch)>1){
                map.put(ch, map.get(ch)-2); // IMP -2
                sb.append(ch);

                generatePalindromesHelper(map,mid,sb,resLength,list);

                sb.deleteCharAt(sb.length()-1);
                map.put(ch, map.get(ch)+2); // IMP +2
            }
        }
    }
}
