/*
Medium
Karat44 Indeed27 Wayfair12 Palantir Technologies7 Intuit5 Paypal2 VMware2 Grab2
https://leetcode.com/problems/subdomain-visit-count/
When we visit a domain like "discuss.leetcode.com", we will also visit the parent domains "leetcode.com" and "com" implicitly.
Input:  ["900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"]
Output: ["901 mail.com","50 yahoo.com","900 google.mail.com","5 wiki.org","5 org","1 intel.mail.com","951 com"]

You may return the answer in any order.
*/
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
