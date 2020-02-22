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
        for (int p = j; p >= 0; p--) {
            if (A[i]*A[i] > A[j]*A[j]) 
                result[p] = A[i] * A[i++];
            else 
                result[p] = A[j] * A[j--];
        }
        return result;
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
https://leetcode.com/problems/sort-colors/
Sort an array in 1 pass with values 0,1,2 only 
Input: [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
*/
class Solution {
    public void sortColors(int[] nums) {
        int left =0, right=nums.length-1;
        for(int i=0;i<=right;i++){
            if(nums[i]==0){
                nums[i] = nums[left];
                nums[left] = 0;
                left++;
            }
            if(nums[i]==2){
                nums[i] = nums[right];
                nums[right] = 2;
                right--;
                i--;            // This is one important bcz switchin 2 with right may give 0 on ith index
            }
        }
    }
}

https://leetcode.com/problems/summary-ranges/
Input:  [0,2,3,4,6,8,9]
Output: ["0","2->4","6","8->9"]
Explanation: 2,3,4 form a continuous range; 8,9 form a continuous range.
    
public class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> summary = new ArrayList<>();
        for (int i, j = 0; j < nums.length; ++j){
            i = j;
            
            // try to extend the range [nums[i], nums[j]]
            while (j + 1 < nums.length && nums[j + 1] == nums[j] + 1)
                ++j;
            
            // put the range into the list
            if (i == j)
                summary.add(nums[i] + "");
            else
                summary.add(nums[i] + "->" + nums[j]);
        }
        return summary;
    }
}


// https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
Input: [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
             Not 7-1 = 6, as selling price needs to be larger than buying price.
Example 2:
Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.

public int maxProfit(int[] prices) {
      int min = Integer.MAX_VALUE, max = 0;
      for (int price: prices) {
          min = Math.min(min, price);
          max = Math.max(max, price - min);
      }
      return max;
}


/*
https://leetcode.com/problems/find-the-duplicate-number/
Input: [1,3,4,2,2]
Output: 2
Input: [3,1,3,4,2]
Output: 3
You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
*/
class Solution {
    // if you could modify an array nlogn through sorting 
    // or 
    // you put number in their respective places  just like you rotate an array 2nd solution: https://leetcode.com/problems/rotate-array/
    // and then the array will be sorted and then you can find the number O(N)
    
    /* 
    Let's say n=10 and I select mid=5. Then I count all the numbers in the array which are less than equal mid. 
    If the there are more than 5 numbers that are less than 5, one of them has occurred more than once.
    n*log(n)
    */
    public int findDuplicate(int[] nums) {
        int low=1, high=nums.length-1;
        while(low<high){            
            int mid=(low+high)/2;
            int count=0;
            for(int i=0; i<nums.length; i++){
                if(nums[i]<=mid){
                    count++;
                }                                
            }
            
            if(count<=mid){
                low = mid+1;
            } else {
                high = mid;
            }                                    
        }
        
        return high;
    }
}
