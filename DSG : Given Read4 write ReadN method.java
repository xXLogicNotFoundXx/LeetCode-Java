/*
https://leetcode.com/problems/read-n-characters-given-read4-ii-call-multiple-times/
int read4(char[] buf) - reads 4 consecutive characters from the file and returns the number of actual characters read.
given method read4, implement a method read(char[] buf, int n) to read n characters. Your method read may be called multiple times.
 * The read4 API is defined in the parent class Reader4.
 *     int read4(char[] buf); 
 */
public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    
    int bufLen =0;
    int bp =0;
    char[] intenalBuffer = new char[4];
    public int read(char[] buf, int n) {
        int i=0;
        while(i<n){
            if(bp == bufLen){
                // read new 4 chars 
                bp = 0;
                bufLen = read4(intenalBuffer);
                if(bufLen == 0) // no chars read
                    break;
            }
            buf[i++] = intenalBuffer[bp++];
        }
        return i;
    }
}
