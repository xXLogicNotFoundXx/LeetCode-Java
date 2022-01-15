https://leetcode.com/articles/a-recursive-approach-to-segment-trees-range-sum-queries-lazy-propagation/

Total number of nodes in the comlete binary  is 2^h-1
By this definition, When only root node in the tree; the height of the tree is 1.

We know there are at least three components in our balanced Segment Tree:
  n numbers from our input array.
  n-1 internal nodes which are mandatorily required.
  The extra space we need to allocate for our padding.

  4*n = is enough size to create a balanced tree in an array.
