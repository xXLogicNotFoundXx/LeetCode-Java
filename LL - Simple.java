// Delete node, direct access to delete the node 
class Solution {
    public void deleteNode(ListNode node) {
        ListNode dummy = node;
        node=node.next;
        while(node.next!=null){
            dummy.val = node.val;
            dummy = node;
            node = node.next;
        }
        dummy.val=node.val;
        dummy.next =null;
    }
}
