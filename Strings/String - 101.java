/*
https://leetcode.com/problems/unique-email-addresses/
Ignore anything after + in userID
Ignore . in userID
tell how many unique email addresses
Input: ["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"]
Output: 2
*/
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
