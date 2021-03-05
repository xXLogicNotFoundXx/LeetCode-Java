// https://leetcode.com/problems/find-the-distance-value-between-two-arrays/submissions/

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
https://leetcode.com/playground/mF43HniQ

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
https://leetcode.com/playground/2wuwP6ft
