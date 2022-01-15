/*
Medium -
Google6 Amazon3

https://leetcode.com/problems/satisfiability-of-equality-equations/
Given an array equations of strings that represent relationships between variables,
Each string equations[i] has length 4 and takes one of two different forms: "a==b" or "a!=b".
Here, a and b are lowercase letters (not necessarily different) that represent one-letter variable names.
Return true if and only if it is possible to assign integers to variable names so as to satisfy all the given equations.

Input: ["a==b","b!=a"]
Output: false

Input: ["b==a","a==b"]
Output: true

Input: ["a==b","b!=c","c==a"]
Output: false

Input: ["c==c","b==d","x!=z"]
Output: true

https://en.wikipedia.org/wiki/Disjoint-set_data_structure
A disjoint-set forest consists of a number of elements each of which stores an id, a parent pointer,
Find(x) follows the chain of parent pointers from x up the tree until it reaches a root element, whose parent is itself.
Path compression flattens the structure of the tree by making every node point to the root whenever Find is used on it.
This is valid, since each element visited on the way to a root is part of the same set. The resulting flatter tree speeds up
future operations not only on these elements, but also on those referencing them.
// there is Union by ranking and size too but we havent used either of that here.

Time Complexity:
    Union Find Operation, amortized O(1)  bcz of path compression
    First pass all equations, O(N)
    Second pass all inequations, O(N)

    Overall O(N)
*/

class Solution {

    public boolean equationsPossible(String[] equations) {
        int[] parents = new int[26];
        for (int i = 0; i < 26; ++i)
            parents[i] = i;

        for (String e : equations){
            if (e.charAt(1) == '='){
               union(parents, e.charAt(0)-'a', e.charAt(3)-'a');
            }
        }

        for (String e : equations){
            if (e.charAt(1) == '!'){
                int a = find(parents, e.charAt(0)-'a');
                int b = find(parents, e.charAt(3)-'a');
                if(a==b)
                    return false;
            }
        }

        return true;
    }

    void union(int []parents, int x, int y){
        int a = find(parents, x);
        int b = find(parents, y);
        parents[a] = b;
    }

    int find(int []parents, int x) {

        if (x != parents[x]) {
            parents[x] = find(parents, parents[x]); // path compression
        }
        return parents[x];
    }
}
