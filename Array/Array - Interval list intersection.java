/*
https://leetcode.com/problems/interval-list-intersections/
Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.
Input: 
A = [[0,2],[5,10],[13,23],[24,25]], 
B = [[1,5],[8,12],[15,24],[25,26]]
Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
Reminder: The inputs and the desired output are lists of Interval objects, and not arrays or lists.
*/
class Solution {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> ans = new ArrayList();
        int i = 0, j = 0;

        while (i < A.length && j < B.length) {
            int lo = Math.max(A[i][0], B[j][0]);
            int hi = Math.min(A[i][1], B[j][1]);
            if(lo<=hi)
                ans.add(new int[]{lo,hi});
            
            if(A[i][1]<B[j][1])
                i++;
            else
                j++;
        }
        
        int [][]array = new int[ans.size()][2];
        i=0;
        for(int [] oneAns: ans){
            array[i][0] = oneAns[0];
            array[i++][1] = oneAns[1]; 
        }
        return array;
        // return ans.toArray(new int[ans.size()][]);   => Smart way to do it
    }
}
