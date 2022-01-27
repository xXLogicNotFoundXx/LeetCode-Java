/**
https://leetcode.com/problems/read-n-characters-given-read4/
int read4(char[] buf); API is given return number of char read;

Input: file = "leetcode", n = 5
Output: 5 & buf should contain "leetc"

Input: file = "abc", n = 5
Output: 3 & buf should contain "abc"
 */
public class Solution extends Reader4 {
    public int read(char[] buf, int n) {
        if(n<=0) return 0;
        boolean eof = false;
        char[] b = new char[4];
        int total=0;
        while(eof!=true){
            int read = read4(b);
            eof = (read < 4) || (total+read > n);
            int i=0;
            while(total<n && read>0){
                buf[total++] = b[i++];
                read--;
            }
        }
        return total;
    }
}
