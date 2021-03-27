/**
https://leetcode.com/problems/closest-binary-search-tree-value/
Given a non-empty binary search tree and a target value, 
find the value in the BST that is closest to the target.
Given target value is a floating point.
target = 3.714286     target = 3.24
Output: 4             Output: 3
    4
   / \
  2   5
 / \
1   3
 */
class Solution {
    public int closestValue(TreeNode root, double target) {
        if(root==null) return -1;
        
        int ans = root.val;
        while(root!=null){
            //Math.abs return absolute value i.e. always positive
            if(Math.abs(target-ans) > Math.abs(target-root.val) ){
                ans = root.val;
            }
            root =   root.val > target ? root.left : root.right;
        }
        return ans;
    }
}

/*
Given the root of a binary search tree, a target value, and an integer k, return the k values in the BST that are closest to the target.
You may return the answer in any order.

Input: root = [4,2,5,1,3], target = 3.714286, k = 2
Output: [4,3]


Input: root = [1], target = 0.000000, k = 1
Output: [1]

*/
class Solution {
    // TIME :  N.Log(K)
    // SPACE : Log(N)... could be N as it is not balance binary tree 
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>( new Comparator<Integer>(){
            public int compare(Integer a, Integer b){
                double a1 = Math.abs(target-a);
                double b1 = Math.abs(target-b);
                if(a1<b1)
                    return 1;
                else 
                    return -1;
            }
        });
        
        binaryTreeTraversal(root, pq, k);
        
        List<Integer> list = new ArrayList<>();
        while(!pq.isEmpty()){
            list.add(pq.poll());
        }
        
        return list; 
    }
    
    void binaryTreeTraversal(TreeNode root, PriorityQueue<Integer> pq, int k){
        if(root==null)
            return; 
        
        pq.add(root.val);
        if(pq.size()>k)
            pq.poll();
        
        binaryTreeTraversal(root.left, pq, k);
        binaryTreeTraversal(root.right, pq, k);
    }
    
    /*  Best could be if we can maintain the window.. alwasy think where you use priority queue 
        can you maintain the window 
        Subtle hint if we traverse inorder that will give us sorted numbers ... 
        we can have a deque and alwasy compare abs value to firstElement and then we poll first element 
        that way it will be mainintain diff bothe sides 
        
        Also see.. how we are making this early termination. 
        
        
        TIME : O(N)
        SPACE : O(logN) ... could be N as it is not balance binary tree 
    */
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        LinkedList<Integer> res = new LinkedList<>();
        collect(root, target, k, res);
        return res;
    }

    public void collect(TreeNode root, double target, int k, LinkedList<Integer> res) {
        if (root == null) return;
        collect(root.left, target, k, res);

        if (res.size() == k) {
            //if size k, add curent and remove head if it's optimal, otherwise return
            if (Math.abs(target - root.val) < Math.abs(target - res.peekFirst())) 
                res.removeFirst();
            else 
                return; // early termination 
        }
        res.add(root.val);
        collect(root.right, target, k, res);
    }
}
