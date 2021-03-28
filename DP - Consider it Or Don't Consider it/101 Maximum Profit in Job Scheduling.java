/*
https://leetcode.com/problems/maximum-profit-in-job-scheduling/
We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].

You're given the startTime, endTime and profit arrays, return the maximum profit you can take such that there are no two jobs 
in the subset with overlapping time range.

If you choose a job that ends at time X you will be able to start another job that starts at time X.

*/
class Solution {
    /*Transform the scattered dat into class*/
    class Job {
        int start, end, profit;
        Job(int a, int b, int c){
           start=a; end=b; profit=c; 
        }
    }
    
    // BETTER way below.
    // does not passes the all test cases ... better way to come up with N^2 
    /* public int jobScheduling1(int[] startTime, int[] endTime, int[] profit) {
        Job[] jobs = new Job[startTime.length];
        for(int i=0;i<startTime.length;i++){
            jobs[i] = new Job(startTime[i],endTime[i],profit[i]);
        }
        
        // sort by start time 
        Arrays.sort(jobs, (a,b) -> a.start==b.start ? a.end-b.end : a.start-b.start);
        
        // adding memo 
        // what am i tracking ->  index and current time , so depends on the data so can create an array for that 
        Map<String, Integer> memo = new HashMap<>();
        return calculateProfit1(jobs, 0, 0, memo);
    }

    int calculateProfit1(Job[] jobs, int startIndex, int currentTime, Map<String, Integer> memo){
        if(startIndex==jobs.length)
            return 0;

        String key = startIndex +":"+currentTime;
        if(memo.containsKey(key))
            return memo.get(key);
        
        int subAns = 0; 
        
        if(jobs[startIndex].start >= currentTime){ // i can take this job 
            subAns = calculateProfit1(jobs, startIndex+1, jobs[startIndex].end, memo) + jobs[startIndex].profit;
        }
        
        subAns = Math.max( subAns, calculateProfit1(jobs, startIndex+1, currentTime, memo));
        
        memo.put(key,subAns);
        return subAns;
    }*/
    
/* ^ 
    with memo test cases 22 / 27 - TLE 
    without memo 18 / 27 passsed - TLE
    This is I usually always think.
    This is not N^2 as the we tracking the index and currentTime ... 
    this would create a lot of calls on same index ..
    Usually if you are tracking index and if you are passing down the some state/count it messes up the runtime. 

    Is there anyway we can just track Index in memo?
    we have to getRid of currentTime .. and find the subans, and once we calculate that sub ans we should be done.
    That means we have to pass next index such that we should be able to take that job and not worry about currentTime. 

*/  
    
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        Job[] jobs = new Job[startTime.length];
        for(int i=0;i<startTime.length;i++){
            jobs[i] = new Job(startTime[i],endTime[i],profit[i]);
        }
        
        // sort by start time 
        Arrays.sort(jobs, (a,b) -> a.start==b.start ? a.end-b.end : a.start-b.start);
        
        // adding memo 
        // what am i tracking ->  index and current time , so depends on the data so can create an array for that 
        Map<Integer, Integer> memo = new HashMap<>();
        return calculateProfit(jobs, 0, memo);
    }

    int calculateProfit(Job[] jobs, int startIndex, Map<Integer, Integer> memo){
        if(startIndex==jobs.length)
            return 0;

        if(memo.containsKey(startIndex))
            return memo.get(startIndex);
        
        // take the current job and find the next index of the job that we can take
        int nextJobIndex = findNextJobThatICanTake(jobs, startIndex);
        int subAns = jobs[startIndex].profit;
        
        if(nextJobIndex!=-1)
            subAns = calculateProfit(jobs, nextJobIndex, memo) + jobs[startIndex].profit;
        
        // leave the current job 
        subAns = Math.max(subAns, calculateProfit(jobs, startIndex+1, memo));
        
        memo.put(startIndex,subAns);
        return subAns;
    }
    
    int findNextJobThatICanTake(Job[] jobs, int startIndex){
        for(int i=startIndex+1; i<jobs.length; i++){
            if(jobs[startIndex].end <= jobs[i].start)
                return i;
        }
        return -1;
    }
  
  // there is another solution with binary search ... will look at it later 
}
