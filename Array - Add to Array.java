https://leetcode.com/problems/add-to-array-form-of-integer/submissions/
[1,2,0,0] + 34 => [1,2,3,4]
[1,1,1] + 9 => [1,0,0,0]

class Solution {
    public List<Integer> addToArrayForm(int[] A, int K) {
        for(int i = A.length-1; i>=0 && K!=0; i--){
            int temp = A[i] + K;
            A[i] = temp % 10;
            K = temp / 10;
        }
        List<Integer> B = new ArrayList<Integer>();
        while(K!=0){
            B.add(0,K%10);
            K = K/10;
        }
       
        for(int i : A)
            B.add(i);
        
        return B;
    }
}