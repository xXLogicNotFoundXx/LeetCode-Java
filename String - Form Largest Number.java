https://leetcode.com/problems/largest-number/
Given a list of non negative integers, arrange them such that they form the largest number.
For example:
[9,32]				[32,3,31]		[0,0,0]
return "931"			return "33231"		return "0"

The idea here is basically implement a String comparator to decide
which String should come first during concatenation.

class Solution {
    class SortByDigits implements Comparator<Integer>{
        public int compare(Integer a, Integer b){
            String str1 = a.toString() + b.toString();
            String str2 = b.toString() + a.toString();

            return str1.compareTo(str2); 
            // On sort this comparitor will give us order in reverse order 
        }
    }
    
    public String largestNumber(int[] nums) {
        if(nums==null || nums.length==0) return "";
        
        Integer []numsInt = new Integer[nums.length];
        for(int i=0;i<nums.length;i++)
            numsInt[i] = new Integer(nums[i]);
                
        Arrays.sort(numsInt,new SortByDigits()); 
        
        StringBuffer str = new StringBuffer();
        for(int i=nums.length-1;i>=0;i--)
            str.append(numsInt[i]);
        
        // for ip = [0,0,0]
        if(str.charAt(0)=='0')
            return "0";
        
        return str.toString();
    }
}

In terms of Time and Space Complexity:
Let's assume:
the length of input array is n,
average length of Strings in s_num is k,
Then, compare 2 strings will take O(k).
Sorting will take O(n*logn)
Appending to StringBuilder takes O(n).
So total will be O(k*nlog(nk)) + O(n) = O(k*nlog(nk)).

Space is pretty straight forward: O(n).
