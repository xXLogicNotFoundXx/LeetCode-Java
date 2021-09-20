/*
FB MSFT 5-6
https://leetcode.com/problems/maximum-frequency-stack/
Implement FreqStack, a class which simulates the operation of a stack-like data structure.

FreqStack has two functions:
push(int x), which pushes an integer x onto the stack.
pop(), which removes and returns the most frequent element in the stack.
If there is a tie for most frequent element, the element closest to the top of the stack is removed and returned.
 
Input: 
["FreqStack","push","push","push","push","push","push","pop","pop","pop","pop"],
[[],[5],[7],[5],[7],[4],[5],[],[],[],[]]
Output: [null,null,null,null,null,null,null,5,7,5,4]


We need 3 data points while popping ... 
should be order by frequecy ... 
if frequecy is same then you have to see when it was added .. so may be index some sort.

so if we can just make a node out of three infow -> value, frequency and PushIndex/Count 
- value and freqency can be maintianed separatly in a map. 
- and from the map info we can generate each node (v, f, index) and priority queue will give which node to pop.

*/

// Sultuion 1 : O(logN)
class FreqStack {
 
  class Node{
        public int value;
        int freq;
        int pushIndex;
        public Node(int v, int f, int p){
            this.value = v;
            this.freq = f;
            this.pushIndex = p;
        }
    }
    
    class NodeComparator implements Comparator<Node>{
        public int compare(Node a, Node b){
            if (a.freq != b.freq) return b.freq -a.freq;
                    return b.pushIndex - a.pushIndex;

        }
    }
 
    int pushCount;
    PriorityQueue<Node> pq;
    Map<Integer, Integer> map;
    public FreqStack() {
        pushCount = 0;
        pq = new PriorityQueue<Node>(new NodeComparator());
        map = new HashMap<Integer, Integer>();
    }
    
    public void push(int x) {
        if (!map.containsKey(x)){
            map.put(x, 0);
        }
        map.put(x, map.get(x) + 1);
        Node n = new Node(x, map.get(x), pushCount++);
        pq.offer(n);
    }
    
    public int pop() {
        return pq.poll().value;
    }

}

// O(1)
class FreqStack {

    List<Stack<Integer>> bucket;
    HashMap<Integer, Integer> map;
    
    public FreqStack() {
        bucket = new ArrayList<>();
        map = new HashMap<>();
    }
    
    public void push(int x) {
        map.put(x, map.getOrDefault(x, 0) + 1);
        int freq = map.get(x);
        if (freq - 1 >= bucket.size()) {
            bucket.add(new Stack<Integer>());
        }
        bucket.get(freq - 1).add(x);
    }
    
    public int pop() {
        int freq = bucket.size();
        int x = bucket.get(freq - 1).pop();
        if (bucket.get(freq - 1).isEmpty()) bucket.remove(bucket.size() - 1);
        
        map.put(x, map.get(x) - 1);
        if (map.get(x) == 0) map.remove(x);
        
        return x;
    }
}
