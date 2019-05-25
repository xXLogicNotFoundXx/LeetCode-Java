/*
https://leetcode.com/problems/strobogrammatic-number-ii/
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
Find all strobogrammatic numbers that are of length = n.
Example:
Input:  n = 1
Output: ["0","1","8"]
Input:  n = 2
Output: ["11","69","88","96"]
Input:  n = 3
Output: ["101","111","181","808","818","888","609","619","689","906","916","986"]

*/

class Solution {
    // RunTime = n*5^n (5-nary tree so 5^n nodes in the three and then concatinations of max length n)
    public List<String> findStrobogrammatic(int n) {
        List<String> ans = new ArrayList<String>();
        findStrobogrammatic(0,n-1,ans,"","");
        return ans;
    }
    
    void findStrobogrammatic(int l, int r, List<String> ans, String strLeft, String strRight){
        if(l>r){
            ans.add(strLeft+strRight);
            return;
        }
        
        if(l==r){ // 6 & 9 are not considered in mid point 
            findStrobogrammatic(l+1,r-1,ans,strLeft+"0",strRight);
            findStrobogrammatic(l+1,r-1,ans,strLeft+"1",strRight);
            findStrobogrammatic(l+1,r-1,ans,strLeft+"8",strRight);
            return;
        }
        
        if(l!=0){ // we cant start number with 0 somethign like 08880
            findStrobogrammatic(l+1,r-1,ans,strLeft+"0", "0"+strRight);
        }
        
        findStrobogrammatic(l+1,r-1,ans,strLeft+"1", "1"+strRight);
        findStrobogrammatic(l+1,r-1,ans,strLeft+"8", "8"+strRight);
        findStrobogrammatic(l+1,r-1,ans,strLeft+"6", "9"+strRight);
        findStrobogrammatic(l+1,r-1,ans,strLeft+"9", "6"+strRight);
    }
}
