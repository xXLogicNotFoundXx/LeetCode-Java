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
    public int threeSumMulti1(int[] A, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int mod = 1000000007;
        int res = 0;
        for (int i = 0; i < A.length; i++) {
            // A[i] must be considered for result before we calculate all combination sum and we dont 
            // want to consider that sum bcz that includesA[i]
            res = (res + map.getOrDefault(target - A[i], 0)) % mod;
            for (int j = 0; j < i; j++) {
                int temp = A[i] + A[j];
                map.put(temp, map.getOrDefault(temp, 0) + 1);
            }
        }
        return res;
    }
    
/*    Runtime 39 ms. This is same as 3SUm combinations.
Time complexity: O(N^2)
Space complexity: O(1)
You might think that the sorting of numbers will mess up the order but it really doesnt matter. 
For any expression a + b + c = T, you can always change the order of the arguments so that their indices conform to the requirement.
For example, a + b + c = T can be rewritten to a + c + b = T if index of b is greater than index of c.
*/   
    public int threeSumMulti(int[] A, int target) {
        int mod = 1000000007;
        int ans = 0;
        Arrays.sort(A);
        for(int i = 0; i < A.length-2; i++) {
            long cnt = 0;
            int l = i+1, r = A.length-1;
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
                        // If A[l...r] are all equal, then there are combinations of C(n, 2) combinations that meet the requirement.
                        // let this sink in so we did n*n-1/2   ... n-2! is cancelled out look at the formula.
                        // nCr = n!/(r!*(n-r)!) 
                        cnt += (n * (n-1) / 2) % mod;
                        break;
                    }
                }
            }
            ans = (int) (ans + cnt) % mod;
        }
        return ans;
    }
}

/*
https://leetcode.com/problems/3sum-smaller/
Given an integer array A, and an integer target, return the number of tuples i, j, k  
such that i < j < k and A[i] + A[j] + A[k] < target.
Complexity analysis
Time complexity : O(n2). 
The twoSumSmaller function takes O(n) time because both left and right traverse at most n steps. 
Therefore, the overall time complexity is O(n2).
Space complexity : O(1).
*/

    public int threeSumSmaller(int[] nums, int target) { 
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            sum += twoSumSmaller(nums, i + 1, target - nums[i]); 
        }
        return sum; 
    }

    private int twoSumSmaller(int[] nums, int startIndex, int target) { 
        int sum = 0;
        int left = startIndex;
        int right = nums.length - 1; 
        while (left < right) {
            if (nums[left] + nums[right] < target) { 
                sum += right - left;
                left++;
            } else {
                right--;
            } 
        }
        return sum; 
    }
