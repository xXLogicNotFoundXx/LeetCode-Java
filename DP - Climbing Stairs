https://leetcode.com/problems/climbing-stairs/solution/
class Solution {
    public int climbStairs(int n) {
        if(n==0)
            return 0;
        int []a = new int[n+1];
        Arrays.fill(a,0);
        a[n]=1;
        a[n-1]=1;
        for(int i=n-2;i>=0;i--){
            a[i] = a[i+1] + a[i+2]; // different ways of climbing ith step
        }
        return a[0]; 
    }   
}

