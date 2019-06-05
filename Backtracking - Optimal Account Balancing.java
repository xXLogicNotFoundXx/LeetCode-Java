/*
https://leetcode.com/problems/optimal-account-balancing/
465. Optimal Account Balancing
A group of friends went on holiday and sometimes lent each other money. For example, Alice paid for Bill's lunch for $10. 
Then later Chris gave Alice $5 for a taxi ride. 
We can model each transaction as a tuple (x, y, z) which means person x gave person y $z. 
Assuming Alice, Bill, and Chris are person 0, 1, and 2 respectively (0, 1, 2 are the person's ID), 
the transactions can be represented as [[0, 1, 10], [2, 0, 5]].
Given a list of transactions between a group of people, return the minimum number of transactions required to settle the debt.
Note:
A transaction will be given as a tuple (x, y, z). Note that x ≠ y and z > 0.
Person's IDs may not be linear, e.g. we could have the persons 0, 1, 2 or we could also have the persons 0, 2, 6.


Input:
[[0,1,10], [2,0,5]]
Output:
2
Explanation:
Person #0 gave person #1 $10.
Person #2 gave person #0 $5.
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
        for (int i = start + 1; i < debt.size(); i++)
            // one should be +ve and one should be -ve ( -ve&-ve and +ve &+ve no need of transaction)
            if (debt.get(start) * debt.get(i) < 0) { 
                
                int newIVal = debt.get(i) + debt.get(start);
                int oldIVal = debt.get(i);
                
                debt.set(i, newIVal);
                int totalTransacction  = 1 + settle(start + 1, debt);
                r = Math.min(r, totalTransacction);
                debt.set(i, oldIVal);
            }
        return r;
    }
}
