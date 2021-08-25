
##### Knapsack - finding if the sum is possible from the given Set. 

###### Coin change - is kinda Knapsack but usually you can use coins multiple times. 
      - For each coin .. and 0-SUM.  (dp[0]=true)
      - you start marking dp[i] = dp[i-coin] that way we mark every possible number that can be reached by combinations of all coint
      - let it sink      

###### There will be something like coin change but you can use number only one time. ( Real Knapsack )
      - For each number .... and SUM-0 (dp[0]=true)
      - you start marking dp[i] = dp[i] || dp[i-number] .
      - Starting from the end we make a path to desired SUM by considering each number only once.
      
