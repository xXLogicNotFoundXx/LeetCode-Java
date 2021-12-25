// Return true if and only if the given array ascending or descending
class Solution {
    public boolean isMonotonic(int[] A) {
        boolean ascending = true;
        boolean descending = true;
        for(int j=1;j<A.length;j++){
            ascending = ascending && (A[j-1]<=A[j]);
            descending = descending && (A[j-1]>=A[j]);
        }
        return ascending || descending;
    }
}

/*
https://leetcode.com/problems/squares-of-a-sorted-array/
977. Squares of a Sorted Array
Given an array of integers A sorted in non-decreasing order, return an array of the squares of each number, also in sorted non-decreasing order.
Input: [-4,-1,0,3,10]
Output: [0,1,9,16,100]
Input: [-7,-3,2,3,11]
Output: [4,9,9,49,121]
*/
class Solution {
    public int[] sortedSquares(int[] A) {
        if(A==null || A.length ==0)
            return A;

        int[] result = new int[A.length];
        int i = 0, j = A.length - 1;
        for (int p = A.length - 1; p >= 0; p--) {
            if (A[i]*A[i] > A[j]*A[j])
                result[p] = A[i] * A[i++];
            else
                result[p] = A[j] * A[j--];
        }
        return result;
    }
}

/*
https://leetcode.com/problems/rotate-array/

class Solution {
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length-1);  // reverse the whole array
        reverse(nums, 0, k-1);  // reverse the first part
        reverse(nums, k, nums.length-1);  // reverse the second part
    }
    public void reverse(int[] nums, int l, int r) {
        while (l < r) {
            int tmp = nums[l];
            nums[l] = nums[r];
            nums[r] = tmp;
            l++;
            r--;
        }
    }
}
*/

// Recursively keep one element to next and next postion it its right position
// ... This is what I though but couldnt build the end condition.
// See the end condition doesnt revovle around K at all
// its untill you process all N element everytime you move element count++ till N
public class Solution {
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        int count = 0;
        for (int start = 0; count < nums.length; start++) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % nums.length;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
                count++;
            } while (start != current);
        }
    }
}

/*
https://leetcode.com/problems/intersection-of-two-arrays-ii/
// This question is asking mutual numbers in two array ..
// if number is two times in both array then output should contains that number two times
Input: nums1 = [1,2,1,2], nums2 = [2,2]
Output: [2,2]
Example 2:
Input: nums1 = [4,5,9,3,9], nums2 = [9,4,9,8,9,4]
Output: [4,9,9]
*/
class Solution {

    // for num1 use map of number and count ....
    // then for num2 add number to list till count in map becomes 0 .. then convert list to an array.
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        List<Integer> list = new ArrayList<Integer>();
        for(int i=0;i<nums1.length;i++){
            map.put(nums1[i],map.getOrDefault(nums1[i],0)+1);
        }
        for(int i=0;i<nums2.length;i++){
            if(map.containsKey(nums2[i]) && map.get(nums2[i]) > 0){
                map.put(nums2[i],map.get(nums2[i])-1);
                list.add(nums2[i]);
            }
        }
        int [] result = new int[list.size()];
        for(int i=0;i<list.size();i++){
            result[i] = list.get(i);
        }
        return result;
    }
}

/*
https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
Input: [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
             Not 7-1 = 6, as selling price needs to be larger than buying price.
Example 2:
Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
*/
public int maxProfit(int[] prices) {
      int min = Integer.MAX_VALUE, max = 0;
      for (int price: prices) {
          min = Math.min(min, price);
          max = Math.max(max, price - min);
      }
      return max;
}
