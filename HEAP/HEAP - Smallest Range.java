/*
Hard -
amz-google-4  Microsoft-Pinterest-2

https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/

632. Smallest Range
Hard
You have k lists of sorted integers in ascending order. Find the smallest range that includes at least one number from each of the k lists.
We define the range [a,b] is smaller than range [c,d] if b-a < d-c or a < c if b-a == d-c.

Example 1:
Input:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
Output: [20,24]
Explanation:
List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
List 2: [0, 9, 12, 20], 20 is in range [20,24].
List 3: [5, 18, 22, 30], 22 is in range [20,24].
*/
/*
 consider if youe have only 1 number in each list - 4,0,5 then range is 0-5 (smallest-largest)
 so we can have that in priority queue then remove smallest one(MIN_HEAP) and then add the next number from the same list.
 keep calculating min,max and store result of [minimum of Math.abs(min-max)]
*/
class Solution {
    // This is a very nice havin list and currentposition of a list in the object.
    // and use these objects for pq.
    class CurNumList{
        List<Integer> list;
        int i=0;
        CurNumList(List<Integer> list){
            this.list = list;
        }
        boolean isEnd(){ return i==list.size(); }
        int get(){ return list.get(i); }
    }

    public int[] smallestRange(List<List<Integer>> nums) {
        if(nums==null || nums.size()==0)
                return new int[2];

        int max = Integer.MIN_VALUE;
        PriorityQueue<CurNumList> pq = new PriorityQueue<CurNumList>(new Comparator<CurNumList>(){
            public int compare(CurNumList a, CurNumList b){
                return a.get() - b.get();
            }
        });

        for(List<Integer> list : nums){
            if(list==null || list.size()==0)
                return new int[2];

            if(list.get(0)>max)
                max = list.get(0);

            CurNumList nl = new CurNumList(list);
            pq.add(nl);
        }

        int min = pq.peek().get();
        int diff = Math.abs(min-max);
        int []result = new int[]{min,max};

        while(pq.size()==nums.size()){
            CurNumList nl = pq.poll();
            nl.i++;
            if(!nl.isEnd()){
                max=Math.max(max,nl.get()); // update the max

                pq.offer(nl);
                min=pq.peek().get(); // get the min from pq

                if(diff > Math.abs(min-max)){
                    result[0] = min;
                    result[1] = max;
                    diff = Math.abs(min-max);
                }
            }
        }
        return result;
    }
}
