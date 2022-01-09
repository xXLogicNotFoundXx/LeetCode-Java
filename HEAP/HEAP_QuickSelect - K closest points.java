/*
Medium - VIMP
Facebook104 Amazon49 LinkedIn7 Google4

https://leetcode.com/problems/k-closest-points-to-origin/

We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
(Here, the distance between two points on a plane is the Euclidean distance.)
You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)


distance between (x1,y1) & (x2,y2) is =  sqrt( (x1-x2)^2 + (y1-y2)^2)
in this case one point is 0,0 => sqrt(x2^2 + y2^2)
we dont need the actual distance so we can skip sqrt just say x^2 + y^2 is our distance
Sort or put in priority queue.

Solution 1 :
1 NLogN sort the array and return 0-K
*/
public int[][] kClosest(int[][] points, int K) {
    Arrays.sort(points, new Comparator<int[]>() {
        @Override
        public int compare(int[] p1, int[] p2) {
             /* Pythagoras theorem
                Sqrt( (x1-x2)^2 + (y1-y2)^2  )
                one point is (0,0)
             */
             int distanceP1 = p1[0] * p1[0] + p1[1] * p1[1];  // we dont really need to do sqrt
             int distanceP2 = p2[0] * p2[0] + p2[1] * p2[1]
            return distanceP1 - distanceP2;
        }
    });
    return Arrays.copyOfRange(points, 0, K);
}
/*
 2
 we can use MAX_HEAP and keep K items in the heap at anygiven point
 Theoretically, the time complexity is O(NlogK), 24ms
 The advantage of this solution is it can deal with real-time(online) stream data.
 It does not have to know the size of the data previously.
*/
class Solution {
    class PointDistance {
        public int x,y,distance;
        public PointDistance(int x, int y){
            this.x = x;
            this.y = y;
            this.distance = x*x + y*y; // Sqrt( (x1-x2)^2 + (y1-y2)^2  )
        }
    }

    public int[][] kClosest(int[][] points, int K) {
        if(points == null || points.length <=K)
            return points;

        // MAX HEAP - Reverse order.
        PriorityQueue<PointDistance> queue = new PriorityQueue<PointDistance>((a,b) -> b.distance - a.distance);

        for(int i=0;i<points.length;i++){
            queue.add(new PointDistance(points[i][0],points[i][1]));
            if(queue.size()>K)
                queue.poll();
        }

        int [][] ans = new int[queue.size()][2];
        int i=0;
        while(!queue.isEmpty()){
            PointDistance p = queue.poll();
            ans[i][0] = p.x;
            ans[i++][1] = p.y;
        }
        return ans;

    }

    /*
    III. The last solution is based on quick sort, we can also call it quick select.
    In the quick sort, we will always choose a pivot to compare with other elements.
    After one iteration, we will get an array that all elements smaller than the pivot are on the left side of the pivot
    and all elements greater than the pivot are on the right side of the pviot (assuming we sort the array in ascending order).
    O(N) 5ms
    */
    public int[][] kClosest1(int[][] points, int K) {
        if(points == null || points.length <=K)
            return points;

        int start = 0, end = points.length-1;
        while(start<end){
            int partitionIndex = partitionQuickSelect(points,start,end);
            if(partitionIndex==K){
                return Arrays.copyOfRange(points, 0, K);
            }
            if(partitionIndex<K)
                start = partitionIndex+1;
            else
                end = partitionIndex-1;
        }

        return Arrays.copyOfRange(points, 0, K);
    }

    int partitionQuickSelect(int[][] points, int start, int end){
        int []pivot = points[end];
        int partitionIndex = start;

        for(int i=start;i<end;i++){
            if(distanceToOrigin(points[i]) <= distanceToOrigin(pivot) && i==partitionIndex){
                partitionIndex++;
            }
            else if(distanceToOrigin(points[i]) <= distanceToOrigin(pivot) ){
                swap(points,partitionIndex,i);
                partitionIndex++;
            }
        }
        swap(points,partitionIndex,end);
        return partitionIndex;
    }

    void swap(int[][] points, int i, int j){
        int []temp = points[i];
        points[i] = points[j];
        points[j] = temp;
    }

    int distanceToOrigin(int []p1){
        return p1[0]*p1[0]+p1[1]*p1[1];
    }
}
