/*
Medium - IMP

https://leetcode.com/problems/longest-consecutive-sequence/
Input: [100, 4, 200, 1, 3, 2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4].
Therefore its length is 4.

We will use HashMap. The key thing is the boundary points of the sequence should maintain the right length. 
For example, as a result, for sequence {1, 2, 3, 4, 5}, map.get(1) and map.get(5) should both return 5.

How do we build this? 
Whenever a new element n is inserted into the map, do two things:
See if n - 1 and n + 1 exist in the map, and if so, it means there is an existing sequence next to n. 
Variables left and right will be the length of those two sequences, while 0 means there is no sequence and n will be the boundary point later. 
Store (left + right + 1) as the associated value to key n into the map.
Use left and right to locate the other end of the sequences to the left and right of n respectively, and replace the value with the new length.
Everything inside the for loop is O(1) so the total time is O(n).
*/
// O(n.LogN) Time and O(1) space
class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        Arrays.sort(nums);

        int longestStreak = 1;
        int currentStreak = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i-1]) {
                if (nums[i] == nums[i-1]+1) {
                    currentStreak += 1;
                }
                else {
                    longestStreak = Math.max(longestStreak, currentStreak);
                    currentStreak = 1;
                }
            }
        }

        return Math.max(longestStreak, currentStreak);
    }
}

// O(n) time and O(n) space
class Solution {
    public int longestConsecutive(int[] nums) {
     
        Map<Integer,Integer> map = new HashMap();
        int ans = 0;
        if(nums==null)
            return ans;
        for(int num : nums){
            if(!map.containsKey(num)){
                int left = map.getOrDefault(num-1,0);
                int right = map.getOrDefault(num+1,0);
                
                int sum = left + right +1;
                map.put(num,sum);
                
                // update the new boundries 
                // this is where all logic is 
                map.put(num-left,sum);
                map.put(num+right,sum);
                
                ans = Math.max(ans,sum);
            }
        }
        return ans;
    }
}

