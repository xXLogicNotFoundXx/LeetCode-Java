
https://leetcode.com/problems/unique-binary-search-trees/
Follow up : https://leetcode.com/problems/unique-binary-search-trees-ii/

//https://www.youtube.com/watch?time_continue=12&v=GgP75HAvrlY 

class Solution {
    public int numTrees(int n) {
        
        int []numTree = new int[n+1];        
        numTree[0] =1;
        numTree[1] =1;
        for(int i=2;i<=n;i++){
            for(int j=1;j<=i;j++){
                numTree[i] += numTree[j-1] * numTree[i-j];
            }
        }
        return numTree[n];
    }
}
