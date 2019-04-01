/*
https://leetcode.com/problems/koko-eating-bananas/
Koko loves to eat bananas.  
There are N piles of bananas, the i-th pile has piles[i] bananas.  
The guards have gone and will come back in H hours.
If the pile has more than K bananas, she eats K and then next hr she eat K from the same pile.
If the pile has less than K bananas, she eats all of them and won't eat any more bananas during this hour.
*/
// Note: the max she can eat in an hr is  one pile she can not eat 2 piles in an hr
// so the max she can eat is maxPile in an hr 
class Solution {
    // O(log(x)*N) x is total number of bananas
    
    public int minEatingSpeed(int[] piles, int H) {
        if(piles==null || piles.length==0 || H==0)
            return 0;
        
        int maxPile=0;
        long total = 0; //long 
        for(int pile : piles){
            total += pile;
            maxPile = Math.max(maxPile,pile);
        }
        
        if(H<piles.length)  return maxPile;
        if(H>=total)  return 1;
        
        int start = 1, end = maxPile;        
        while(start<end){
            int mid = start+(end-start)/2;
            int hrs = countHours(piles,mid);
            if(hrs<=H){                         // <= very important 
                end = mid;
            } else {
                start = mid+1;
            }
        }
        return start;
    }
    
    int countHours(int[] piles, int limit){
        int hrs =0;
        for(int i=0;i<piles.length;i++){
            hrs += piles[i]/limit;
            if(piles[i]%limit!=0)    // this is faster than Math.ceil((double)piles[i]/limit); 11 ms vs 35 ms!
                hrs++;
        }
        return hrs;
    }
}
