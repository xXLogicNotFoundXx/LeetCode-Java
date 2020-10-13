/*
https://leetcode.com/problems/3sum-with-multiplicity/
Given an integer array A, and an integer target, return the number of tuples i, j, k  
such that i < j < k and A[i] + A[j] + A[k] == target.

As the answer can be very large, return it modulo 10^9 + 7.

Time : O(n^2)
Space : O(n^2)    
*/

class Solution {
    // This one is N^2 takes  599 ms 
    public int threeSumMulti(int[] A, int target) {
        HashMap<Integer, Integer> count = new HashMap<>();
        int n = A.length;
        long result = 0;
        
        for(int i = 0; i + 1 < n; i++){
            for(int j = i + 1; j < n; j++){
                if(count.containsKey(target - A[i] - A[j])){
                    result += ( 1L * count.get(target - A[i] - A[j]) ); 
                    //System.out.println(result);
                }
            }
            count.put(A[i], count.getOrDefault(A[i], 0) + 1);
        }
        
        // System.out.println(result);
        return (int) (result%(1000000000 + 7));
    }
}

/*    Runtime 39 ms. This is same as 3SUM calculating combinations.
Time complexity: O(N^2)
Space complexity: O(1)
NOTE: 
You would think sorting will mess up the order of i,j,k indexes but it really doesnt matter.
This is USELESS detail bcz i,j,k can be reorder to any combination and it doesnt matter.
A[i] + A[j] + A[k] can be A[k] + A[i] + A[j]
Moreover they are not even asking to return tuples of i,j,k  
*/   
    public int threeSumMulti(int[] A, int target) {
        int mod = 1000000007;
        int ans = 0;
        Arrays.sort(A);
        for(int i = 0; i < A.length-2; i++) {
            
            long cnt = 0;
            int l = i+1
            int r = A.length-1;
            
            while(l < r) {
                
                if(A[i] + A[l] + A[r] > target) 
                    r--;
                else if(A[i] + A[l] + A[r] < target) 
                    l++;
                else {
                    if(A[l] != A[r]) {
                        long cntL = 1, cntR = 1;
                        while(l+1 < r && A[l] == A[l+1]) { 
                            cntL++; l++; 
                        }
                        while(l < r-1 && A[r] == A[r-1]) { 
                            cntR++; r--; 
                        }
                        cnt += (cntL * cntR) % mod;
                        l++;
                        r--;
                    } else {
                        long n = r-l+1;
                        // If A[l...r] are all equal, then there are combinations of nCr combinations that meet the requirement.
                        // nCr = n!/(r!*(n-r)!)  where n is total items and we choose r items. 
                        // let this sink in so we did n*n-1/2   ... n-2! is cancelled out look at the formula.
                        cnt += (n * (n-1) / 2) % mod;
                        break; // there is nothing to calculate further 
                    }
                }
            }
            
            ans = (int) (ans + cnt) % mod;
        }   
        return ans;
    }
}
