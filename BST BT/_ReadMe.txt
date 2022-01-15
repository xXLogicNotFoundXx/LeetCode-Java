Height of the balanced binary Trees is log(n) - n is the total number of nodes in
If there is only Root node -  height is 0 by this definition .

In a complete binary tree Height of the tree is log(n) - n is number of nodes. -> height of the tree with only root node is 0
Some says the binary tree Height of the tree is log(n) + 1 - n is number of nodes. -> height of the tree with only root node is 1

Height    Nodes    Log to the base2 calculation
    0        1      log 1 = 0
    1        3      log 3 = 1
    2        7      log 7 = 2
    3       15      log 15 = 3



    But, Total number of nodes in the comlete binary  is 2^h-1

    2^0 = 1
    2^1 = 2     by this calculations height of the only root node should be 1.
    2^2 = 4
    2^3 = 8
    2^4 = 16


    It just an assuption you make for the recursive description of the height of a binary tree.
    You can consider a tree composed by just a node either with 0 height or with 1 height.

    If you really want to think about it somehow you can think that

    it's 0 if you consider the height as a edge count (so that a single node doesn't have any edge, hence 0)
    it's 1 if you consider the height as a node count (so that a single node counts as 1)



Height VS Depth

The depth of a NODE is the number of edges from the node to the tree's root node.
A root node will have a depth of 0.

The height of a NODE is the number of edges on the longest path from the node to a leaf.
A leaf node will have a height of 0.

https://stackoverflow.com/questions/2603692/what-is-the-difference-between-tree-depth-and-height
