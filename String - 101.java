/*
https://leetcode.com/problems/isomorphic-strings/
Given two strings s and t, determine if they are isomorphic.
Two strings are isomorphic if the characters in s can be replaced to get t.
Input: s = "egg", t = "add"
Output: true

String 1:              A B E A C D B
index pattern:         0 1 2 0 4 5 1
String 2:              X Y I X H K Y
index pattern:         0 1 2 0 4 5 1
Whether the two strings are isomorphic can be judged by the index patterns. 
In the above example, these two strings are isomorphic with the same index patterns.
*/

class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s == null || t == null) return false;
        if (s.length() != t.length()) return false;
        
        Map<Character, Integer> mapS = new HashMap<Character, Integer>();
        Map<Character, Integer> mapT = new HashMap<Character, Integer>();
        
        for (int i = 0; i < s.length(); i++) {
            int indexS = mapS.getOrDefault(s.charAt(i), -1);
            int indexT = mapT.getOrDefault(t.charAt(i), -1);
                        
            if (indexS != indexT) 
                return false;
            
            mapS.put(s.charAt(i), i);
            mapT.put(t.charAt(i), i);
        }
        
        return true;
    }
}

https://leetcode.com/problems/subdomain-visit-count/
When we visit a domain like "discuss.leetcode.com", we will also visit the parent domains "leetcode.com" and "com" implicitly.
Input:  ["900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"]
Output: ["901 mail.com","50 yahoo.com","900 google.mail.com","5 wiki.org","5 org","1 intel.mail.com","951 com"]
class Solution {
    public List<String> subdomainVisits(String[] cpdomains) {
        HashMap<String,Integer> map = new HashMap();
        for(String str : cpdomains){
            String [] countDomain = str.split(" ");
            int count = Integer.parseInt(countDomain[0]); // OR you can use Integer.valueOf(countDomain[0]);
            map.put(countDomain[1], map.getOrDefault(countDomain[1],0) + count);
            for(int i=0;i<countDomain[1].length()-1;i++){
                if(countDomain[1].charAt(i)=='.'){
                    String s = countDomain[1].substring(i+1);
                    map.put(s, map.getOrDefault(s,0) + count);
                }
            }
        }
        List<String> ans = new ArrayList();
        for(Map.Entry<String,Integer> e : map.entrySet()){
            String str = e.getValue() + " " + e.getKey();
            ans.add(str);
        }
        return ans;
    }
}

https://leetcode.com/problems/unique-email-addresses/
Ignore anything after + in userID
Ignore . in userID
tell how many unique email addresses 
Input: ["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"]
Output: 2

class Solution {
    public int numUniqueEmails(String[] emails) {
        Set<String> dict = new HashSet();
        for(String str : emails){
            String [] collection = str.split("@");
            if(collection.length==2){
                String [] user = collection[0].split("\\+");
                // remove .
                StringBuffer sb = new StringBuffer();
                for(String s : user[0].split("\\.")){
                    sb.append(s);
                }
                sb.append(collection[1]);
                //System.out.println(sb);
                dict.add(sb.toString());
            }
        }
        return dict.size();
    }
}
