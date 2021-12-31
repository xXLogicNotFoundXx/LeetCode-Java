/*
https://leetcode.com/problems/minimum-number-of-refueling-stops/
A car travels from a starting position to a destination which is target miles east of the starting position.
Along the way, there are gas stations.  Each station[i] represents a gas station that is station[i][0] miles east of the starting position,
and has station[i][1] liters of gas.
The car starts with an infinite tank of gas, which initially has startFuel liters of fuel in it.  It uses 1 liter of gas per 1 mile that it drives.
When the car reaches a gas station, it may stop and refuel, transferring all the gas from the station into the car.
What is the least number of refueling stops the car must make in order to reach its destination?  If it cannot reach the destination, return -1.

Input: target = 100, startFuel = 10, stations = [[10,60],[20,30],[30,30],[60,40]]
Output: 2

*/

class Solution {

    /* TLE - 109 / 198 test cases passed.
        Take it or leave it approach and using memoization.
        The problem with this is that our starting state changes if we take it ..i.e remaining fuel

        This is not simple take or leave.. take/leave changes the ability to take stops down the path
        it is not pick and choose also like we did in Maximum Profit in Job Scheduling problem.

        thats is why the state at every index changes... and the memoization can not happen only on index position.
        index position with fuel left changes our decision.

        so this become like N*MAX_fuel

        So same mistake that ... we started tracking Index and SomeValue that changes as per data.
        This is not good.

    */
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        if(target <= startFuel)
            return 0;

        // now adding memoization ..
        Map<String,Integer> memo = new HashMap<>();

        int ans = getStops(target, stations, 0, 0, startFuel,0, memo);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    Integer getStops(int target,  int[][] stations, int start, int milesTraveled, int fuelLeft, int stops , Map<String,Integer> memo){

        if(milesTraveled+fuelLeft >= target)
            return stops;

        if(start>= stations.length)
            return Integer.MAX_VALUE;

        // memo
        String key = start+":"+fuelLeft+":"+stops;
        if(memo.getOrDefault(key, null) != null)
            return memo.get(key);

        int ans = Integer.MAX_VALUE;
        for(int i=start; i<stations.length; i++){

            // i can reach this station
            if(milesTraveled+fuelLeft >= stations[i][0]){

                int newMiles  = stations[i][0];
                int fuelToAdd = stations[i][1];

                int fuelLeftAfter = fuelLeft - (newMiles-milesTraveled); // after we reached this station

                ans = Math.min(ans, getStops(target, stations, i+1, newMiles, fuelLeftAfter + fuelToAdd, stops+1, memo));
            }
        }

        memo.put(key, ans);
        return ans;
    }

    

}
