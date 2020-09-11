class Solution {
    // https://leetcode.com/problems/pascals-triangle/
    // 1
    // 1 1
    // 1 2 1
    // 1 3 3 1
    // 1 4 6 4 1
    // In this we can see it is always 1 when j==0 or j==i
    // when j>i then we compute the next row 
    public List<List<Integer>> generate(int numRows) {
        
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        
        for(int i=0;i<numRows;i++){
            List<Integer> oneRow = new ArrayList<Integer>();
            
            for(int j=0; j<=i;j++){
                
                if(j==0 || j==i){
                    oneRow.add(1);
                }else{
                    List<Integer> lastRow = ans.get(i-1); 
                    oneRow.add(lastRow.get(j-1) + lastRow.get(j));
                }
            }
            ans.add(oneRow);
        }
        return ans;
    }

// https://leetcode.com/problems/pascals-triangle-ii/
// Input: 3
// Output: [1,3,3,1]  index start at 0  and only constant K space 
    public List<Integer> getRow(int rowIndex) {
        List<Integer> ans = new ArrayList<Integer>();

        for(int i=0;i<=rowIndex;i++){
            List<Integer> tempRow = new ArrayList<Integer>();
            for(int j=0; j<=i;j++){
                
                if(j==0 || j==i)
                    tempRow.add(1);
                else{
                    tempRow.add(ans.get(j-1) + ans.get(j));
                }
                
            }
            ans=tempRow;
        }

        return ans; 
    }
}
