/*
774. Minimize Max Distance to Gas Station
https://leetcode.com/problems/minimize-max-distance-to-gas-station/

On a horizontal number line, we have gas stations at positions stations[0], stations[1], ..., stations[N-1], where N = stations.length.
Now, we add K more gas stations so that D, the maximum distance between adjacent gas stations, is minimized.

Return the smallest possible value of D.

Example:

Input: stations = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], K = 9
Output: 0.500000
Note:

stations.length will be an integer in range [10, 2000].
stations[i] will be an integer in range [0, 10^8].
K will be an integer in range [1, 10^6].
Answers within 10^-6 of the true value will be accepted as correct.
*/

class Solution {
/*    
    Solution 1: for each K we find max distance between 2 gas station and add station in beetween. (N^2)
    Solution 2: TLE
            1. We put diff on MaxHeap with one station in it.
            2. We pop and we put the one more station in the that distance,
                so the avg distance becomes distance/stations.
            3. we keep adding K stations.
    
    Solution 3: is bonary search too diff so i am not doing this. 
*/  
    public class Pair {
        public double distance;
        public double stations;
        
        Pair(double d, double s){
            distance = d; 
            stations = s;
        }
        
        public double avgDistance(){
            return distance/stations;
        }
        
    }
    
    public double minmaxGasDist(int[] stations, int K) {
        PriorityQueue<Pair> maxHeap = new PriorityQueue<Pair>(new Comparator<Pair>(){
            public int compare(Pair a, Pair b){
                return  a.avgDistance() < b.avgDistance() ? 1 : -1; 
            }
        });
        
        for(int i=0; i<stations.length-1; i++){
            maxHeap.add(new Pair(stations[i+1] - stations[i], 1));
        }
        
        while(K>0){
            Pair p = maxHeap.poll();
            p.stations++;
            maxHeap.add(p);
            K--;
        }
        
        return maxHeap.poll().avgDistance();
    }
}
