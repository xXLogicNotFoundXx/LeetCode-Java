/* Premium: 
Longest Line of Consecutive One in Matrix
https://leetcode.com/problems/longest-line-of-consecutive-one-in-matrix/
Given a 01 matrix M, find the longest line of consecutive one in the matrix. The line could be horizontal, vertical, diagonal or anti-diagonal.
Example:
 [0,1,1,0]
 [0,1,1,0]
 [0,0,0,1]
Output: 3
*/
class Solution {
    public int longestLine(int[][] M) {
        if(M==null || M.length ==0)
            return 0;
        int []row = new int[M.length];
        int []col = new int[M[0].length];
        int []slash = new int[M.length + M[0].length]; // calculation of index will be i+j let that sink in 
        // i+j will be 0-M.length + M[0].length
        int []backslash = new int[M.length + M[0].length]; // this one is tough j-i+M.length let that sink in  
        // -i+M.length this will never cross 0-M.length and then j is alwasy plus so that adds up to M.length + M[0].length
        int max=0;
        for(int i=0;i<M.length;i++){
            for(int j=0;j<M[0].length;j++){
                if(M[i][j] == 1){
                    row[i]++;
                    col[j]++;
                    slash[i+j]++;
                    backslash[j-i+M.length]++;
                    max = Math.max(max,row[i]);
                    max = Math.max(max,col[j]);
                    max = Math.max(max,slash[i+j]);
                    max = Math.max(max,backslash[j-i+M.length]); // -i+M.length will never be negative ( i can go to max M.length)
                }else{
                    row[i] = 0;
                    col[j] = 0;
                    slash[i+j]  = 0;
                    backslash[j-i+M.length]  = 0;
                }
            }
        }
        return max;
    }
}


/*
Similar problem where you have to calculate unique number for antiDiagonal
1329. Sort the Matrix Diagonally
https://leetcode.com/problems/sort-the-matrix-diagonally/
    [3,3,1,1]       [1,1,1,1]
    [2,2,1,2]   =>  [1,2,2,2]
    [1,1,1,2]       [1,2,3,3]
*/
class Solution {

    // Time O(MNlogD), where D is the length of diagonal with D = min(M,N).
    // Space O(MN)
    public int[][] diagonalSort(int[][] mat) {
        int m = mat.length, n= mat[0].length;
        PriorityQueue<Integer>[] pArray = new PriorityQueue[m+n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(pArray[j-i+m]==null) // -i+m will never be negative ( i can go to max m)
                    pArray[j-i+m] = new PriorityQueue<Integer>();
                pArray[j-i+m].add(mat[i][j]);
            }
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                mat[i][j]= pArray[j-i+m].poll();
            }
        }
        return mat;
    }
    
    // Solution 2 
    // Here if we are not storing in an array we dont have to worry about negative index 
    // so j-i+mat.length is not required 
    // i-j would give same number for given antidiagonal elements with index i,j
     public int[][] diagonalSort2(int[][] A) {
        int m = A.length, n = A[0].length;
        HashMap<Integer, PriorityQueue<Integer>> d = new HashMap<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                d.putIfAbsent(i - j, new PriorityQueue<>());    // nice putIfAbsent
                d.get(i - j).add(A[i][j]);
            }
        }
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                A[i][j] = d.get(i - j).poll();
        return A;
    }
}
