/*
https://leetcode.com/problems/median-of-two-sorted-arrays/submissions/
*/
// O(N)
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        
        int totalLen = nums1.length + nums2.length;
        boolean isEvenLen = totalLen%2==0;
        
        Set<Integer> indexes = new HashSet<>();
        indexes.add(totalLen/2);
        if(isEvenLen) {
            indexes.add(totalLen/2 - 1);
        }
        
        int i=0, j=0, sum=0;
        
        while(i<nums1.length || j<nums2.length) {
            
            int curI = i < nums1.length ? nums1[i] : Integer.MAX_VALUE;
            int curJ = j < nums2.length ? nums2[j] : Integer.MAX_VALUE;
            
            int currNum = curI<curJ ? curI : curJ;
            i=curI<curJ ? i+1 : i;
            j=curI>=curJ ? j+1 : j;   // see we have ">="
            // if we find same number someone has to be incremented 
            
            if(indexes.contains(i + j -1)) {
                sum += currNum;
            }
        }
        
        return isEvenLen ? sum/2.0 : sum;
    }
}
