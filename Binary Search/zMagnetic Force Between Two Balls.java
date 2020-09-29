/*
1552. Magnetic Force Between Two Balls
https://leetcode.com/problems/magnetic-force-between-two-balls/

In universe Earth C-137, Rick discovered a special form of magnetic force between two balls 
if they are put in his new invented basket. Rick has n empty baskets, the ith basket is at position[i],
Morty has m balls and needs to distribute the balls into the baskets 
such that the minimum magnetic force between any two balls is maximum.

Rick stated that magnetic force between two different balls at positions x and y is |x - y|.

n == position.length
2 <= n <= 10^5
1 <= position[i] <= 10^9
All integers in position are distinct.
2 <= m <= position.length


Given the integer array position and the integer m. Return the required force.
Input: position = [1,2,3,4,7], m = 3
Output: 3
Explanation: Distributing the 3 balls into baskets 1, 4 and 7 will make the magnetic 
force between ball pairs [3, 3, 6]. 
The minimum magnetic force is 3. We cannot achieve a larger minimum magnetic force than 3.

*/
class Solution {
    // Approach 1 
    // I tried to figure out where to put the next magnet?
    // you can put first magnet at first position then 2nd in last position. 
    // then in then the middle index ... but that is not correct ...
    // the array is not sorted and even after sort the mid index shouldnt be always correct position.
    // [5,4,3,2,1,1000000000]
    // [1,2,3,4,5,1000000000] with this logic we will place 3rd magnet in basket 3 but it should have been 5 
    // baskets are not evenly distributed. SO this thinking is not correct. 
    
    // Approach 2
    // we know the max magnetic field would be position[0] - position[n-1]
    // i.e minPosition and maxPosition
    // we can put magnet at certain difference [mid] ans see how many magnets we can put 
    // according to that we can move our left and right 
    
    
    public int maxDistance(int[] position, int m) {
        
        Arrays.sort(position);
        int left = 1;
        int right = position[position.length-1];
        
        int minAns = -1;
        
        // NOTE : this is left <= right 
        // READ the comments below. 
        
        while(left <= right){
            int mid = left + (right-left)/2;
            
            int magnets = placeMagnetAtThisForce(position, mid);
            // System.out.println("left="+left+" right="+right+" Mid="+mid+" Mag="+magnets);
            // we want to keep moving right including left=right (finding max magForce) 
            if(magnets >= m){ 
                // left = mid; 
                /* only this is not enough bcz this could go in infinite loop : 
                   [5,4,3,2,1,1000000000] m=2
                    left=999999999 right=1000000000 Mid=999999999 Mag=2
                    left=999999999 right=1000000000 Mid=999999999 Mag=2
                    left=999999999 right=1000000000 Mid=999999999 Mag=2 
                   
                   [1,2,3,4,7] 3
                    left=0 right=7 Mid=3 Mag=3
                    left=3 right=7 Mid=5 Mag=2
                    left=5 right=7 Mid=6 Mag=2
                    left=6 right=7 Mid=6 Mag=2
                    left=6 right=7 Mid=6 Mag=2
                    left=6 right=7 Mid=6 Mag=2
                    
                
                   we have seen when we were asked for minimum of something 
                   we did this same 
                   but RIGHT = mid and  left = mid+1 
                   koko, min capacity, mim divisor, mim sweetness to cut chocolate, 
                   min days for bouquets, split array min sum.
                   These all were asking for min so we would move right=mid  & left=mid+1
                   and we never encountered infinite loop.
                   Why?
                   
                   well for given left and right; mid will never calculate to be right.
                   unless they left and right are same. 
                   because as long as there is a 1 unit difference between left and right 
                   mid will never be calculate to be right. 
                    
                    So the following code is required 
                */
                minAns = mid;  // this will be difficult if there is no ans 
                left = mid+1;
                
            } else {
                right = mid-1;
            }
        }
        
        return minAns;
    }
    
    int placeMagnetAtThisForce(int[] pos, int magnetForce){
        int lastPos = pos[0]; 
        int count = 1; 
        
        for(int i=1;i<pos.length;i++){
            if(pos[i] - lastPos >= magnetForce){
                count ++;
                lastPos = pos[i];
            }
        }
        
        return count; 
    }
}
