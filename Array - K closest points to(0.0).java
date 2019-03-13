// https://leetcode.com/problems/k-closest-points-to-origin/

// we can use MAX_HEAP and keep K items in the heap at anygiven point 
// if size becomes more than K we remove item from the HEAP in the end we gonna have K point which are closest to 0,0
class Solution {
    class PointDistance{
        int x,y,distance;
        PointDistance(int x, int y, int distance){
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
    
    class sortByDistanceDec implements Comparator<PointDistance>{
        public int compare(PointDistance a, PointDistance b){
            return b.distance - a.distance;
        }
    }
    
    public int[][] kClosest(int[][] points, int K) {
        if(points == null) return new int[0][];
        
        PriorityQueue<PointDistance> queue = new PriorityQueue<PointDistance>(new sortByDistanceDec());
        for(int i=0;i<points.length;i++){
            int dist = calDistance(points[i]);
            queue.add(new PointDistance(points[i][0],points[i][1],dist));
            if(queue.size()>K)
                queue.poll();
        }
        int [][] ans = new int[K][2];
        int i=0;
        while(!queue.isEmpty()){
            PointDistance p = queue.poll();
            ans[i][0] = p.x;
            ans[i][1] = p.y;
            i++;
        }
        return ans;
    }
    
    int calDistance(int[] point){
        return point[0]*point[0] + point[1]*point[1] ; 
    }
    
}

we find the K-th distance by creating an array of distances and then sorting them. 
After, we select all the points with distance less than or equal to this K-th distance.
O(N*log(N)
The problem with this what if the Kth distance has multiple points and during calculation they 
come first than the lowest points we end up getting more point at kth distance than lowder distance once.

class Solution {
    public int[][] kClosest(int[][] points, int K) {
        if(K>points.length) return new int[0][];
        
        int []distance = new int[points.length];
        int [][] ans = new int[K][2];
        for(int i=0;i<points.length;i++){
            distance[i] = calDistance(points[i]);
        }
        Arrays.sort(distance);
        int j=0;
        for(int i=0;i<points.length;i++){
            if(calDistance(points[i]) <= distance[K-1])
                ans[j++]= points[i];
        }
        return ans;
    }
    
    int calDistance(int[] point){
        return point[0]*point[0] + point[1]*point[1] ; 
    }
    
}
