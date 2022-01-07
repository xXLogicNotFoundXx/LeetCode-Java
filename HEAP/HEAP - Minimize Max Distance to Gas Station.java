/*
Hard -
only Google2 in last two years.

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



   Time :  O(N*Log(N)+ K*Log(N))
   Space : O(n)
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


    // This is wrong algorithm
    // Imagine we have to add 2 stattion and the largest distance is 30
    // we add in the middle so now we have 15 and 15 distance. then we add a station in one of the 15 diff.
    // we should be adding 2 station at 10 and 20 -- so the min dstance becomes 10
    public double minmaxGasDist(int[] stations, int k) {
        if(stations==null || stations.length==0)
            return 0;
        PriorityQueue<Double> pq = new PriorityQueue<Double>( Collections.reverseOrder());

        for(int i=0; i<stations.length-1; i++){
            pq.add(stations[i+1]*1.0 - stations[i]*1.0);
        }

        for(int i=0;i<k; i++){
            double dist = pq.poll();
            System.out.println(dist);
            dist = dist/2.0;
            pq.add(dist);
            pq.add(dist);
        }
        return pq.poll();
    }

}
