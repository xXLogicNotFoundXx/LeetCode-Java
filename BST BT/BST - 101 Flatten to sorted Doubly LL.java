/*
Medium -
Facebook63 Amazon4 Microsoft2

426. Convert Binary Search Tree to Sorted Doubly Linked List
https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/
Convert a BST to a sorted circular doubly-linked list in-place.
left and right is like previous and next pointers.

               4
          2        5
      1      3
-------------------------------------
       Head
      - 1 <=> 2 <=> 3 <=> 4 <=> 5
      --------------------------^
*/
// Time-O(n) and    O(n) space, tree is not blaalanced
class Solution {
    public Node treeToDoublyList(Node root) {
        if(root==null)
            return null;

        Stack<Node> st = new Stack<>();
        Node dummy = new Node();
        Node prev = dummy;

        while(root!=null || !st.isEmpty()){ // this is iterative inorder traversal
            while(root!=null){
                st.push(root);
                root = root.left;
            }
            root = st.pop();

            /******** operations you want to do in inorder traversal  *****/
            prev.right = root;
            root.left = prev;
            prev = root;
            /**************/

            root = root.right;
        }

        prev.right = dummy.right; // connect end to the start
        dummy.right.left = prev; // connect start to end

        return dummy.right;
    }
}

/* Recursive */
// Time-O(n) and    O(n) space, tree is not blaalanced
class Solution {
  	Node prev = null;

  	public Node treeToDoublyList(Node root) {
    		if (root == null)
          return null;

    		Node dummy = new Node();
    		prev = dummy;

    		inOrder(root);

    		// connect head and tail
    		prev.right = dummy.right;
    		dummy.right.left = prev;
    		return dummy.right;
  	}

  	private void inOrder (Node cur) {
    		if (cur == null)
          return;

    	  inOrder(cur.left);

    		prev.right = cur;
    		cur.left = prev;
    		prev = cur;

    	  inOrder(cur.right);
  	}
}
