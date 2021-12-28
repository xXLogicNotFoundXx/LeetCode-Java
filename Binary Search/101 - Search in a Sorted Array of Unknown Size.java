/*
medium - in last year  msft-2 FB-2
702. Search in a Sorted Array of Unknown Size
https://leetcode.com/problems/search-in-a-sorted-array-of-unknown-size/

Given an integer array sorted in ascending order, write a function to search target in nums.
If target exists, then return its index, otherwise return -1. However, the array size is unknown to you.
You may only access the array using an ArrayReader interface,
where ArrayReader.get(k) returns the element of the array at index k (0-indexed).

You may assume all integers in the array are less than 10000,
and if you access the array out of bounds, ArrayReader.get will return 2147483647.

Input: array = [-1,0,3,5,9,12], target = 9
Output: 4
Explanation: 9 exists in nums and its index is 4

Time : log(T) where T is and index of Target.
*/
class Solution {
    public int search(ArrayReader reader, int target) {
        int max = 2;
        int left=0, right=max;

        while(reader.get(max)<target){
            left = max;             // very smart to move left
            max = (int) Math.pow(max,2);
        }

        right=max;
        while(left <= right){
            int mid = left + (right-left)/2;
            /*
              As a rule of thumb, use :

              m = l + (r-l)/2       OR    m = r - (r-l)/2
              with                        with
              l = m + 1                   l = m
              r = m                       r = m-1
            */
            //System.out.println("mid"+mid);
            int t = reader.get(mid);

            if(t==target)
                return mid;


            // here both are moving with +1 OR -1 so we sont have infinite loop
            if(reader.get(mid)<target)
                left = mid+1;
            else
                right = mid-1;
        }

        return -1;
    }
}
