/*
https://leetcode.com/explore/featured/card/top-interview-questions-easy/127/strings/884/
There is something wrong with test case in leetcode but this should work
*/
public static int myAtoi(String str) {
    if(str==null) return 0;
    StringBuffer s = new StringBuffer(str.trim());
    if( s.length()==0 ) return 0;                             // s.isEmpty() does not exist
    if(isDigit(s.charAt(0)) || s.charAt(0)=='-' || s.charAt(0)=='+'){
        int negMulti = s.charAt(0)=='-' ? -1: 1;
        int i = s.charAt(0)=='-' || s.charAt(0)=='+' ? 1 : 0;
        long l=0;
        while( i<s.length() && isDigit(s.charAt(i))){        // here we check i is whithin range and then access the char
            l= l*10 + (s.charAt(i) - '0');
            i++;
        }
        l = l*negMulti;
        try{
            return Math.toIntExact(l);      // java 8 provides this method
        } catch(Exception e) {
            return Integer.MIN_VALUE;
        }
    }
    return 0;
}
static boolean isDigit(char c){
    return (c >= '0' && c <='9');
}
