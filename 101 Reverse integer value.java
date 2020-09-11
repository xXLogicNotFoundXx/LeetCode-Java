// https://leetcode.com/problems/reverse-integer/
class Solution {
    public int reverse1(int x) {
        
        StringBuffer str = new StringBuffer();
        str.append(x);
        str.reverse();
        
        int neg=1;
        if(str.charAt(str.length()-1) == '-'){
            str.deleteCharAt(str.length()-1);
            neg =-1;
        }
        
        long l = Long.valueOf(str.toString()) * neg;
        if(l > Integer.MAX_VALUE || l < Integer.MIN_VALUE)
            return 0;
            
        return Integer.valueOf(str.toString())*neg;
    }
}
