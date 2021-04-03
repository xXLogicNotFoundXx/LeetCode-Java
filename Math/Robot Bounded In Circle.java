/*
https://leetcode.com/problems/robot-bounded-in-circle/
On an infinite plane, a robot initially stands at (0, 0) and faces north. The robot can receive one of three instructions:

"G": go straight 1 unit;
"L": turn 90 degrees to the left;
"R": turn 90 degrees to the right.
The robot performs the instructions given in order, and repeats them forever.

Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.

Input: instructions = "GGLLGG"
Output: true

Input: instructions = "GG"
Output: false
*/
class Solution {
    public boolean isRobotBounded(String instructions) {
        int []cur = new int[]{0,0};
        char dir = 'N';
        
        for(int i=0; i<instructions.length(); i++){
            if(instructions.charAt(i)=='G'){
                cur[0] = (dir == 'N' ||  dir == 'S') ?  cur[0] :  dir == 'W' ? cur[0]-1 : cur[0]+1;
                cur[1] = (dir == 'W' ||  dir == 'E') ?  cur[1] :  dir == 'S' ? cur[1]-1 : cur[1]+1;
            }
            else if(instructions.charAt(i)=='L'){
                dir = dir=='N' ? 'W' : (dir=='S' ? 'E' : (dir=='E' ? 'N' : 'S' ));
            } else {
                dir = dir=='N' ? 'E' : (dir=='S' ? 'W' : (dir=='E' ? 'S' : 'N' ));
            }
        }
        
        if(cur[0]==0 && cur[1]==0)
            return true; 
        
        if(dir=='N') // if facing same dir as start we wont get the loop 
            return false; 
        
        return true;
    }
}
