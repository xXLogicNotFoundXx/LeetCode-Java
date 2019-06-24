/*
https://leetcode.com/problems/spiral-matrix/
Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
*/
class Solution {
    public List<Integer> spiralOrder(int[][] a) {
       
        List<Integer> list = new ArrayList<Integer>();
        if(a.length == 0) return list;

        int left=0, right=a[0].length-1;
        int top=0,  bottom=a.length-1;
        
        while(left<=right && top<=bottom){
            for(int i=left;i<=right;i++) 
                list.add(a[top][i]); 
            top++;
            for(int i=top;i<=bottom;i++) 
                list.add(a[i][right]);
            right--;
            
            if(top<=bottom){    // IMP 
                for(int i=right;i>=left;i--) 
                    list.add(a[bottom][i]);
                bottom--;
            }
            if(left<=bottom){   // IMP 
                for(int i=bottom;i>=top;i--) 
                    list.add(a[i][left]);
                left++;
            }
        }
        return list;
    }
}
