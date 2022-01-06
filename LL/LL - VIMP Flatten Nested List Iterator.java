/**
Medium - VIMP
Facebook6 LinkedIn5 Apple4 Salesforce4 Twitter3

https://leetcode.com/problems/flatten-nested-list-iterator/

 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
// list could be empty so we have to have Integer at the top so all the operations i did in next() has to be in hasNext()
// OR you can just flatten all list at once in the ArrayList and just manipulate the index on next and hasNext();
public class NestedIterator implements Iterator<Integer> {

    Stack<NestedInteger> stack = new Stack<>();

    public NestedIterator(List<NestedInteger> nestedList) {
        for(int i=nestedList.size()-1;i>=0;i--)
            stack.push(nestedList.get(i));
    }

    @Override
    public Integer next() {
        return stack.pop().getInteger();
    }

    @Override
    public boolean hasNext1() {
        if(stack.isEmpty())
            return false;

        NestedInteger peek = stack.peek();
        if(!peek.isInteger()){
            NestedInteger top = stack.pop();
            for(int i= top.getList().size()-1; i>=0; i--)
                stack.push(top.getList().get(i));

            return hasNext(); // nice
        }

        return true;
    }

    @Override  // No Recursion
    public boolean hasNext() {

        while (!stack.isEmpty() && !stack.peek().isInteger()){
            NestedInteger top = stack.pop();
            for(int i= top.getList().size()-1; i>=0; i--)
                stack.push(top.getList().get(i));
        }

        if(stack.isEmpty())
            return false;

        return true;
    }
}
