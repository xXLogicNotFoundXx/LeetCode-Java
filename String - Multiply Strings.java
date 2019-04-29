/*
https://leetcode.com/problems/multiply-strings/
It is same as we do multiplication the smart thing is to realize the indexes to store multiplication of each pair.
Eacha pair means it is gonna be O(n^2)
Also, realise that result size will be length1+length2.
So the indexes are : 
num1[i] * num2[j] will be placed at indices [i+j, i+j+1]
*/
class Solution {
    public String multiply(String num1, String num2) {
        int m = num1.length();
        int n = num2.length();
        int []res = new int[m+n];
        for(int i=m-1;i>=0;i--){
            for(int j=n-1;j>=0;j--){
                int right = i+j+1;
                int left = i+j;
                
                int mul = (num1.charAt(i)-'0') *(num2.charAt(j)-'0');
                int sum = res[right] + mul;
                
                res[right] = sum%10;
                res[left] += sum/10;
            }
        }
        StringBuffer sb = new StringBuffer();
        for(int r : res){
            if(sb.length()==0 && r==0)
                continue;
            sb.append(r);
        }
        
        return sb.length()==0 ? "0" : sb.toString();
    }
}
