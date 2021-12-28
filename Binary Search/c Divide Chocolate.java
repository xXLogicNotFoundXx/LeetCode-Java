/*
Hard
Google3 FB3 AMZ2
1231. Divide Chocolate
https://leetcode.com/problems/divide-chocolate/

You have one chocolate bar that consists of some chunks. Each chunk has its own sweetness given by the array sweetness.
You want to share the chocolate with your K friends so you start cutting the chocolate bar into K+1 pieces using K cuts,
each piece consists of some consecutive chunks.
Being generous, you will eat the piece with the minimum total sweetness and give the other pieces to your friends.
Find the maximum total sweetness of the piece you can get by cutting the chocolate bar optimally.

Input: sweetness = [1,2,3,4,5,6,7,8,9], K = 5
Output: 6
Explanation: You can divide the chocolate to [1,2,3], [4,5], [6], [7], [8], [9]

Well here, how do they know that we have to cut at 6 sweetness?

    1 May be I can try with minimum number in an array & try to make cuts with that sweetness.
    2 if that cuts more that K cuts then we can do the next min number... so on
        but what if array [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1] K = 4

    what number do we consider next?

    think what is the max number you can take ...
    that would be sum of all sweetness and that will make 1 cut ....

    eureka: left = min , right = sum of sweetness

Similar to problem :
    Koko eatin banana
    Capacity to ship packages in D day
    Split array largest sum

O( N*Log(S) ) - where S is total sweetness

*/
class Solution {
    public int maximizeSweetness(int[] sweetness, int K) {
        int min = Integer.MAX_VALUE;
        int totalSweetness = 0;
        for(int num : sweetness){
            min = Math.min(min,num);
            totalSweetness += num;
        }

        int left = min, right = totalSweetness;

        while(left < right){
            int mid = left + (right-left)/2;

            int cuts = howManyCuts(sweetness, mid);

            if(cuts<=K)
                right = mid;
            else
                left = mid+1;
        }

        return left; // once we cut the optimally .. we are getting the lowest sweetness for ourself .
    }

    int howManyCuts(int[] sweetness, int minTotalSweetness){

        int total=0,count=0;

        for(int num : sweetness){
            total += num;
            if(total>minTotalSweetness){
                count++;
                total=0;
            }
        }

        return count;
    }
}
