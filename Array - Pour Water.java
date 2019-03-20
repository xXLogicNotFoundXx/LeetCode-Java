/*
https://leetcode.com/problems/pour-water/
We are given an elevation map, heights[i] representing the height of the terrain at that index. 
The width at each index is 1. After V units of water fall at index K, how much water is at each index?
Input: heights = [2,1,1,2,1,2,2], V = 4, K = 3
Output: [2,2,2,3,2,2,2]
Explanation:
#       #
#   W   #
##WW#W###
#########

This looks like raindrop problem with two arrays separately but 
it wont work bcz of imited water drops and left right conditions

Also consider this case 
[1,2,3,4,3,2,1,2,3,4,3,2,1] V=2 , K=5
[1,2,3,4,3,3,2,2,3,4,3,2,1]

the time complexity should be O(N lg N + V lg N). The "add" operation in pq will execute at most N times.
Hence, the total runtime inside the while loops is going to be O(N log N). 
We also "remove" and "add" from priority queue V times, hence O(V log N).
Therefore, it should be O(N lg N + V lg N).  heapify procedure will take O(lg n).
*/
class Solution {
    
    public int[] pourWater(int[] heights, int V, int K) {
        // sort by height[a,b] if height is same then return biggest index 
        PriorityQueue<Integer>  leftPQ  = new  PriorityQueue<Integer>(new Comparator<Integer>(){    // new Comparator<T>(){ public int compare(T,T){} }
            public int compare(Integer a,Integer b){
                if(heights[a] == heights[b])
                    return b-a;
                return heights[a] - heights[b];
            }
        });
        // sort by height[a,b] if height is same then return smallest index 
        PriorityQueue<Integer>  rightPQ = new  PriorityQueue<Integer>(new Comparator<Integer>(){   // // new Comparator<T>(){ public int compare(T,T){} }
            public int compare(Integer a,Integer b){
                if(heights[a] == heights[b])
                    return a-b;
                return heights[a] - heights[b];
            }
        });
        
        int left = K-1, right=K+1;
        for(int drop=0;drop<V;drop++){
            
            while(left>=0 && heights[left] <= heights[left+1]){  // <= is Important we have to add all same and small height 
                leftPQ.add(left);                                // eventually if K level rises all indexes height in PQ should be rising eventually 
                left--;
            }
            while(right<  heights.length && heights[right] <= heights[right-1]){
                rightPQ.add(right); 
                right++;
            }
            
            if(!leftPQ.isEmpty() && heights[leftPQ.peek()] < heights[K]){ // fill it in left smallest
                heights[leftPQ.peek()]++;
                leftPQ.add(leftPQ.poll());
            } else if(!rightPQ.isEmpty() && heights[rightPQ.peek()] < heights[K]){ // fill it in right smallest
                heights[rightPQ.peek()]++;
                rightPQ.add(rightPQ.poll());
            } else {
                heights[K]++; // middle level rises 
            }
        }
        return heights;
    }
}
