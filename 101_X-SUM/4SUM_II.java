/* 
https://leetcode.com/problems/4sum-ii/
Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) 
there are such that A[i] + B[j] + C[k] + D[l] is zero.
O(N^2)
*/
class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int num1 : A) {
            for (int num2 : B) {
                int sum = num1 + num2;
                map.put(sum, map.getOrDefault(sum, 0)+1);
            }
        }
        for (int num3 : C) {
            for (int num4 : D) {
                int sum = num3 + num4;
                count += map.getOrDefault(-sum, 0);
            }
        }
        
        return count;
    } 
}
