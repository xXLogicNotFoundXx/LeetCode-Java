// https://leetcode.com/problems/k-closest-points-to-origin/

// we can use MAX_HEAP and keep K items in the heap at anygiven point. (O(n*log(k))
// if we use MIN_HEAP then we have to add all points in HEAP and then take out K elements. (O(n*log(n))
// if size becomes more than K we remove item from the HEAP in the end we gonna have K point which are closest to 0,0
class Solution {
    class PointDistance implements Comparator<PointDistance>{
        int x,y,distance;
        PointDistance(int x, int y){
            this.x = x;
            this.y = y;
            this.distance = x*x + y*y;
        }
        PointDistance(){}
        public int compare(PointDistance a, PointDistance b){
            return b.distance - a.distance;
        }
    }
    
    public int[][] kClosest(int[][] points, int K) {
        if(points == null) 
            return new int[0][];
        if(points.length <=K) 
            return points;
        
        PriorityQueue<PointDistance> queue = new PriorityQueue<PointDistance>(new PointDistance());
        for(int i=0;i<points.length;i++){
            queue.add(new PointDistance(points[i][0],points[i][1]));
            if(queue.size()>K)
                queue.poll();
        }
        
        int [][] ans = new int[K][2];
        int i=0;
        while(!queue.isEmpty()){
            PointDistance p = queue.poll();
            ans[i][0] = p.x;
            ans[i++][1] = p.y;
        }
        return ans;
    }
}
