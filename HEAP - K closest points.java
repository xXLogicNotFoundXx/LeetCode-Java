// https://leetcode.com/problems/k-closest-points-to-origin/
class Solution {
    /* 1 NLogN sort the array and return K 
    public int[][] kClosest(int[][] points, int K) {
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] p1, int[] p2) {
                return p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1];
            }
        });
        return Arrays.copyOfRange(points, 0, K);
    }
    */
    // 2 
    // we can use MAX_HEAP and keep K items in the heap at anygiven point
    // Theoretically, the time complexity is O(NlogK), 24ms
    // The advantage of this solution is it can deal with real-time(online) stream data. It does not have to know the size of the data previously.

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
    
    public int[][] kClosest1(int[][] points, int K) {
        if(points == null || points.length <=K) 
            return points;
        
        PriorityQueue<PointDistance> queue = new PriorityQueue<PointDistance>(new PointDistance());
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
    average time complexity O(N) - 5ms 
    */
    public int[][] kClosest(int[][] points, int K) {
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
                end= partitionIndex-1;
        }
        
        return Arrays.copyOfRange(points, 0, K);
    }
    
    int partitionQuickSelect(int[][] points, int start, int end){
        int []pivot = points[end];
        int partitionIndex = start;
        
        for(int i=start;i<end;i++){
            if(distanceToOrigin(points[i]) <= distanceToOrigin(pivot) ){
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
