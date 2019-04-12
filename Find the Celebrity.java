/* 
Find the Celebrity
https://leetcode.com/problems/find-the-celebrity/
Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity. The definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of them.
The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */
//if a knows b, we know for sure, a is not a cele, if a does not know b, we know for sure b is not cele. 
public class Solution extends Relation {
    public int findCelebrity(int n) {
        // base case
        if (n <= 0) return -1;
        if (n == 1) return 0;

        Stack<Integer> stack = new Stack<>();

        // put all people to the stack
        for (int i = 0; i < n; i++) stack.push(i);

        int a = 0, b = 0;

        while (stack.size() > 1) {
            a = stack.pop(); b = stack.pop();

            if (knows(a, b)) 
                // a knows b, so a is not the celebrity, but b may be
                stack.push(b);
            else 
                // a doesn't know b, so b is not the celebrity, but a may be
                stack.push(a);
        }

        // double check the potential celebrity
        int c = stack.pop();

        for (int i = 0; i < n; i++)
            // c should not know anyone else
            if (i != c && (knows(c, i) || !knows(i, c)))
                return -1;

        return c;
    }
}
// celebrity knows himself and all n knows him 
// N^2 very simple solution
/*
public class Solution extends Relation {
    public int findCelebrity(int n) {
        int []know = new int[n];
        int []popular = new int[n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(knows(i,j)){
                    popular[j]++;
                    know[i]++;
                }
            }
        }
        for(int i=0;i<n;i++){
            if(know[i]==1 && popular[i]==n) //celebrity knows himself and all n knows him 
                return i;
        }
        return -1;
    }
}
*/
