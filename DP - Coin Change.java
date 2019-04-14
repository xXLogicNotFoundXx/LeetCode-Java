class Solution { // [2,4,5] 12
    // Same as perfect square problem but we have to numbers for that : https://leetcode.com/problems/perfect-squares/
    public int coinChange(int[] coins, int amount) {
        int[] DP = new int[amount+1];
        Arrays.fill(DP,-1);
        DP[0] = 0;
        for(int target=1;target<= amount; target++){
            int min = Integer.MAX_VALUE;
            for(int coin : coins){
                // dont consider the numbers which are marked as -1 
                if(coin <= target && DP[target-coin]!=-1){ 
                    min = Math.min(min,DP[target-coin] +1);
                    DP[target] = min; 
                }
            }
        }
        return DP[amount];
    }
}

 /*       1st iteration  target = 1  i/p = [2,4,5] 12
            sol[] = 0,-1,-1,-1,-1,-1,-1,-1
          target 2 = for coin=2  (2-2=0th ) sol[2] is 0+1(+1 bcz we considerd coin 2) 
                     we end up writting sol[2]=1... rest coins are bigger 
            sol[] = 0,-1,1,
          target 3
            sol[] = for coin=2   (3-2 = 1th ) sol[1] is -1.. we dont do anything and 
                  = 0,-1,1,-1
          target 4
            sol[] = for coin 2 . (4-2 = 2nd) sol[2] is 1 so 1+1 =2 we end up writtin sol[4]=2
                    0,-1,1,-1,2
            sol[] = for coin 4 . (4-4 = 0th) sol[0] is 0 + 1=1 we end up writtin sol[4]=1
                    0,-1,1,-1,1
                    rest coins are bigger 
          target 5 = 0,-1,1,-1,1,1
          target 6 = 0,-1,1,-1,1,1,2 (coin 2, bcz 6-2 sol[4th] + 1)
                   = 0,-1,1,-1,1,1,2 (coin 4, bcz 6-4 sol[2] + 1)  
                   = for coin 5  sol[6-5] is -1 so we skip 
*/




