/*
https://leetcode.com/problems/add-binary/
Input: a = "1010",
       b = "1011"
Output:   "10101"
*/
class Solution {
    public String addBinary(String a, String b) {

        int lenA = a.length()-1;
        int lenB = b.length()-1;
        int max = Math.max(lenA, lenB);

        StringBuilder sum = new StringBuilder();
        int carry = 0;

        for(int i = 0; i <= max; i++){
            int add = getBit(a,lenA-i) + getBit(b, lenB-i) + carry;
            sum.append(add % 2);
            carry = add / 2;
        }

        if(carry == 1)
            sum.append("1");

        return sum.reverse().toString();
    }

    public int getBit(String s, int index){
        if(index < 0 )
            return 0;

        return s.charAt(index) == '0' ? 0 : 1;
    }
}
