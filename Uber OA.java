/*
Given two integer arrays a and b, and an integer value d, your task is to find the comparator value between these arrays.

The comparator value is defined as the number of elements x ∈ a such that there are no elements y ∈ b where |x - y| ≤ d.
In other words, it's the number of elements in a that are more than d away from any element of b.

*/
https://leetcode.com/problems/find-the-distance-value-between-two-arrays/submissions/



// Product - sum 
public int subtractProductAndSum(int n) {
	int product=1, sum=0;

	while(n>0){
	    int i = n%10;
	    n = n/10;
	    product *= i;
	    sum += i;
	}

	return product-sum;
}


/*
Easy:
Given an array A return an output array B of size [A.length - 2] where B[i] = 1 if sides A[i],A[i+1] and A[i+2] form a triangle. Otherwise, set B[i] = 0.
ex. A = [1, 2, 2, 5, 5, 4] should return B = [1,0,0,1]
*/
// The sum of the length of two sides of a triangle is always greater than the length of the third side.
for(int i=0;i<A.length-2;i++){
  int a=A[i],b=A[i+1]c=A[i+2];
  
  if(a+b>c && b+c>a && c+a>b)
    b[i]=1;
  else
    b[i]=0;
}

/*
Given two strings a and b, merge the strings so that the letters are added in alternating order starting with string a. If one string is longer than the other, then append the letters to the end of the merged string.
ex. "abcd", "efghi" -> "aebfcgdhi"
ex. "", "abcd" -> "abcd"
ex. "abcdefg", "zxy" -> "azbxycdefg"
*/

int i=0;
int j=0;
while(i<=str.length()-1 || j<=str2.length()-1);
  char a = i<=str.length()-1 ? str.chat(i) : "";
  char b = j<=str2.length()-1 ? str2.chat(j) : "";
  append(a);
  append(b);
  i++;j++;


/*
Given a string s, return the longest and lexicographically smallest palindromic string that can be formed from the characters.
ex. "abbaa" -> "abba"
ex. "adeadeadevue" -> "adeeaeeda"
*/
public class Main {
    public static String lexLongestPalimdrome(String str){
        if(str==null || str.isEmpty())
            return "";
        
        int[] count = new int[26];
        for(char ch : str.toCharArray())
            count[ch-'a']++;
        
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<count.length;i++){
            if(count[i]==0 || count[i]==1)
                continue;
            
            int mid = sb.length()/2;
            int c = count[i];
            char ch = (char) ((int)'a'+i);
            String s = Character.toString(ch);
            if(c%2==0){
                sb.insert(mid,s.repeat(c));
            } else {
                sb.insert(mid,s.repeat(c-1));
            }
        }
        
        for(int i=0;i<count.length;i++){
            if(count[i]%2==1){
                int mid = sb.length()/2; 
                char ch = (char) ((int)'a'+i);
                sb.insert(mid,ch);
                break;
            }
        }
            
        return sb.toString();
    }
    public static void main(String[] args) {
        System.out.println(lexLongestPalimdrome(""));
        System.out.println(lexLongestPalimdrome("adeadeadevue"));
        System.out.println(lexLongestPalimdrome("adskassda"));
        System.out.println(lexLongestPalimdrome("aahslqjjd"));
    }
}

/*
Given a string S, Count number of ways of splitting S into 3 non-empty a,b,c such 
that a+b, b+c, c+a are all different.

ex. xzxzx OP: 5
   x, z, xzx
	x, zx, zx
	xz, x, zx
	xz, xz, x
	xzx, z, x

// This is different NASTY problem 
https://leetcode.com/problems/number-of-ways-to-split-a-string/
https://leetcode.com/problems/number-of-ways-to-split-a-string/discuss/830455/JavaPython-3-Multiplication-of-the-ways-of-1st-and-2nd-cuts-w-explanation-and-analysis./685436
*/
// this is easy one if they are not similar like nasty problem one 
public class Main {
    public static int numberOfThreeWayCuts(String S){
        if(S==null || S.length()<3)
            return 0;
        
        int count=0;
        for(int i = 1; i<S.length()-1;i++){
            for(int j = i+1; j<S.length();j++){
                String a = S.substring(0,i);
                String b = S.substring(i,j);
                String c = S.substring(j);
                
                System.out.println(a +" "+b+" "+c);
                if(!((a+b).equals(b+c) || (b+c).equals(c+a) || (a+b).equals(c+a)))
                count++;
            }
        }
        return count;
    }
    
    public static void main(String[] args) {
        System.out.println(numberOfThreeWayCuts("xzxzx"));
        System.out.println(numberOfThreeWayCuts("XXX"));
    }
}
