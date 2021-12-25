// https://leetcode.com/problems/reverse-integer/
class Solution {
    /* Note :  
        123  % 10 = 3  but 
        -123 % 10 = -3    
        However, 2nd operator sigh doesnt matter so 
        123 % 10  = 3 and 
        123 % -10 = 3
    */
    public int reverse1(int x) {
        long rev= 0;
        while( x != 0){
            rev= rev*10 + x % 10;
            x= x/10;
            if( rev > Integer.MAX_VALUE || rev < Integer.MIN_VALUE)
                return 0;
        }
        return (int) rev;
    }
    
    public int reverse2(int x) {
        
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
    
    // Without using long 
    public int reverse(int x) {
        int prevRes = 0 , res= 0;
        while( x != 0){
            res= res*10 + x % 10;
            x= x/10;
            
            if(res/10 != prevRes) // if res overflows then it is not gonna be equal to prevRes 
                return 0;
            
            prevRes = res;
        }
        
        return res;
    }
    
}
