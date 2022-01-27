/*
Medium - VIMP 2SUM variation
Amazon15 Citadel14 ServiceNow4 Bloomberg4 Cisco2 VMware2 Mathworks2 Salesforce2 Twilio2

https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/

You are given a list of songs where the ith song has a duration of time[i] seconds.
Return the number of pairs of songs for which their total duration in seconds is divisible by 60.
Formally, we want the number of indices i, j such that i < j with (time[i] + time[j]) % 60 == 0.
*/
class Solution {
    public int numPairsDivisibleBy60(int[] time) {
        if(time==null)
            return 0;

        Map<Integer,Integer> modCount = new HashMap<>();

        int ans = 0;

        for(int i=0;i<time.length; i++){

            int mod = time[i]%60;
            int numberToLookFor = (60 - mod) % 60 ;
            // we are doing mod again here ... bcz for the input like [120,180,240]

            ans += modCount.getOrDefault(numberToLookFor, 0);

            // put the mod in the map
            modCount.put(mod, modCount.getOrDefault(mod,0) + 1);
        }

        return ans;
    }
}
