/*
Hard
Amz 3 appl2 

https://leetcode.com/problems/cut-off-trees-for-golf-event/

675. Cut Off Trees for Golf Event

Given matrix : 
0 represents the obstacle can't be reached.
1 represents the ground can be walked through.
The place with number bigger than 1 represents a tree can be walked through, and this positive number represents the tree's height.

We have to cut all trees and we always cut off the trees in sorted order of the tree height.
You will start from the point (0, 0) and you should output the minimum steps you need to walk to cut off all the trees. 
If you can't cut off all the trees, output -1 in that situation.
You are guaranteed that no two trees have the same height, and there is at least one tree needs to be cut off.
( ^ if this is not gauranteed then this problem is a mess. Because how would you decide which one to take first if two trees have same height) 

Since we have to cut trees in order of their height, we first put trees{row, col, height} into a priority queue by height.
Poll each tree from the queue and use BFS to find out steps needed.
Since there are m * n trees and for each BFS worst case time complexity is O(m * n) .
The worst case time complexity could be O((m*n)*(m*n))  = >  O(m^2 * n^2)  (m = number of rows, n = number of columns) 
*/
class Solution {
    static int[][] dir = {{0,1}, {0, -1}, {1, 0}, {-1, 0}};

    public int cutOffTree(List<List<Integer>> forest) {
        PriorityQueue<List<Integer>> pq = new PriorityQueue<List<Integer>>( new Comparator<List<Integer>>(){
            public int compare(List<Integer> a, List<Integer> b){
                return a.get(2) - b.get(2);
            }
        });
        
        for(int i=0;i<forest.size();i++){
            for(int j=0;j<forest.get(0).size();j++){
                if(forest.get(i).get(j)>1){
                    List<Integer> point = new ArrayList<>();
                    point.add(i);point.add(j);
                    point.add(forest.get(i).get(j));
                    pq.add(point);
                }
            }
        }
        // assuming starting is not an obstacle
        List<Integer> start = new ArrayList<>();
        start.add(0);start.add(0);start.add(forest.get(0).get(0));
        
        int sum = 0;
        while(!pq.isEmpty()){
            List<Integer> nextTree = pq.poll();
            int steps = minSteps( start,  nextTree, forest);
            if(steps==-1)
                return -1;
            sum += steps;
            start = nextTree;
        }
        return sum;
    }
    
    int minSteps(List<Integer> start, List<Integer> nextTree, List<List<Integer>> forest){
        int [][] visited = new int[forest.size()][forest.get(0).size()];
        int steps =0; 
        Queue<List<Integer>> q = new LinkedList<List<Integer>>();
        q.add(start);
        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0;i<size;i++){
                List<Integer> cur = q.poll();
                
                if(cur.get(0)==nextTree.get(0) && 
                   cur.get(1)==nextTree.get(1) )
                    return steps;
                
                for( int []d : dir){
                    List<Integer> next = new ArrayList<>();
                    next.add(cur.get(0)+d[0]);
                    next.add(cur.get(1)+d[1]);
                    // out of range/ obstacle/ visited then continue
                    if(  next.get(0) <0 || next.get(0) >= forest.size()
                      || next.get(1) <0 || next.get(1) >= forest.get(0).size() 
                      || visited[next.get(0)][next.get(1)]==1
                      || forest.get(next.get(0)).get(next.get(1)) == 0) { 
                        continue;
                    }
                    visited[next.get(0)][next.get(1)]=1;
                    q.add(next);
                }
            }
            steps++;
        }
        return -1;
    }
}
