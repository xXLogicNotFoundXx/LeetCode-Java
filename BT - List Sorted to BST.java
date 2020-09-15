/*
https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
Given the head of a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
*/
class Solution {
    // Time O(N.LogN),  Space O(LogN)
    public TreeNode sortedListToBST1(ListNode head) {
        if(head==null) 
            return null;
        return sortedListToBSTHelper(head,null);
    }
    
    TreeNode sortedListToBSTHelper(ListNode head, ListNode tail){
        if(head==tail)
            return null;
        
        ListNode slow = head;
        ListNode fast = head; 
        
        while(fast!=tail && fast.next!=tail){
            fast = fast.next.next;
            slow = slow.next;
        }
        
        TreeNode node = new TreeNode(slow.val);
        
        node.left  = sortedListToBSTHelper(head,slow);
        node.right = sortedListToBSTHelper(slow.next,tail);
        
        return node;
    }
    
    
/* Solution 2 :  Can you do it in O(N) and LogN space?

   1->2->3->4->4->5->6->7->8->9->10->12->13->18->20
   
                 7
            /         \
         4               12
       /   \           /    \
      2      5         9      18
     / \    /  \      / \     / \
    1   3   4   6    8  10   13  20
    
    So idea is almost like inorder traversal of BST. 
*/  
    // O(N) and LogN space 
    private ListNode node;
    public TreeNode sortedListToBST(ListNode head) {
        int size = 0;
        
        ListNode dummy = head; 
        node = head;
        
        while(dummy!=null){
            dummy=dummy.next;
            size++;
        }
        
        return sortedListToBSTHelper(0,size-1);
    }
    
    TreeNode sortedListToBSTHelper(int start, int end){
        
        if(start>end)
            return null;
        
        int mid = (end-start)/2 + start;
        TreeNode treeNode = new TreeNode();
        
        treeNode.left  = sortedListToBSTHelper(start, mid-1);
        
        treeNode.val = node.val; // create left most node 
        node=node.next;   // move the list now 
        
        treeNode.right = sortedListToBSTHelper(mid+1, end);
        
        return treeNode;
    }
}
