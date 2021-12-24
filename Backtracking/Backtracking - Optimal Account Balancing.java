/*
https://leetcode.com/problems/optimal-account-balancing/
465. Optimal Account Balancing
A group of friends went on holiday and sometimes lent each other money. 
A transaction will be given as a tuple (x, y, z). Note that x ≠ y and z > 0.
Person's IDs may not be linear, e.g. we could have the persons 0, 1, 2 or we could also have the persons 0, 2, 6.
Find minimum transaction needed. 
Input:
[[0,1,10], [2,0,5]]
    Explanation:
    Person #0 gave person #1 $10.
    Person #2 gave person #0 $5.

Output:
2
One way to settle the debt is person #1 pays person #0 and #2 $5 each.

we can build a map like who owes money and who need to get the money  (+ve owes money )
0 -> -5
1 -> +10
2 -> -5 
create a list of map.values ->  -5, 10 , -5 

[[0,1,10], [1,0,1], [1,2,5], [2,0,5]]
Person #0 gave person #1 $10.
Person #1 gave person #0 $1.
Person #1 gave person #2 $5.
Person #2 gave person #0 $5.
Map : 
0 -> 4
1 -> -4
2 -> 0
create a list of map.values ->  4, -4 , 0

Now we can use backtracking with  start=0
and internal loop from i=start+1 to N 
    in loop if we find +ve/-ve OR -ve/+ve cobination for start & i'th 
    that means we have to do transaction. 
    we give ALL start's money to i and then call backtracking function again with (start +1) 
    (as start gave all his money he is clear now & all other (start+1...n) needs to settle ..
    doing this we keep track of minimum transaction needed)
*/
class Solution {
    public int minTransfers(int[][] transactions) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int[] t : transactions) {
            m.put(t[0], m.getOrDefault(t[0], 0) - t[2]);
            m.put(t[1], m.getOrDefault(t[1], 0) + t[2]);
        }
        return settle(0, new ArrayList<>(m.values()));
    }

    int settle(int start, List<Integer> debt) {
        while (start < debt.size() && debt.get(start) == 0)
            start++;
        if (start == debt.size()) return 0;
        int r = Integer.MAX_VALUE;
        for (int i = start + 1; i < debt.size(); i++){
            // one should be +ve and one should be -ve ( -ve&-ve and +ve &+ve no need of transaction)
            if (debt.get(start) * debt.get(i) < 0) { 
                
                int oldIVal = debt.get(i);
                // very IMPORTANT ... here start is giving all his money to i
                int newIVal = debt.get(i) + debt.get(start); 
                
                debt.set(i, newIVal);
                int totalTransacction  = 1 + settle(start + 1, debt); // as start has 0 balance we dont need start anymore. 
                
                r = Math.min(r, totalTransacction);
                debt.set(i, oldIVal);
            }
        }
        return r;
    }
}
